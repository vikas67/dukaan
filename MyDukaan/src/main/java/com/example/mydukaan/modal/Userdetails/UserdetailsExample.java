package com.example.mydukaan.modal.Userdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserdetailsExample {
    @SerializedName("details")
    @Expose
    private UserProfile user_profile ;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public UserProfile getuser_profile() {
        return user_profile;
    }

    public void setuser_profile(UserProfile user_profile) {
        this.user_profile = user_profile;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
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
