package com.revature.foundations.dtos.requests;

import java.util.Objects;

public class UserUpdateRequest {
    private String user_id;
    private String given_name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Boolean is_active;

    private String role;

    public UserUpdateRequest() {
        super(); // not required, but it bugs me personally not to have it
    }

    public UserUpdateRequest(String given_name, String surname, String email, String username, String password) {
        this.given_name = given_name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserUpdateRequest(String given_name, String surname, String email, String username,
                             String password, String role) {
        this.given_name = given_name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserUpdateRequest(String given_name, String surname, String email, String username, Boolean is_active) {
        this.given_name = given_name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.is_active = is_active;
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

    public Boolean getActive() {
        return is_active;
    }

    public void setActive(Boolean active) {
        is_active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toFileString() {
        return user_id + ":" +
                given_name + ":" +
                surname + ":" +
                email + ":" +
                username + ":" +
                password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateRequest that = (UserUpdateRequest) o;
        return Objects.equals(user_id, that.user_id)
                && Objects.equals(given_name, that.given_name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(email, that.email)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && Objects.equals(is_active, that.is_active)
                && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, given_name, surname, email, username, password, is_active, role);
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "user_id='" + user_id + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + is_active +
                ", role='" + role + '\'' +
                '}';
    }

}
