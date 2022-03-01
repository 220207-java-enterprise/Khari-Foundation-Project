package com.revature.foundations.models;

import java.util.Objects;

public class ReimbursementTypes {
    private String type_id;
    private String type;

    public ReimbursementTypes() {
    }

    /*
     * Constructor with 2 input parameters
     * @param id the id of the type
     * @param type the name of the type
     */

    public ReimbursementTypes(String type_id, String type) {
        this.type_id = type_id;
        this.type = type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementTypes reimbursementTypes = (ReimbursementTypes) o;
        return Objects.equals(type_id, reimbursementTypes.type_id) &&
                Objects.equals(type, reimbursementTypes.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_id, type);
    }

    @Override
    public String toString() {
        return "reimbursementTypes{" +
                "type_id='" + type_id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
