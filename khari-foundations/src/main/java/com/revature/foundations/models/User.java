package com.revature.foundations.models;

import java.util.Objects;

public class User {
    private String user_id;
    private String username;
    private String email;
    private String password;
    private String given_name;
    private String surname;
    private Boolean is_active;
    private UserRole role;

    public User() {
        super();
    }

    public User(String user_id, String username, String email, String password, String given_name, String surname, Boolean is_active, UserRole role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
        this.is_active = is_active;
        this.role = role;
    }

    public User(String given_name, String surname, String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public Boolean getIs_active() {
        return is_active;
    }

    public boolean is_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id)
                && Objects.equals(username, user.username)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(given_name, user.given_name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(is_active, user.is_active)
                && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, email, password, given_name, surname, is_active, role);
    }

}

