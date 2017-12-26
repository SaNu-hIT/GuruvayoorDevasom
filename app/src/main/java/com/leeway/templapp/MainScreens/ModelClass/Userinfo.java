
package com.leeway.templapp.MainScreens.ModelClass;

import com.google.gson.annotations.SerializedName;

public class Userinfo {

    @SerializedName("Illam_id")
    private Long mIllamId;
    @SerializedName("illam_name")
    private String mIllamName;
    @SerializedName("mobile")
    private String mMobile;
    @SerializedName("name")
    private String mName;
    @SerializedName("roleId")
    private Long mRoleId;
    @SerializedName("userId")
    private Long mUserId;

    public Long getIllamId() {
        return mIllamId;
    }

    public void setIllamId(Long IllamId) {
        mIllamId = IllamId;
    }

    public String getIllamName() {
        return mIllamName;
    }

    public void setIllamName(String illamName) {
        mIllamName = illamName;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getRoleId() {
        return mRoleId;
    }

    public void setRoleId(Long roleId) {
        mRoleId = roleId;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}
