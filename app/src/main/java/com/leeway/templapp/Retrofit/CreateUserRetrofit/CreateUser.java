
package com.leeway.templapp.Retrofit.CreateUserRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUser {

    @SerializedName("status")
    @Expose
    private CreateStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CreateUser() {
    }

    /**
     * 
     * @param status
     */
    public CreateUser(CreateStatus status) {
        super();
        this.status = status;
    }

    public CreateStatus getStatus() {
        return status;
    }

    public void setStatus(CreateStatus status) {
        this.status = status;
    }

}
