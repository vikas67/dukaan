
package com.example.mydukaan.modal.ChatChomplane;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("order_no")
    @Expose
    private String orderNo;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("resolution_status")
    @Expose
    private Object resolutionStatus;
    @SerializedName("resolution_des")
    @Expose
    private Object resolutionDes;

    private boolean isExpanded = false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getResolutionStatus() {
        return resolutionStatus;
    }

    public void setResolutionStatus(Object resolutionStatus) {
        this.resolutionStatus = resolutionStatus;
    }

    public Object getResolutionDes() {
        return resolutionDes;
    }

    public void setResolutionDes(Object resolutionDes) {
        this.resolutionDes = resolutionDes;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
