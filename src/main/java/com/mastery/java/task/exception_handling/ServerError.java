package com.mastery.java.task.exception_handling;

public class ServerError {
    private String error;

    public ServerError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
