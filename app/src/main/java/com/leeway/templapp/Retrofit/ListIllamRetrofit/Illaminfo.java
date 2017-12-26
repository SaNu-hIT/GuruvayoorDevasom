
package com.leeway.templapp.Retrofit.ListIllamRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Illaminfo {

    @SerializedName("Illam_id")
    @Expose
    private Integer illamId;
    @SerializedName("illam_name")
    @Expose
    private String illamName;
    @SerializedName("illam_address")
    @Expose
    private String illamAddress;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Illaminfo() {
    }

    /**
     * 
     * @param illamAddress
     * @param illamId
     * @param illamName
     */
    public Illaminfo(Integer illamId, String illamName, String illamAddress) {
        super();
        this.illamId = illamId;
        this.illamName = illamName;
        this.illamAddress = illamAddress;
    }

    public Integer getIllamId() {
        return illamId;
    }

    public void setIllamId(Integer illamId) {
        this.illamId = illamId;
    }

    public String getIllamName() {
        return illamName;
    }

    public void setIllamName(String illamName) {
        this.illamName = illamName;
    }

    public String getIllamAddress() {
        return illamAddress;
    }

    public void setIllamAddress(String illamAddress) {
        this.illamAddress = illamAddress;
    }

}
