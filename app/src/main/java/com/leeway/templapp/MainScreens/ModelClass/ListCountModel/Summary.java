package com.leeway.templapp.MainScreens.ModelClass.ListCountModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

    @SerializedName("monthsummary")
    @Expose
    private Integer monthsummary;
    @SerializedName("DailySummary")
    @Expose
    private Integer dailySummary;

    public Integer getMonthsummary() {
        return monthsummary;
    }

    public void setMonthsummary(Integer monthsummary) {
        this.monthsummary = monthsummary;
    }

    public Integer getDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(Integer dailySummary) {
        this.dailySummary = dailySummary;
    }

}