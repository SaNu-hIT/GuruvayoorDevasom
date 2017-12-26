
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Event {

    @SerializedName("error")
    private Boolean mError;
    @SerializedName("events_info")
    private List<EventsInfo> mEventsInfo;

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

}
