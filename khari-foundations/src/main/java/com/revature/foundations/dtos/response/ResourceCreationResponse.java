package com.revature.foundations.dtos.response;

public class ResourceCreationResponse {

    private String user_id;

    public ResourceCreationResponse(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
