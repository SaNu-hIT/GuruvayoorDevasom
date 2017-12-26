
package com.leeway.templapp.Retrofit.LoginRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModule {

    @SerializedName("code")
    @Expose
    private LoginCode code;
    @SerializedName("status")
    @Expose
    private LoginStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginModule() {
    }

    /**
     * 
     * @param status
     * @param code
     */
    public LoginModule(LoginCode code, LoginStatus status) {
        super();
        this.code = code;
        this.status = status;
    }

    public LoginCode getCode() {
        return code;
    }

    public void setCode(LoginCode code) {
        this.code = code;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

}
