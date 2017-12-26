package com.leeway.templapp.Connection.Model;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;

import java.util.Calendar;

/**
 * Created by intellyelabs on 10/07/17.
 */

public class DrawableCalendarEvent extends BaseCalendarEvent {
    private String mDrawableId;

    // region Constructors


    public DrawableCalendarEvent(String title, String description, String location, int color, Calendar startTime, Calendar endTime, boolean allDay, String mDrawableId, String venue, String date, String time, String eventTitle, String eventVenue, String atendingValue, String eventId, String eventIcon) {
        super(title, description, location, color, startTime, endTime, allDay, venue, date, time, eventTitle, eventVenue, atendingValue, eventId,eventIcon);
        this.mDrawableId = mDrawableId;
    }

    public DrawableCalendarEvent(DrawableCalendarEvent calendarEvent) {
        super(calendarEvent);
        this.mDrawableId = calendarEvent.getDrawableId();
    }

    // endregion

    // region Public methods

    public String getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(String drawableId) {
        this.mDrawableId = drawableId;
    }

    // endregion

    // region Class - BaseCalendarEvent

    @Override
    public CalendarEvent copy() {
        return new DrawableCalendarEvent(this);
    }

    // endregion
}