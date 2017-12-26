package com.leeway.templapp.Bus.Events;

/**
 * Created by work on 7/24/2017.
 */

public class DeleteEventDetailsEvent {
    private String eventId;

    public DeleteEventDetailsEvent(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
