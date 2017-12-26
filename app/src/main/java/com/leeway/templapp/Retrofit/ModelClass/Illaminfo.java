
package com.leeway.templapp.Retrofit.ModelClass;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Illaminfo {

    @SerializedName("illam_address")
    private String mIllamAddress;
    @SerializedName("Illam_id")
    private Long mIllamId;
    @SerializedName("illam_name")
    private String mIllamName;

    public String getIllamAddress() {
        return mIllamAddress;
    }

    public void setIllamAddress(String illamAddress) {
        mIllamAddress = illamAddress;
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

}
