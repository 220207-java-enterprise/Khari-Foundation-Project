package com.revature.foundations.services;

import com.revature.foundations.daos.CrudDAO;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.dtos.requests.LoginRequest;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.response.UsersResponse;
import com.revature.foundations.models.UserRoles;
import com.revature.foundations.models.Users;
import com.revature.foundations.util.exceptions.AuthenticationException;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UsersService {

    private UserDAO userDAO; // a dependency of UserService

    public UsersService(Object userDAO) {
    }

    // Constructor injection
    public void UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static List<UsersResponse> getAllUsers() {

        // Pre-Java 8 mapping logic (without Streams)
//        List<AppUser> users = userDAO.getAll();
//        List<AppUserResponse> userResponses = new ArrayList<>();
//        for (AppUser user : users) {
//            userResponses.add(new AppUserResponse(user));
//        }
//        return userResponses;

        // Java 8+ mapping logic (with Streams)
        return CrudDAO.getAll() != null ? CrudDAO.getAll()
                .stream()
                .map(UsersResponse::new) // intermediate operation
                .collect(Collectors.toList()) : null; // terminal operation
    }

    public Users register(NewUserRequest newUsersRequest) {

        Users newUsers = newUsersRequest.extractUser();

        if (!isUserValid(newUsers)) {
            throw new InvalidRequestException("Bad registration details given.");
        }

        boolean usernameAvailable = isUsernameAvailable(newUsers.getUsername());
        boolean emailAvailable = isEmailAvailable(newUsers.getEmail());

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }

        // TODO encrypt provided password before storing in the database

        newUsers.setUser_id(UUID.randomUUID().toString());
        newUsers.setRole_id(new UserRoles("7c3521f5-ff75-4e8a-9913-01d15ee4dc97", "BASIC_USER")); // All newly registered users start as BASIC_USER
        userDAO.save(newUsers);

        return newUsers;
    }

    public Users login(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (isUsernameValid(username) || isPasswordValid(password)) {
            throw new InvalidRequestException("Invalid credentials provided!");
        }

        // TODO encrypt provided password (assumes password encryption is in place) to see if it matches what is in the DB

        Users authUsers = userDAO.findUsersByUsernameAndPassword(username, password);

        if (authUsers == null) {
            throw new AuthenticationException();
        }

        return authUsers;

    }

    public boolean isUserValid(Users Users) {

        // First name and last name are not just empty strings or filled with whitespace
        if (Users.getGiven_name().trim().equals("") || Users.getSurname().trim().equals("")) {
            return false;
        }

        // Usernames must be a minimum of 8 and a max of 25 characters in length, and only contain alphanumeric characters.
        if (isUsernameValid(Users.getUsername())) {
            return false;
        }

        // Passwords require a minimum eight characters, at least one uppercase letter, one lowercase
        // letter, one number and one special character
        if (isPasswordValid(Users.getPassword())) {
            return false;
        }

        // Basic email validation
        return isEmailValid(Users.getEmail());

    }

    public boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }

    public static boolean isUsernameValid(String username) {
        if (username == null) return true;
        return !username.matches("^[a-zA-Z0-9]{8,25}");
    }

    public boolean isPasswordValid(String password) {
        return !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    public static boolean isUsernameAvailable(String username) {
        if (username == null || isUsernameValid(username)) return false;
        return UserDAO.findUserByUsername(username) == null;
    }

    public static boolean isEmailAvailable(String email) {
        if (email == null || !isEmailValid(email)) return false;
        return UserDAO.findUserByEmail(email) == null;
    }
}
