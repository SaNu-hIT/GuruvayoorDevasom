
package com.leeway.templapp.Retrofit.DeleteIllam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteStatus {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeleteStatus() {
    }

    /**
     * 
     * @param message
     * @param code
     */
    public DeleteStatus(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
