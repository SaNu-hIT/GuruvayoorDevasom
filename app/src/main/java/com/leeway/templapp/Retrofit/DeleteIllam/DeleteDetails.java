
package com.leeway.templapp.Retrofit.DeleteIllam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteDetails {

    @SerializedName("status")
    @Expose
    private DeleteStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DeleteDetails() {
    }

    /**
     * 
     * @param status
     */
    public DeleteDetails(DeleteStatus status) {
        super();
        this.status = status;
    }

    public DeleteStatus getStatus() {
        return status;
    }

    public void setStatus(DeleteStatus status) {
        this.status = status;
    }

}
