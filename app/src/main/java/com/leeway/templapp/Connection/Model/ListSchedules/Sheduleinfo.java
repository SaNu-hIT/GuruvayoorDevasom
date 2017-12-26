
package com.leeway.templapp.Connection.Model.ListSchedules;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Sheduleinfo {

    @SerializedName("date")
    private String mDate;
    @SerializedName("Illam_id")
    private Long mIllamId;
    @SerializedName("illam_name")
    private String mIllamName;
    @SerializedName("job_id")
    private Long mJobId;
    @SerializedName("jobname")
    private String mJobname;
    @SerializedName("sh_id")
    private Long mShId;
    @SerializedName("status")
    private Long mStatus;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Long getIllamId() {
        return mIllamId;
    }

    public void setIllamId(Long IllamId) {
        mIllamId = IllamId;
    }

    public String getIllamName() {
        return mIllamName;
    }

    public void setIllamName(String illamName) {
        mIllamName = illamName;
    }

    public Long getJobId() {
        return mJobId;
    }

    public void setJobId(Long jobId) {
        mJobId = jobId;
    }

    public String getJobname() {
        return mJobname;
    }

    public void setJobname(String jobname) {
        mJobname = jobname;
    }

    public Long getShId() {
        return mShId;
    }

    public void setShId(Long shId) {
        mShId = shId;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
