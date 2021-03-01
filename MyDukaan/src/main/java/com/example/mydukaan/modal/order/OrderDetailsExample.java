package com.example.mydukaan.modal.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsExample {

    public List<Orders> getDetails() {
        return details;
    }

    public void setDetails(List<Orders> details) {
        this.details = details;
    }

    @SerializedName("details")
    @Expose
    private List<Orders> details = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;



    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
