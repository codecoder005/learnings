package com.dmi.api.v1.user.response.model;

import java.io.Serializable;

public class CreateUserResponseModel implements Serializable {
    private static final long serialVersionUID = 7178249084669066784L;
    private String email;
    private String firstName;
    private String lastName;
    private String userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
