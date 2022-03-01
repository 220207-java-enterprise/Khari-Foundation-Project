package com.revature.foundations.models;

import java.util.Objects;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class Reimbursements {
    private String reimb_id;
    private int amount;
    private int submitted;
    private int resolved;
    private String description;
    private byte receipt;
    private String payment_id;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;

    public Reimbursements() {
        super();
    }

    public Reimbursements(String reimb_id,
                          int amount,
                          int submitted,
                          int resolved,
                          String description,
                          byte receipt,
                          String payment_id,
                          String author_id,
                          String resolver_id,
                          String status_id,
                          String type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getReceipt() {
        return receipt;
    }

    public void setReceipt(byte receipt) {
        this.receipt = receipt;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursements reimbursements = (Reimbursements) o;
        return Objects.equals(reimb_id, reimbursements.reimb_id) &&
                Double.compare(amount, reimbursements.amount) == 0 &&
                Objects.equals(submitted, reimbursements.submitted) &&
                Objects.equals(resolved, reimbursements.resolved) &&
                Objects.equals(description, reimbursements.description) &&
                Objects.equals(receipt, reimbursements.receipt) &&
                Objects.equals(author_id, reimbursements.author_id) &&
                Objects.equals(resolver_id, reimbursements.resolver_id) &&
                Objects.equals(status_id, reimbursements.status_id) &&
                Objects.equals(type_id, reimbursements.type_id);
    }

    public int hashCode(){
        return Objects.hash(reimb_id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id, type_id);
    }

    @Override
    public String toString() {
        return "reimbursements{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", resolver_id='" + resolver_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}