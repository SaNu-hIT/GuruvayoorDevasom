
package com.leeway.templapp.Connection.Model.ListRemarkModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Remarkinfo {

    @SerializedName("Content")
    private String mContent;
    @SerializedName("name")
    private String mName;
    @SerializedName("r_date")
    private String mRDate;
    @SerializedName("remark_id")
    private Long mRemarkId;
    @SerializedName("userId")
    private Long mUserId;

    public String getContent() {
        return mContent;
    }

    public void setContent(String Content) {
        mContent = Content;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRDate() {
        return mRDate;
    }

    public void setRDate(String rDate) {
        mRDate = rDate;
    }

    public Long getRemarkId() {
        return mRemarkId;
    }

    public void setRemarkId(Long remarkId) {
        mRemarkId = remarkId;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}
