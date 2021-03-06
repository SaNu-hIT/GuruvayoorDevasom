
package com.leeway.templapp.Connection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ListNewsDetailsModel {

    @SerializedName("status")
    private Boolean mError;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("news_list")
    private List<NewsList> mNewsList;

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<NewsList> getNewsList() {
        return mNewsList;
    }

    public void setNewsList(List<NewsList> newsList) {
        mNewsList = newsList;
    }

}
