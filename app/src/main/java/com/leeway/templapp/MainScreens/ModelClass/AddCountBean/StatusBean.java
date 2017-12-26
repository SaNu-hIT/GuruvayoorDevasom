
package com.leeway.templapp.MainScreens.ModelClass.AddCountBean;

import com.google.gson.annotations.SerializedName;


public class StatusBean {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
