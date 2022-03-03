package com.revature.foundations.models;

import java.util.Objects;

public class ReimbursementStatuses {
    private String status_id;
    private String status;

    public ReimbursementStatuses() {
        super();
    }

    /*
     * Constructor with 2 input parameters
     * @param id the id of the status
     * @param status the name of the status
     */

    public ReimbursementStatuses(String status_id, String status) {
        this.status_id = status_id;
        this.status = status;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatuses reimbursementStatuses = (ReimbursementStatuses) o;
        return Objects.equals(status_id, reimbursementStatuses.status_id) &&
                Objects.equals(status, reimbursementStatuses.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status_id, status);
    }

    @Override
    public String toString() {
        return "reimbursementStatuses{" +
                "status_id='" + status_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
