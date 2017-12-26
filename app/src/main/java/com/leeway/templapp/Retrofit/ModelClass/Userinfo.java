
package com.leeway.templapp.Retrofit.ModelClass;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Userinfo {

    @SerializedName("Illam_id")
    private Long mIllamId;
    @SerializedName("name")
    private String mName;
    @SerializedName("mobile")
    private Long mPhone;
    @SerializedName("roleId")
    private Long mRole;
    @SerializedName("userId")
    private Long mUId;

    public Long getIllamId() {
        return mIllamId;
    }

    public void setIllamId(Long illamId) {
        mIllamId = illamId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getPhone() {
        return mPhone;
    }

    public void setPhone(Long phone) {
        mPhone = phone;
    }

    public Long getRole() {
        return mRole;
    }

    public void setRole(Long role) {
        mRole = role;
    }

    public Long getUId() {
        return mUId;
    }

    public void setUId(Long uId) {
        mUId = uId;
    }

}
