package com.revature.foundations.services;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.daos.UserRolesDAO;
import com.revature.foundations.dtos.requests.LoginRequest;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.requests.UserUpdateRequest;
import com.revature.foundations.dtos.response.UserResponse;
import com.revature.foundations.models.User;
import com.revature.foundations.models.UserRole;
import com.revature.foundations.util.exceptions.AuthenticationException;
import com.revature.foundations.util.exceptions.ForbiddenException;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    private UserDAO userDAO;
    private UserRolesDAO userRoleDAO;


    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
    }

    public static List<UserResponse> getAllEmployee() {
        return null;
    }


    //Admin
    public List<UserResponse> getAllEmployees(){
        List<User> users = userDAO.getAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users){
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
    }

    // Admin/User
    public User register(NewUserRequest newUserRequest) {
        User newUser = newUserRequest.extractUser();

        if (!isUserValid(newUser) || newUserRequest.getRole().equals("ADMIN")) {
            throw new InvalidRequestException("Bad registration details were given.");
        }

        boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());
        boolean emailAvailable = isEmailAvailable(newUser.getEmail());

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }
        UserRole myRole = userRoleDAO.getById(newUserRequest.getRole());
        newUser.setRole(myRole);

        System.out.println(myRole);
        newUser.setUser_id(UUID.randomUUID().toString());
        newUser.setIs_active(false);

        userDAO.save(newUser);

        return newUser;
    }


    public User login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (isUsernameValid(username) || isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        User authUser = userDAO.findUserByUsername(username);

        // Check for if user exists then check if user is active
        if (authUser == null) {
            throw new AuthenticationException();
        }
        if (!authUser.is_active()) {
            throw new ForbiddenException();
        }

        return authUser;
    }

    // Admin update user status
    public void updateUser(UserUpdateRequest userUpdate) throws IOException {
        User newUser = userDAO.getById(userUpdate.getUser_id());
        System.out.println(newUser);
        if (newUser.getRole().getRole().equals("ADMIN"))
            throw new InvalidRequestException("Cannot remove admin");

        UserRole myRole = userRoleDAO.getById(userUpdate.getRole());

        //Check for any updates then prepare User to be updated
        if(userUpdate.getGiven_name() != null)
            newUser.setGiven_name(userUpdate.getGiven_name());
        if(userUpdate.getSurname() != null)
            newUser.setGiven_name(userUpdate.getGiven_name());
        if(userUpdate.getEmail() != null)
            newUser.setEmail(userUpdate.getEmail());
        if(userUpdate.getUsername() != null)
            newUser.setUsername(userUpdate.getUsername());
        if(userUpdate.getPassword() != null)
            newUser.setPassword(userUpdate.getPassword());
        if(userUpdate.getActive() != null )
            newUser.setIs_active(userUpdate.getActive());
        if(userUpdate.getRole() != null)
            newUser.setRole(myRole);

        if (newUser.getRole().getRole().equals("ADMIN"))
            throw new InvalidRequestException("Cannot promote to admin");

        System.out.println(newUser);
        userDAO.update(newUser);
    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }


    public boolean isUsernameValid(String username) {
        if (username == null) return true;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }


    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }


    public boolean isUsernameAvailable(String username) {
        return userDAO.findUserByUsername(username) == null;
    }


    public boolean isEmailAvailable(String email) {
        return userDAO.findUserByEmail(email) == null;
    }


    private boolean isUserValid(User appUser) {

        // First name and last name are not just empty strings or filled with whitespace
        if (appUser.getGiven_name().trim().equals("") || appUser.getSurname().trim().equals("")) {
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (isUsernameValid(appUser.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (isPasswordValid(appUser.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(appUser.getEmail());
    }
}
