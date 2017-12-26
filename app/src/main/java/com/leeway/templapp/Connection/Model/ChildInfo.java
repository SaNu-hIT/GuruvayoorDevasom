
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ChildInfo {

    @SerializedName("dob")
    private String mDob;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("FullName")
    private String mFullName;
    @SerializedName("mobile_no")
    private String mMobileNo;
    @SerializedName("name")
    private String mName;
    @SerializedName("office_no")
    private String mOfficeNo;
    @SerializedName("SpouseImageUrl")
    private String mSpouseImageUrl;
    @SerializedName("title")
    private String mTitle;

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOfficeNo() {
        return mOfficeNo;
    }

    public void setOfficeNo(String officeNo) {
        mOfficeNo = officeNo;
    }

    public String getSpouseImageUrl() {
        return mSpouseImageUrl;
    }

    public void setSpouseImageUrl(String SpouseImageUrl) {
        mSpouseImageUrl = SpouseImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
