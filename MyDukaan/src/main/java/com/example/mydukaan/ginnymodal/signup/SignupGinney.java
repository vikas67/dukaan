
package com.example.mydukaan.ginnymodal.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupGinney {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
