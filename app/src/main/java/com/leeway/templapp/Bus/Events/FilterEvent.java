package com.leeway.templapp.Bus.Events;

/**
 * Created by work on 7/31/2017.
 */

public class FilterEvent {
    private int eventTypeId;
    private String dateFromSend;
    private String dateToSend;
    public FilterEvent(int eventTypeId, String dateFromSend, String dateToSend) {
        this.dateFromSend=dateFromSend;
        this.eventTypeId=eventTypeId;
        this.dateToSend=dateToSend;
    }
    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getDateFromSend() {
        return dateFromSend;
    }

    public void setDateFromSend(String dateFromSend) {
        this.dateFromSend = dateFromSend;
    }

    public String getDateToSend() {
        return dateToSend;
    }

    public void setDateToSend(String dateToSend) {
        this.dateToSend = dateToSend;
    }


}
