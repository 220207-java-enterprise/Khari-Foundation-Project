package com.revature.foundations.dtos.response;

import com.revature.foundations.models.User;

public class Principal {

    private String user_id;
    private String username;
    private String role;

    public Principal() {
        super();
    }

    public Principal(User user) {
        this.user_id = user.getUser_id();
        this.username = user.getUsername();
        this.role = user.getRole().getRole();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
