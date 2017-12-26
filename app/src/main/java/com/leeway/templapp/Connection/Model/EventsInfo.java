
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EventsInfo {

    @SerializedName("date")
    private String mDate;
    @SerializedName("event_list")
    private List<EventList> mEventList;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<EventList> getEventList() {
        return mEventList;
    }

    public void setEventList(List<EventList> eventList) {
        mEventList = eventList;
    }

}
