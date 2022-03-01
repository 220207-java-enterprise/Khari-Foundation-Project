package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.foundations.services.UsersService;

import javax.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private UsersService usersService;
    private ObjectMapper objectMapper;

    public UsersServlet(UsersService usersService, ObjectMapper objectMapper) {
        this.usersService = usersService;
        this.objectMapper = objectMapper;
    }
}
