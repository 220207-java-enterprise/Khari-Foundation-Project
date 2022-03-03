package com.revature.foundations.dtos.response;

import com.revature.foundations.models.UserRoles;
import com.revature.foundations.models.Users;

public class UsersResponse {

    private String user_id;
    private String given_name;
    private String surname;
    private String username;
    private String role_id;

    public UsersResponse() {
        super();
    }

    public UsersResponse(Users users) {
        this.user_id = users.getUser_id();
        this.given_name = users.getGiven_name();
        this.surname = users.getSurname();
        this.username = users.getUsername();
        this.role_id = users.getRole().getRole();
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(UserRoles role_id) {
        this.role_id = String.valueOf(role_id);
    }
}
