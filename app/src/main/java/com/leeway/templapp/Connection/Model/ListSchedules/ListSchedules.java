
package com.leeway.templapp.Connection.Model.ListSchedules;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ListSchedules {

    @SerializedName("sheduleinfo")
    private List<Sheduleinfo> mSheduleinfo;
    @SerializedName("status")
    private Status mStatus;

    public List<Sheduleinfo> getSheduleinfo() {
        return mSheduleinfo;
    }

    public void setSheduleinfo(List<Sheduleinfo> sheduleinfo) {
        mSheduleinfo = sheduleinfo;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
