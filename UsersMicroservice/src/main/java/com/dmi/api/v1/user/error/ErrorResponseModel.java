package com.dmi.api.v1.user.error;

import java.sql.Timestamp;

public class ErrorResponseModel {
    private Timestamp timestamp;
    private String errorMessage;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
