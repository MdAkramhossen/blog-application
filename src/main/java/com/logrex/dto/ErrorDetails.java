package com.logrex.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorDetails {

    private Date timeStapes;
    private String message;
    private String details;


    public ErrorDetails(Date timeStapes, String message, String details) {
        this.timeStapes = timeStapes;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStapes() {
        return timeStapes;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
