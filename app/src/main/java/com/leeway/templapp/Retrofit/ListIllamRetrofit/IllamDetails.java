
package com.leeway.templapp.Retrofit.ListIllamRetrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IllamDetails {

    @SerializedName("code")
    @Expose
    private IllamCode code;
    @SerializedName("illaminfo")
    @Expose
    private List<Illaminfo> illaminfo = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public IllamDetails() {
    }

    /**
     * 
     * @param illaminfo
     * @param code
     */
    public IllamDetails(IllamCode code, List<Illaminfo> illaminfo) {
        super();
        this.code = code;
        this.illaminfo = illaminfo;
    }

    public IllamCode getCode() {
        return code;
    }

    public void setCode(IllamCode code) {
        this.code = code;
    }

    public List<Illaminfo> getIllaminfo() {
        return illaminfo;
    }

    public void setIllaminfo(List<Illaminfo> illaminfo) {
        this.illaminfo = illaminfo;
    }

}
