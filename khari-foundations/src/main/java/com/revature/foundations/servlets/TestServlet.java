package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public UserService getUserService() {
        return userService;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        resp.getWriter().write("<h1>/test works!</h1>");
    }
}
