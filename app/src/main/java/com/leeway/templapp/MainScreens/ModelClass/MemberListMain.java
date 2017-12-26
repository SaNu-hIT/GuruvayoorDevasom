
package com.leeway.templapp.MainScreens.ModelClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class MemberListMain {

    @SerializedName("code")
    private Code mCode;
    @SerializedName("userinfo")
    private List<Userinfo> mUserinfo;

    public Code getCode() {
        return mCode;
    }

    public void setCode(Code code) {
        mCode = code;
    }

    public List<Userinfo> getUserinfo() {
        return mUserinfo;
    }

    public void setUserinfo(List<Userinfo> userinfo) {
        mUserinfo = userinfo;
    }

}
