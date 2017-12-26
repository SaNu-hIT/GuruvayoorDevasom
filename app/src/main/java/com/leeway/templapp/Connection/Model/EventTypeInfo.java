
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EventTypeInfo {

    @SerializedName("event_icon")
    private String mEventIcon;
    @SerializedName("event_Type_id")
    private String mEventTypeId;
    @SerializedName("event_type_name")
    private String mEventTypeName;

    public String getEventIcon() {
        return mEventIcon;
    }

    public void setEventIcon(String eventIcon) {
        mEventIcon = eventIcon;
    }

    public String getEventTypeId() {
        return mEventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        mEventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return mEventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        mEventTypeName = eventTypeName;
    }

}
