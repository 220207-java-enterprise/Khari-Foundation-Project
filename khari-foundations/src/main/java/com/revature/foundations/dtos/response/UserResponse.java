package com.revature.foundations.dtos.response;

import com.revature.foundations.models.UserRole;
import com.revature.foundations.models.User;

public class UserResponse {

    private String user_id;
    private String given_name;
    private String surname;
    private String username;
    private Boolean is_active;
    private String role;

    public UserResponse() {
        super();
    }

    public UserResponse(User user) {
        this.user_id = user.getUser_id();
        this.given_name = user.getGiven_name();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.is_active = user.is_active();
        this.role = user.getRole().getRole();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean is_Active() {
        return is_active;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user_id='" + user_id + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", is_active=" + is_active +
                ", role='" + role + '\'' +
                '}';
    }
}

