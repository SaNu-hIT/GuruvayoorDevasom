
package com.leeway.templapp.Connection.Model.ListRemarkModel;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RemarkModelForList {

    @SerializedName("Remarkinfo")
    private List<com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo> mRemarkinfo;
    @SerializedName("status")
    private Status mStatus;

    public List<com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo> getRemarkinfo() {
        return mRemarkinfo;
    }

    public void setRemarkinfo(List<com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo> Remarkinfo) {
        mRemarkinfo = Remarkinfo;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
