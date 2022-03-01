package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.services.UsersService;

import javax.servlet.http.HttpServlet;

public class AuthServlet extends HttpServlet {
    private final UsersService usersService;
    private final ObjectMapper objectMapper;

    public AuthServlet(UsersService usersService, ObjectMapper objectMapper) {
        this.usersService = usersService;
        this.objectMapper = objectMapper;
    }
}
