package com.github.tibolte.agendacalendarview.models;

import java.util.Calendar;

public interface CalendarEvent {


    void setPlaceholder(boolean placeholder);

    boolean isPlaceholder();

    public String getLocation();

    public void setLocation(String mLocation);

    long getId();

    void setId(long mId);

    Calendar getStartTime();

    void setStartTime(Calendar mStartTime);

    Calendar getEndTime();

    void setEndTime(Calendar mEndTime);

    String getTitle();

    void setTitle(String mTitle);

    String getDes();
String geteventIcon();
void  seteventIcon(String eventIcon);
    void setDes(String des);

    String getTime();

    void setTime(String time);
    String getEventId();

    void setEventId(String eventId);
    String getDate();

    void setDate(String date);

    String getEventTitle();

    void setEventTitle(String eventTitle);

    String getVenue();

    void setVenue(String attendingMember);

    String getAttendingMember();

    void setAttendingMember(String attendingMember);

    Calendar getInstanceDay();

    void setInstanceDay(Calendar mInstanceDay);

    IDayItem getDayReference();

    void setDayReference(IDayItem mDayReference);

    IWeekItem getWeekReference();

    void setWeekReference(IWeekItem mWeekReference);

    CalendarEvent copy();
}
