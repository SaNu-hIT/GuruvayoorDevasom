package com.leeway.templapp.MainScreens.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.IDayItem;
import com.leeway.templapp.Bus.Events.UpdateAgendaCalendarEvent;
import com.leeway.templapp.Connection.HttpRequestForGetEvents;
import com.leeway.templapp.Connection.Model.DrawableCalendarEvent;
import com.leeway.templapp.Connection.Model.EventsInfo;
import com.leeway.templapp.Connection.Model.ListSchedules.Sheduleinfo;
import com.leeway.templapp.Connection.Model.OnHttpRespoceForEvents;
import com.leeway.templapp.Connection.OnfragmentInteractionListner;
import com.leeway.templapp.CustomClass.DrawableEventRenderer;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;
import com.squareup.otto.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedsFragment extends BaseFragment implements OnHttpRespoceForEvents, CalendarPickerController {
    public static final String ARG_PAGE = "ARG_PAGE";
    RecyclerView recyclerView;
    AgendaCalendarView mAgendaCalendarView;
    List<CalendarEvent> eventList;
    private OnfragmentInteractionListner mListener;

    SessionManager sessionManager;
    private ProgressDialog progressBar;

    public FeedsFragment() {
        // Required empty public constructor
    }

    public static FeedsFragment newInstance(int pageNo) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        FeedsFragment fragment = new FeedsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onFragmentInteractionParam("Feeds");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.feeds, container, false);
//        BusFactory.getBus().register(this);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAgendaCalendarView = (AgendaCalendarView) rootview.findViewById(R.id.agenda_calendar_view);
        eventList = new ArrayList<>();


        if (mListener != null) {
            mListener.onFragmentInteractionParam("SCHEDULE");
        }

        sessionManager = new SessionManager(getContext());
        setProgressBar();
        showProgress("Please wait while processing...");
        HttpRequestForGetEvents httpRequestForGetEvents = new HttpRequestForGetEvents(this);
        httpRequestForGetEvents.getEvents("", sessionManager.getUserId());


        // Inflate the layout for this fragment
        return rootview;


    }

    public void setProgressBar() {

        progressBar = new ProgressDialog(getActivity(),
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setCancelable(false);

        progressBar.cancel();

    }


    public void showProgress(String message) {
        progressBar.setMessage(message);
        progressBar.show();
    }

    public void cancelProgress() {
        progressBar.cancel();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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
            mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
            mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void SetDataToCalenerList(List<EventsInfo> eventsInfoList) {

    }

    @Override
    public void onHttpEventFailed(String message) {
        cancelProgress();
        Toast.makeText(getActivity(), message + "", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mListener = (OnfragmentInteractionListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }


    @Override
    public void onDaySelected(IDayItem dayItem) {

    }

    @Override
    public void onEventSelected(CalendarEvent event) {
//        int eventId = Integer.parseInt(event.getEventId());
//        if (eventId == -1) {
//            Intent intent = new Intent(getActivity(), EventsActivity.class);
//            intent.putExtra("titleFunction", event.getTitle());
//            intent.putExtra("Details", event.getDes());
//            intent.putExtra("Date", event.getDate());
//            intent.putExtra("Time", event.getTime());
//            intent.putExtra("Venue", event.getVenue());
//            intent.putExtra("eventTitle", event.getEventTitle());
//            intent.putExtra("atendingValue", event.getAttendingMember());
//            intent.putExtra("eventId", event.getEventId());
//            intent.putExtra("date", event.getDate());
//            intent.putExtra("eventIcon", event.geteventIcon());
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(getActivity(), EventsByAdminActivity.class);
//            intent.putExtra("titleFunction", event.getTitle());
//            intent.putExtra("Details", event.getDes());
//            intent.putExtra("Date", event.getDate());
//            intent.putExtra("Time", event.getTime());
//            intent.putExtra("Venue", event.getVenue());
//            intent.putExtra("eventTitle", event.getEventTitle());
//            intent.putExtra("atendingValue", event.getAttendingMember());
//            intent.putExtra("eventId", event.getEventId());
//            intent.putExtra("date", event.getDate());
//            intent.putExtra("eventIcon", event.geteventIcon());
//            startActivity(intent);
//        }
    }

    @Override
    public void onScrollToDate(Calendar calendar) {

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
                                ContextCompat.getColor(getActivity(), R.color.colorOrange), calendar, calendar, false, "", "", "", "", "", "", "", "","");
                        eventList.add(event3);

            }



        }
    }

    @Subscribe
    public void ErrorNull(UpdateAgendaCalendarEvent event) {

        HttpRequestForGetEvents httpRequestForGetEvents = new HttpRequestForGetEvents(this);
        httpRequestForGetEvents.getEvents("", sessionManager.getUserId());

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
