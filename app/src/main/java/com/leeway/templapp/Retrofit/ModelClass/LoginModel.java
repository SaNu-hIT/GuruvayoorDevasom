
package com.leeway.templapp.Retrofit.ModelClass;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LoginModel {

    @SerializedName("status")
    private Status mStatus;
    @SerializedName("code")
    private Userinfo mUserinfo;

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public Userinfo getUserinfo() {
        return mUserinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        mUserinfo = userinfo;
    }

}
