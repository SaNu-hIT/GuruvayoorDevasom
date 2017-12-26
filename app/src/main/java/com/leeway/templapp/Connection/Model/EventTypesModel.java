
package com.leeway.templapp.Connection.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EventTypesModel {

    @SerializedName("error")
    private Boolean mError;
    @SerializedName("Event_Type_info")
    private List<EventTypeInfo> mEventTypeInfo;
    @SerializedName("message")
    private String mMessage;

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public List<EventTypeInfo> getEventTypeInfo() {
        return mEventTypeInfo;
    }

    public void setEventTypeInfo(List<EventTypeInfo> EventTypeInfo) {
        mEventTypeInfo = EventTypeInfo;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
