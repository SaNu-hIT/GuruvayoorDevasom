
package com.leeway.templapp.Retrofit.ModelClass.HomeBean;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class HomeBea {

    @SerializedName("status")
    private com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO mINFO;
    @SerializedName("code")
    private Status mStatus;

    public com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO getINFO() {
        return mINFO;
    }

    public void setINFO(com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO INFO) {
        mINFO = INFO;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
