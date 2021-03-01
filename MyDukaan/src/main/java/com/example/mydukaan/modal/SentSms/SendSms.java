package com.example.mydukaan.modal.SentSms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendSms {
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("server_key")
    @Expose
    private String serverKey;
    @SerializedName("agent_id")
    @Expose
    private String agentId;
    @SerializedName("chatlist_id")
    @Expose
    private String chatlistId;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("api_type")
    @Expose
    private String apiType;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getChatlistId() {
        return chatlistId;
    }

    public void setChatlistId(String chatlistId) {
        this.chatlistId = chatlistId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
