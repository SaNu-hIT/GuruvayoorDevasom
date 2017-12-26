
package com.leeway.templapp.Retrofit.ModelClass.AddIllamModel;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ResponceCommon {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
