
package com.example.mydukaan.modal.ChatStart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatlistExample {

    @SerializedName("user_chat")
    @Expose
    private List<UserChat> userChat = null;

    public List<UserChat> getUserChat() {
        return userChat;
    }

    public void setUserChat(List<UserChat> userChat) {
        this.userChat = userChat;
    }

}
