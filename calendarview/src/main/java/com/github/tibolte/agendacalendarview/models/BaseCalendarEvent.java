package com.github.tibolte.agendacalendarview.models;

import java.util.Calendar;

/**
 * Event model class containing the information to be displayed on the agenda view.
 */
public class BaseCalendarEvent implements CalendarEvent {

    /**
     * Id of the event.
     */
    private long mId;
    /**
     * Color to be displayed in the agenda view.
     */
    private int mColor;
    /**
     * Title of the event.
     */
    private String mTitle;
    /**
     * Description of the event.
     */
    private String mDescription;
    /**
     * Where the event takes place.
     */
    private String mLocation;
    /**
     * Calendar instance helping sorting the events per section in the agenda view.
     */
    private Calendar mInstanceDay;
    /**
     * Start time of the event.
     */
    private Calendar mStartTime;
    /**
     * End time of the event.
     */
    private Calendar mEndTime;
    /**
     * Indicates if the event lasts all day.
     */
    private boolean mAllDay;
    /**
     * Tells if this BaseCalendarEvent instance is used as a placeholder in the agenda view, if there's
     * no event for that day.
     */
    private boolean mPlaceHolder;
    /**
     * Tells if this BaseCalendarEvent instance is used as a forecast information holder in the agenda
     * view.
     */
    private boolean mWeather;
    /**
     * Duration of the event.
     */
    private String mDuration;
    private String eventVenue;
    private String date;
    private String time;
    private String atendingValue;
    private String eventTitle;
    private String eventId;
    private String eventIcon;
    /**
     * References to a DayItem instance for that event, used to link interaction between the
     * calendar view and the agenda view.
     */
    private IDayItem mDayReference;
    /**
     * References to a WeekItem instance for that event, used to link interaction between the
     * calendar view and the agenda view.
     */
    private IWeekItem mWeekReference;
    /**
     * Weather icon string returned by the Dark Sky API.
     */
    private String mWeatherIcon;
    /**
     * Temperature value returned by the Dark Sky API.
     */
    private double mTemperature;

    // region Constructor

    public BaseCalendarEvent() {

    }


    public BaseCalendarEvent(BaseCalendarEvent calendarEvent) {
        this.mId = calendarEvent.getId();
        this.mColor = calendarEvent.getColor();
        this.mAllDay = calendarEvent.isAllDay();
        this.mDuration = calendarEvent.getDuration();
        this.mTitle = calendarEvent.getTitle();
        this.mDescription = calendarEvent.getDescription();
        this.mLocation = calendarEvent.getLocation();
        this.mStartTime = calendarEvent.getStartTime();
        this.mEndTime = calendarEvent.getEndTime();
        this.time = calendarEvent.getTime();
        this.date = calendarEvent.getDate();
        this.eventVenue = calendarEvent.getVenue();
        this.eventTitle = calendarEvent.getEventTitle();
        this.atendingValue=calendarEvent.getAttendingMember();
        this.eventId=calendarEvent.getEventId();
        this.eventIcon=calendarEvent.geteventIcon();
    }

    public BaseCalendarEvent(String title, String description, String location, int color, Calendar startTime, Calendar endTime, boolean allDay, String venue, String date, String time, String eventTitle, String eventVenue, String atendingValue, String eventId, String eventIcon) {

        this.mTitle = title;
        this.mDescription = description;
        this.mLocation = location;
        this.mColor = color;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mAllDay = allDay;
        this.date = date;
        this.time = time;
        this.eventTitle = eventTitle;
        this.eventVenue = eventVenue;
        this.atendingValue = atendingValue;
        this.eventId = eventId;
        this.eventIcon = eventIcon;
    }

    // endregion

    // region Getters/Setters

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isAllDay() {
        return mAllDay;
    }

    public void setAllDay(boolean allDay) {
        this.mAllDay = allDay;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Calendar getInstanceDay() {
        return mInstanceDay;
    }

    public void setInstanceDay(Calendar mInstanceDay) {
        this.mInstanceDay = mInstanceDay;
        this.mInstanceDay.set(Calendar.HOUR, 0);
        this.mInstanceDay.set(Calendar.MINUTE, 0);
        this.mInstanceDay.set(Calendar.SECOND, 0);
        this.mInstanceDay.set(Calendar.MILLISECOND, 0);
        this.mInstanceDay.set(Calendar.AM_PM, 0);
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Calendar mEndTime) {
        this.mEndTime = mEndTime;
    }

    public void setPlaceholder(boolean placeholder) {
        mPlaceHolder = placeholder;
    }

    public boolean isPlaceholder() {
        return mPlaceHolder;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public Calendar getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Calendar mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String getDes() {
        return mDescription;
    }

    @Override
    public String geteventIcon() {
        return eventIcon;
    }

    @Override
    public void seteventIcon(String eventIcon) {
this.eventIcon=eventIcon;
    }

    @Override
    public void setDes(String des) {
        this.mDescription = des;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(String eventId) {
this.eventId=eventId;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getEventTitle() {
        return eventTitle;
    }

    @Override
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Override
    public String getVenue() {
        return eventVenue;
    }

    @Override
    public void setVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    @Override
    public String getAttendingMember() {
        return atendingValue;
    }

    @Override
    public void setAttendingMember(String atendingValue) {
        this.atendingValue = atendingValue;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public boolean isPlaceHolder() {
        return mPlaceHolder;
    }

    public void setPlaceHolder(boolean mPlaceHolder) {
        this.mPlaceHolder = mPlaceHolder;
    }

    public boolean isWeather() {
        return mWeather;
    }

    public void setWeather(boolean mWeather) {
        this.mWeather = mWeather;
    }

    public IDayItem getDayReference() {
        return mDayReference;
    }

    public void setDayReference(IDayItem mDayReference) {
        this.mDayReference = mDayReference;
    }

    public IWeekItem getWeekReference() {
        return mWeekReference;
    }

    public void setWeekReference(IWeekItem mWeekReference) {
        this.mWeekReference = mWeekReference;
    }

    public String getWeatherIcon() {
        return mWeatherIcon;
    }

    public void setWeatherIcon(String mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    @Override
    public CalendarEvent copy() {
        return new BaseCalendarEvent(this);
    }

    // endregion

    @Override
    public String toString() {
        return "BaseCalendarEvent{"
                + "title='"
                + mTitle
                + ", instanceDay= "
                + mInstanceDay.getTime()
                + "}";
    }
}
