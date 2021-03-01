package com.example.mydukaan.modal.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orders {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order_no")
    @Expose
    private String order_no;
    @SerializedName("product_id")
    @Expose
    private String product_id;
    @SerializedName("product_name")
    @Expose
    private String product_name;
    @SerializedName("product_img")
    @Expose
    private String product_img;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("order_total")
    @Expose
    private String order_total;
    @SerializedName("time")
    @Expose
    private String time;



    @SerializedName("actual_price")
    @Expose
    private String actual_price;
    @SerializedName("discounted_price")
    @Expose
    private String discounted_price;
    @SerializedName("delivery_caharge")
    @Expose
    private String delivery_caharge;
    @SerializedName("discount_amount")
    @Expose
    private String discount_amount;
    @SerializedName("paid_amount")
    @Expose
    private String paid_amount;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("coupon_code")
    @Expose
    private String coupon_code;
    @SerializedName("delivey_time")
    @Expose
    private String delivey_time;



    @SerializedName("coupon_code_amount")
    @Expose
    private String coupon_code_amount;
    @SerializedName("user_mobile")
    @Expose
    private String user_mobile;
    @SerializedName("delivey_status")
    @Expose
    private String delivey_status;
    @SerializedName("order_pre_total")
    @Expose
    private String order_pre_total;
    @SerializedName("sold_price")
    @Expose
    private String sold_price;
    @SerializedName("payment_mode")
    @Expose
    private String payment_mode;

    private boolean check_box = false;

    public boolean isCheck_box() {
        return check_box;
    }

    public void setCheck_box(boolean check_box) {
        this.check_box = check_box;
    }

    public boolean isSelect_box() {
        return select_box;
    }

    public void setSelect_box(boolean select_box) {
        this.select_box = select_box;
    }

    public  boolean select_box=false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrder_total() {
        return order_total;
    }

    public void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getDelivery_caharge() {
        return delivery_caharge;
    }

    public void setDelivery_caharge(String delivery_caharge) {
        this.delivery_caharge = delivery_caharge;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getDelivey_time() {
        return delivey_time;
    }

    public void setDelivey_time(String delivey_time) {
        this.delivey_time = delivey_time;
    }

    public String getCoupon_code_amount() {
        return coupon_code_amount;
    }

    public void setCoupon_code_amount(String coupon_code_amount) {
        this.coupon_code_amount = coupon_code_amount;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getDelivey_status() {
        return delivey_status;
    }

    public void setDelivey_status(String delivey_status) {
        this.delivey_status = delivey_status;
    }

    public String getOrder_pre_total() {
        return order_pre_total;
    }

    public void setOrder_pre_total(String order_pre_total) {
        this.order_pre_total = order_pre_total;
    }

    public String getSold_price() {
        return sold_price;
    }

    public void setSold_price(String sold_price) {
        this.sold_price = sold_price;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    @SerializedName("cash")
    @Expose
    private String cash;
}
