
package com.example.mydukaan.modal.SignIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatAgentLogin {

    @SerializedName("agentsdetails")
    @Expose
    private Agentsdetails agentsdetails;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Agentsdetails getAgentsdetails() {
        return agentsdetails;
    }

    public void setAgentsdetails(Agentsdetails agentsdetails) {
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
