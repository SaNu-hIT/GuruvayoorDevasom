
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MemberBean {

    @SerializedName("contact_info")
    private List<ContactInfo> mContactInfo;
    @SerializedName("error")
    private Boolean mError;

    public List<ContactInfo> getContactInfo() {
        return mContactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        mContactInfo = contactInfo;
    }

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

}
