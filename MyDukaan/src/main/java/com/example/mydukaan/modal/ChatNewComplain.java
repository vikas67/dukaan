
package com.example.mydukaan.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatNewComplain {

    @SerializedName("agentsdetails")
    @Expose
    private Object agentsdetails;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Object getAgentsdetails() {
        return agentsdetails;
    }

    public void setAgentsdetails(Object agentsdetails) {
        this.agentsdetails = agentsdetails;
    }

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
