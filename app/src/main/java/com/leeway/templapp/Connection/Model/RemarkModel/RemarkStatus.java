
package com.leeway.templapp.Connection.Model.RemarkModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RemarkStatus {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
