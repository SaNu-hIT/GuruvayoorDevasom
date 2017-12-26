
package com.leeway.templapp.MainScreens.ModelClass.ListCountModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCountModel {

    @SerializedName("Quantity")
    private List<com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity> mQuantity;
    @SerializedName("status")
    private Status mStatus;
    @SerializedName("Summary")
    private List<Summary> summary;



    public List<com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity> getQuantity() {
        return mQuantity;
    }

    public void setQuantity(List<com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity> Quantity) {
        mQuantity = Quantity;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }





    public List<Summary> getSummary() {
        return summary;
    }

    public void setSummary(List<Summary> summary) {
        this.summary = summary;
    }



}
