
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ContactInfo {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("Child_Info")
    private List<ChildInfo> mChildInfo;
    @SerializedName("Contact_Id")
    private String mContactId;
    @SerializedName("contact_info")
    private List<ContactInfo> mContactInfo;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("error")
    private Boolean mError;
    @SerializedName("FullName")
    private String mFullName;
    @SerializedName("FullnameOf_spouse")
    private String mFullnameOfSpouse;
    @SerializedName("ismarried")
    private String mIsmarried;
    @SerializedName("MemberImageUrl")
    private String mMemberImageUrl;
    @SerializedName("mobile_no")
    private String mMobileNo;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameOf_spouse")
    private String mNameOfSpouse;
    @SerializedName("office_no")
    private String mOfficeNo;
    @SerializedName("spouse_dob")
    private String mSpouseDob;
    @SerializedName("spouse_email")
    private String mSpouseEmail;
    @SerializedName("SpouseImageUrl")
    private String mSpouseImageUrl;
    @SerializedName("spouse_mobileNo")
    private String mSpouseMobileNo;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("title_for_spouse")
    private String mTitleForSpouse;
    @SerializedName("weeding_date")
    private String mWeedingDate;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public List<ChildInfo> getChildInfo() {
        return mChildInfo;
    }

    public void setChildInfo(List<ChildInfo> ChildInfo) {
        mChildInfo = ChildInfo;
    }

    public String getContactId() {
        return mContactId;
    }

    public void setContactId(String ContactId) {
        mContactId = ContactId;
    }

    public List<ContactInfo> getContactInfo() {
        return mContactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        mContactInfo = contactInfo;
    }

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

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String FullName) {
        mFullName = FullName;
    }

    public String getFullnameOfSpouse() {
        return mFullnameOfSpouse;
    }

    public void setFullnameOfSpouse(String FullnameOfSpouse) {
        mFullnameOfSpouse = FullnameOfSpouse;
    }

    public String getIsmarried() {
        return mIsmarried;
    }

    public void setIsmarried(String ismarried) {
        mIsmarried = ismarried;
    }

    public String getMemberImageUrl() {
        return mMemberImageUrl;
    }

    public void setMemberImageUrl(String MemberImageUrl) {
        mMemberImageUrl = MemberImageUrl;
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

    public String getNameOfSpouse() {
        return mNameOfSpouse;
    }

    public void setNameOfSpouse(String nameOfSpouse) {
        mNameOfSpouse = nameOfSpouse;
    }

    public String getOfficeNo() {
        return mOfficeNo;
    }

    public void setOfficeNo(String officeNo) {
        mOfficeNo = officeNo;
    }

    public String getSpouseDob() {
        return mSpouseDob;
    }

    public void setSpouseDob(String spouseDob) {
        mSpouseDob = spouseDob;
    }

    public String getSpouseEmail() {
        return mSpouseEmail;
    }

    public void setSpouseEmail(String spouseEmail) {
        mSpouseEmail = spouseEmail;
    }

    public String getSpouseImageUrl() {
        return mSpouseImageUrl;
    }

    public void setSpouseImageUrl(String SpouseImageUrl) {
        mSpouseImageUrl = SpouseImageUrl;
    }

    public String getSpouseMobileNo() {
        return mSpouseMobileNo;
    }

    public void setSpouseMobileNo(String spouseMobileNo) {
        mSpouseMobileNo = spouseMobileNo;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitleForSpouse() {
        return mTitleForSpouse;
    }

    public void setTitleForSpouse(String titleForSpouse) {
        mTitleForSpouse = titleForSpouse;
    }

    public String getWeedingDate() {
        return mWeedingDate;
    }

    public void setWeedingDate(String weedingDate) {
        mWeedingDate = weedingDate;
    }

}
