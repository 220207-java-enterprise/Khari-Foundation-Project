package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.requests.UserUpdateRequest;
import com.revature.foundations.dtos.response.Principal;
import com.revature.foundations.dtos.response.ResourceCreationResponse;
import com.revature.foundations.dtos.response.UserResponse;
import com.revature.foundations.models.User;
import com.revature.foundations.services.TokenService;
import com.revature.foundations.services.UserService;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


//Mapping: /users/
public class UserServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(UserServlet.class);

    private final TokenService tokenService;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserServlet(UserService userService, ObjectMapper objectMapper, TokenService tokenService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }
    // Admin get all users
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("availability")) {
            checkAvailability(req, resp);
            return; // necessary, otherwise we end up doing more work than was requested
        }

        // TODO implement some security logic here to protect sensitive operations


        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (requester == null) {
            resp.setStatus(401);
            return;
        }
        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }

        List<UserResponse> users = UserService.getAllEmployee();
        String payload = objectMapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().write(payload);


    }

    //Anyone can register new user/manager
    // registration endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        register(req, resp);

        PrintWriter respWriter = resp.getWriter();

        try {

            NewUserRequest newUserRequest = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);
            User newUser = userService.register(newUserRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = objectMapper.writeValueAsString(new ResourceCreationResponse(newUser.getUser_id()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }

    }

    // Admin only update/approve/soft delete user
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        if (requester == null) {
            resp.setStatus(401);
            return;
        }
        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }
        updateUser(req, resp);
    }

    // Admin only update/approve/soft delete user
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        if (requester == null) {
            resp.setStatus(401);
            return;
        }
        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }
        deleteUser(req, resp);
    }

    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{
            UserUpdateRequest updateUser = objectMapper.readValue(req.getInputStream(), UserUpdateRequest.class);

            userService.updateUser(updateUser);

            resp.setStatus(204);
            resp.setContentType("application/json");

        }catch(InvalidRequestException e){
            resp.setStatus(405);
        } catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserUpdateRequest updateUser = objectMapper.readValue(req.getInputStream(), UserUpdateRequest.class);
            updateUser.setActive(false);

            userService.updateUser(updateUser);

            resp.setStatus(204);
            resp.setContentType("application/json");

        } catch (InvalidRequestException e) {
            resp.setStatus(405);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    // User can register as manager or user
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter respWriter = resp.getWriter();

        try {

            NewUserRequest newUserRequest = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);
            User newUser = userService.register(newUserRequest);

            resp.setStatus(201);
            resp.setContentType("application/json");
            String payload = objectMapper.writeValueAsString(new ResourceCreationResponse(newUser.getUser_id()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }
    }

    //-------------------------
    protected void checkAvailability(HttpServletRequest req, HttpServletResponse resp) {
        String usernameValue = req.getParameter("username");
        String emailValue = req.getParameter("email");
        if (usernameValue != null) {
            if (userService.isUsernameAvailable(usernameValue)) {
                resp.setStatus(204); // NO CONTENT
            } else {
                resp.setStatus(409);
            }
        } else if (emailValue != null) {
            if (userService.isEmailAvailable(emailValue)) {
                resp.setStatus(204);
            } else {
                resp.setStatus(409);
            }
        }
    }

}
