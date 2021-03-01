
package com.example.mydukaan.modal.ChatChomplane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatComplainList {

    @SerializedName("complaint_list")
    @Expose
    private List<ComplaintList> complaintList = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;


    public List<ComplaintList> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<ComplaintList> complaintList) {
        this.complaintList = complaintList;
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
