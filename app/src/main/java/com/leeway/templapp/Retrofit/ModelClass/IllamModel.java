
package com.leeway.templapp.Retrofit.ModelClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class IllamModel {

    @SerializedName("code")
    private Code mCode;
    @SerializedName("illaminfo")
    private List<Illaminfo> mIllaminfo;

    public Code getCode() {
        return mCode;
    }

    public void setCode(Code code) {
        mCode = code;
    }

    public List<Illaminfo> getIllaminfo() {
        return mIllaminfo;
    }

    public void setIllaminfo(List<Illaminfo> illaminfo) {
        mIllaminfo = illaminfo;
    }

}
