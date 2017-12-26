
package com.leeway.templapp.Connection.Model.News.Model_Two;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ViewEventsModel {

    @SerializedName("error")
    private Boolean mError;
    @SerializedName("events_info")
    private List<EventsInfo> mEventsInfo;
    @SerializedName("message")
    private String mMessage;

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public List<EventsInfo> getEventsInfo() {
        return mEventsInfo;
    }

    public void setEventsInfo(List<EventsInfo> eventsInfo) {
        mEventsInfo = eventsInfo;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
