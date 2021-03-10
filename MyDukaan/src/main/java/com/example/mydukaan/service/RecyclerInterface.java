package com.example.mydukaan.service;


import com.example.mydukaan.modal.ChatChomplane.ChatComplainList;
import com.example.mydukaan.modal.ChatNewComplain;
import com.example.mydukaan.modal.ChatOrderAction.Chatorderaction;
import com.example.mydukaan.modal.ChatStart.ChatlistExample;
import com.example.mydukaan.modal.SentSms.SendSms;
import com.example.mydukaan.modal.SignIn.ChatAgentLogin;
import com.example.mydukaan.modal.Userdetails.UserdetailsExample;
import com.example.mydukaan.modal.chatdetails.ChatDetailsExample;
import com.example.mydukaan.modal.order.OrderDetailsExample;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RecyclerInterface {

    @POST("Chat_agent_login")//your api link
    @FormUrlEncoded
    Call<ChatAgentLogin> SingIn(@Field("login_id") String email, @Field("password") String password, @Field("fcm_token") String fcm_token);

    @POST("send_agent_chat_sms")//your api link
    @FormUrlEncoded
    Call<SendSms> SendSms(@Field("agent_id") String agent_id, @Field("user_id") String user_id, @Field("msg") String msg, @Field("order_id") String order_id, @Field("side") String side, @Field("chatlist_id") String chatlist_id, @Field("user_name") String user_name);

    @POST("chatlist")//your api link
    @FormUrlEncoded
    Call<ChatlistExample> chatlist(@Field("agent_id") String agent_id);

    @POST("chat_detail")//your api link
    @FormUrlEncoded
    Call<ChatDetailsExample> Chat_details(@Field("id") String id);

    @POST("chat_user_end")//your api link
    @FormUrlEncoded
    Call<Object> EndChat(@Field("user_id") String user_id, @Field("chatlist_id") String chatlist_id);

    @POST("chat_user_detail")//your api link
    @FormUrlEncoded
    Call<UserdetailsExample> GetuserDetails(@Field("user_id") String user_id);

    @POST("chat_order_detail")//your api link
    @FormUrlEncoded
    Call<OrderDetailsExample> GetorderDetails(@Field("order_no") String order_no);

    @POST("user_profile")//your api link
    @FormUrlEncoded
    Call<UserdetailsExample> Getuserdetails(@Field("user_id") String user_id);

    @POST("chat_order_action")
    @FormUrlEncoded
    Call<Chatorderaction> ChatOrderAction(@Field("order_id") String order_id, @Field("des") String des, @Field("status") String status);

    @POST("Chat_new_complain")
    @FormUrlEncoded
    Call<ChatNewComplain> Chat_new_complain(@Field("user_id") String user_id, @Field("order_id") String order_id, @Field("order_no") String order_no, @Field("user_name") String user_name, @Field("mobile") String mobile, @Field("heading") String heading, @Field("des") String des);

    @POST("chat_old_complaint")
    @FormUrlEncoded
    Call<ChatComplainList> getComplane(@Field("user_id") String user_id);


    @POST("reFund")
    @FormUrlEncoded
    Call<Object> amountrefund(@Field("user_id") String user_id, @Field("order_id") String order_id);


    @POST("product_search")
    @FormUrlEncoded
    Call<OrderDetailsExample> user_product_search(@Field("user_id") String user_id, @Field("order_id") String order_id);


}
