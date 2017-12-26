
package com.leeway.templapp.Connection.Model.News.Model_Two;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EventsInfo {

    @SerializedName("event_date")
    private String mEventDate;
    @SerializedName("event_description")
    private String mEventDescription;
    @SerializedName("event_icon")
    private String mEventIcon;
    @SerializedName("Event_id")
    private String mEventId;
    @SerializedName("event_time")
    private String mEventTime;
    @SerializedName("event_title")
    private String mEventTitle;
    @SerializedName("Event_type_id")
    private String mEventTypeId;
    @SerializedName("event_type_name")
    private String mEventTypeName;
    @SerializedName("event_venue")
    private String mEventVenue;

    public String getEventDate() {
        return mEventDate;
    }

    public void setEventDate(String eventDate) {
        mEventDate = eventDate;
    }

    public String getEventDescription() {
        return mEventDescription;
    }

    public void setEventDescription(String eventDescription) {
        mEventDescription = eventDescription;
    }

    public String getEventIcon() {
        return mEventIcon;
    }

    public void setEventIcon(String eventIcon) {
        mEventIcon = eventIcon;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String EventId) {
        mEventId = EventId;
    }

    public String getEventTime() {
        return mEventTime;
    }

    public void setEventTime(String eventTime) {
        mEventTime = eventTime;
    }

    public String getEventTitle() {
        return mEventTitle;
    }

    public void setEventTitle(String eventTitle) {
        mEventTitle = eventTitle;
    }

    public String getEventTypeId() {
        return mEventTypeId;
    }

    public void setEventTypeId(String EventTypeId) {
        mEventTypeId = EventTypeId;
    }

    public String getEventTypeName() {
        return mEventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        mEventTypeName = eventTypeName;
    }

    public String getEventVenue() {
        return mEventVenue;
    }

    public void setEventVenue(String eventVenue) {
        mEventVenue = eventVenue;
    }

}
