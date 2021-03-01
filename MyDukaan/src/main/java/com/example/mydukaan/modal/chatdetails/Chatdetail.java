
package com.example.mydukaan.modal.chatdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chatdetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("agent_id")
    @Expose
    private String agentId;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("user_token")
    @Expose
    private String userToken;
    @SerializedName("agent_token")
    @Expose
    private String agentToken;
    @SerializedName("agent_name")
    @Expose
    private String agentName;
    @SerializedName("chatlist_id")
    @Expose
    private String chatlistId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getAgentToken() {
        return agentToken;
    }

    public void setAgentToken(String agentToken) {
        this.agentToken = agentToken;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChatlistId() {
        return chatlistId;
    }

    public void setChatlistId(String chatlistId) {
        this.chatlistId = chatlistId;
    }

}
