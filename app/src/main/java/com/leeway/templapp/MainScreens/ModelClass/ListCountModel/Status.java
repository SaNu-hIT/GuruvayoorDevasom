
package com.leeway.templapp.MainScreens.ModelClass.ListCountModel;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("message")
    private String mMessage;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
