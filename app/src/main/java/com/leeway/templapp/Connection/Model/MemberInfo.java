
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MemberInfo {

    @SerializedName("FullName")
    private String mFullName;
    @SerializedName("mobile_no")
    private String mMobileNo;

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String FullName) {
        mFullName = FullName;
    }

    public String getMobileNo() {
        return mMobileNo;
    }

    public void setMobileNo(String mobileNo) {
        mMobileNo = mobileNo;
    }

}
