
package com.example.mydukaan.modal.ChatStart;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserChat implements Parcelable {
 public UserChat(){

 }

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
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("agent_id")
    @Expose
    private String agentId;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public static Creator<UserChat> getCREATOR() {
        return CREATOR;
    }

    @SerializedName("order_no")
    @Expose
    private String order_no;
    @SerializedName("agent_name")
    @Expose
    private String agentName;
    @SerializedName("chat_joined_time")
    @Expose
    private String chatJoinedTime;
    @SerializedName("chat_active")
    @Expose
    private String chatActive;

    protected UserChat(Parcel in) {
        id = in.readString();
        userId = in.readString();
        userName = in.readString();
        orderId = in.readString();
        startTime = in.readString();
        agentId = in.readString();
        agentName = in.readString();
        chatJoinedTime = in.readString();
        chatActive = in.readString();
        count_bedage = in.readString();
    }

    public static final Creator<UserChat> CREATOR = new Creator<UserChat>() {
        @Override
        public UserChat createFromParcel(Parcel in) {
            return new UserChat(in);
        }

        @Override
        public UserChat[] newArray(int size) {
            return new UserChat[size];
        }
    };

    public String getCount_bedage() {
        return count_bedage;
    }

    public void setCount_bedage(String count_bedage) {
        this.count_bedage = count_bedage;
    }

    String count_bedage;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChatJoinedTime() {
        return chatJoinedTime;
    }

    public void setChatJoinedTime(String chatJoinedTime) {
        this.chatJoinedTime = chatJoinedTime;
    }

    public String getChatActive() {
        return chatActive;
    }

    public void setChatActive(String chatActive) {
        this.chatActive = chatActive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(orderId);
        dest.writeString(startTime);
        dest.writeString(agentId);
        dest.writeString(agentName);
        dest.writeString(chatJoinedTime);
        dest.writeString(chatActive);
        dest.writeString(count_bedage);
    }
}
