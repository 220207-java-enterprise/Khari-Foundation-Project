package com.revature.foundations.models;

import java.util.Objects;

public class UserRoles {
    private String role_id;
    private String role;

    public UserRoles() {
        super();
    }

    public UserRoles(String role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public UserRoles(String role_id) {
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(role_id, userRoles.role_id) &&
                Objects.equals(role, userRoles.role);
    }

    public int hashCode() {
        return Objects.hash(role_id, role);
    }

    @Override
    public String toString() {
        return "userRoles{" +
                "role_id='" + role_id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
