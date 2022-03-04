package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.models.User;
import com.revature.foundations.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    private final UserService userService;
    private final ObjectMapper mapper;

    public TestServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewUserRequest newUserRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);
        User newUser = userService.register(newUserRequest);
        resp.getWriter().write("<h1>/" + newUserRequest +"</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NewUserRequest newUserRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            resp.getWriter().write("<h1>/" + newUserRequest + "</h1>");
            User newUser = userService.register(newUserRequest);
            resp.setStatus(201);

        }catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
