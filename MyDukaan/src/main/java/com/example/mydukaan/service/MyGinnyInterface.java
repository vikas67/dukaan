package com.example.mydukaan.service;

import com.example.mydukaan.ginnymodal.category.CreateCategoryMarket;
import com.example.mydukaan.ginnymodal.signin.SignInGinney;
import com.example.mydukaan.ginnymodal.signup.SignupGinney;
import com.example.mydukaan.ginnymodal.verifyotp.VerifyOtp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface MyGinnyInterface {

    @POST("api/auth/signin")
    @FormUrlEncoded
    Call<SignInGinney> SignINGinny(@Field("mobile") String mobile, @Field("password") String password);

    @POST("api/auth/send_otp")
    @FormUrlEncoded
    Call<SignupGinney> SignUpGinny(@Field("name") String name, @Field("email") String email, @Field("mobile") String mobile, @Field("password") String password, @Field("user_type") String user_type);

    @POST("api/auth/verify_otp")
    @FormUrlEncoded
    Call<VerifyOtp> verifyOtp(@Field("user_id") String user_id, @Field("otp") String otp);

    @POST("api/auth/resend")
    @FormUrlEncoded
    Call<VerifyOtp> resendOtp(@Field("user_id") String user_id, @Field("otp") String otp);

    @POST("api/auth/resend")
    @FormUrlEncoded
    Call<CreateCategoryMarket> getMarketCategory();






}
