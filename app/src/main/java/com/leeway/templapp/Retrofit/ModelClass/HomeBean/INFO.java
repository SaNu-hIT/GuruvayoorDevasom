
package com.leeway.templapp.Retrofit.ModelClass.HomeBean;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class INFO {

    @SerializedName("CurrentWorkAssigned")
    private Long mCurrentWorkAssigned;
    @SerializedName("Illam")
    private Long mIllam;
    @SerializedName("TotalWorkAssigned")
    private Long mTotalWorkAssigned;
    @SerializedName("Users")
    private Long mUsers;

    public Long getCurrentWorkAssigned() {
        return mCurrentWorkAssigned;
    }

    public void setCurrentWorkAssigned(Long CurrentWorkAssigned) {
        mCurrentWorkAssigned = CurrentWorkAssigned;
    }

    public Long getIllam() {
        return mIllam;
    }

    public void setIllam(Long Illam) {
        mIllam = Illam;
    }

    public Long getTotalWorkAssigned() {
        return mTotalWorkAssigned;
    }

    public void setTotalWorkAssigned(Long TotalWorkAssigned) {
        mTotalWorkAssigned = TotalWorkAssigned;
    }

    public Long getUsers() {
        return mUsers;
    }

    public void setUsers(Long Users) {
        mUsers = Users;
    }

}
