
package com.leeway.templapp.Retrofit.LoginRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginStatus {

    @SerializedName("u_id")
    @Expose
    private Integer uId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("api_key")
    @Expose
    private String apiKey;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginStatus() {
    }

    /**
     * 
     * @param phone
     * @param name
     * @param uId
     * @param apiKey
     */
    public LoginStatus(Integer uId, String name, String phone, String apiKey) {
        super();
        this.uId = uId;
        this.name = name;
        this.phone = phone;
        this.apiKey = apiKey;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
