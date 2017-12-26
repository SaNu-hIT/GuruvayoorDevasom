package com.leeway.templapp.MainScreens.Activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.IDayItem;
import com.leeway.templapp.Connection.HttpRequestForGetEvents;
import com.leeway.templapp.Connection.Model.DrawableCalendarEvent;
import com.leeway.templapp.Connection.Model.ListSchedules.Sheduleinfo;
import com.leeway.templapp.Connection.Model.OnHttpRespoceForEvents;
import com.leeway.templapp.CustomClass.DrawableEventRenderer;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JobsListing extends BaseActivity implements OnHttpRespoceForEvents,CalendarPickerController {


    AgendaCalendarView mAgendaCalendarView;
    List<CalendarEvent> eventList;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_listing);
        mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);
        eventList = new ArrayList<>();

        sessionManager = new SessionManager(this);
        setProgressBar();
        showProgress("Please wait while processing...");
        HttpRequestForGetEvents httpRequestForGetEvents = new HttpRequestForGetEvents(this);
        httpRequestForGetEvents.getEvents("", sessionManager.getUserId());



    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    @Override
    public void onHttpEventSuccess(List<Sheduleinfo> eventsInfoList) {
        cancelProgress();
        eventList = new ArrayList<>();
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);
        mockList(eventList, eventsInfoList);
        try {
            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), JobsListing.this);

            mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHttpEventFailed(String message) {
        cancelProgress();

    }

    private void mockList(List<CalendarEvent> eventList, List<Sheduleinfo> eventsInfos) {

        // Example on how to provide your own layout
        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        startTime3.set(Calendar.HOUR_OF_DAY, 14);
        startTime3.set(Calendar.MINUTE, 0);
        endTime3.set(Calendar.HOUR_OF_DAY, 15);
        endTime3.set(Calendar.MINUTE, 0);

        if (eventsInfos.size() > 0) {
            for (int i = 0; i < eventsInfos.size(); i++) {
                String date = eventsInfos.get(i).getDate();
                SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
                Date dateObj = null;
                try {
                    dateObj = curFormater.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateObj);
                String illamName = eventsInfos.get(i).getIllamName();
                String jobname = eventsInfos.get(i).getJobname();
                DrawableCalendarEvent event3 = new DrawableCalendarEvent(illamName,jobname , "",
                        ContextCompat.getColor(this, R.color.colorOrange), calendar, calendar, false, "", "", "", "", "", "", "", "","");
                eventList.add(event3);

            }



        }
    }

    @Override
    public void onDaySelected(IDayItem dayItem) {

    }

    @Override
    public void onEventSelected(CalendarEvent event) {

    }

    @Override
    public void onScrollToDate(Calendar calendar) {

    }
}
