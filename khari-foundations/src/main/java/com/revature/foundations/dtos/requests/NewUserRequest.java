package com.revature.foundations.dtos.requests;

import com.revature.foundations.models.Users;

public class NewUserRequest {

    private String given_name;
    private String surname;
    private String email;
    private String username;
    private String password;

    public NewUserRequest() {
        super();
    }

    public NewUserRequest(String firstName, String lastName, String email, String username, String password) {
        this.given_name = firstName;
        this.surname = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users extractUser() {
        return new Users(given_name, surname, email, username, password);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
