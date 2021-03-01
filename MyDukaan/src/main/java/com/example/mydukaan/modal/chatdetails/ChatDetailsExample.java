
package com.example.mydukaan.modal.chatdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatDetailsExample {

    @SerializedName("chatdetail")
    @Expose
    private List<Chatdetail> chatdetail = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Chatdetail> getChatdetail() {
        return chatdetail;
    }

    public void setChatdetail(List<Chatdetail> chatdetail) {
        this.chatdetail = chatdetail;
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
