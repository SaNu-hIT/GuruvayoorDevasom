
package com.leeway.templapp.MainScreens.ModelClass.ListCountModel;
import com.google.gson.annotations.SerializedName;
public class Quantity {

    @SerializedName("c_date")
    private String mCDate;
    @SerializedName("c_quantity")
    private Long mCQuantity;
    @SerializedName("name")
    private String mName;
    @SerializedName("Remark")
    private String mRemark;
    @SerializedName("userId")
    private Long mUserId;

    public String getCDate() {
        return mCDate;
    }

    public void setCDate(String cDate) {
        mCDate = cDate;
    }

    public Long getCQuantity() {
        return mCQuantity;
    }

    public void setCQuantity(Long cQuantity) {
        mCQuantity = cQuantity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String Remark) {
        mRemark = Remark;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }
}
