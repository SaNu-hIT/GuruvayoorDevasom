package com.leeway.templapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leeway.templapp.Adapter.CalendarListAdapter;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Codmob on 17-06-16.
 */
@SuppressWarnings("ALL")
public class CalendarNew extends BaseActivity /*implements ConnectivityChangeListener*/ {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private final List<String> events_creator_type = new ArrayList<>();
    private final List<String> events_content = new ArrayList<>();
    private ListView listViewevnts;
    TextView contenttextvew;
    private TextView princitxt;
    Dialog dlg;
    Dialog dlg2;
    private CalendarListAdapter mAdapter;
    ListAdapter adapter;
    private ProgressBar progressBar;
    private DrawerArrowDrawable drawerArrowDrawable;
    private float offset;
    private boolean flipped;
    private String parentPh, s_Id;
    ImageView Calendaricon;
    String createdtype;
    public int CalMonth, CalYear;
    private final ArrayList<HashMap<String, String>> eventslist = new ArrayList<>();
    private RecyclerView mRecyclerView;
    List<String> calendar_item_creator_type = new ArrayList<>();
    List<String> calendar_item_content = new ArrayList<>();
    List<String> calendar_item_created_by = new ArrayList<>();
    private Activity activity;
    public int CurrMonth, CurrYear;


    private static final String EVENTCONTENT = "content";

    private String dateevnt, dateevnt2;
    int flag = 0;

    /**
     * Setting color for specific dates
     */
    private void setCustomResourceForDates() {


        Date blueDate = Constants.d;
        Date currentDate = new Date();
        System.out.println("ELDHO  : " + blueDate);

        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {

            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable cyan = new ColorDrawable(Color.CYAN);
                caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);

                caldroidFragment.setTextColorForDate(R.color.white, blueDate);

            }

        }

    }

    /**
     * Setting color for specific dates
     */
    private void setStudentForDates() {

        Date blueDate = Constants.d;

        Date currentDate = new Date();
        System.out.println("DATE 2 : " + blueDate);

        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {


            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable cyan = new ColorDrawable(Color.CYAN);
                caldroidFragment.setBackgroundDrawableForDate(green, blueDate);

                caldroidFragment.setTextColorForDate(R.color.white, blueDate);

            }
        }
    }

    /**
     * Setting color for specific dates
     */
    private void setBothForDates() {

        Date blueDate = Constants.d;

        Date currentDate = new Date();
        System.out.println("DATE 3 : " + blueDate);

        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {


            if (caldroidFragment != null) {


                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable cyan = new ColorDrawable(Color.CYAN);
                caldroidFragment.setBackgroundDrawableForDate(cyan, blueDate);
                caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            }
        }
    }

    /**
     * Setting color for Holiday
     */
    private void setHolidays() {

        Date blueDate = Constants.d;
        System.out.println("Eldhoss: BlueDate: " + blueDate);

        Date currentDate = new Date();


        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {


            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable Gray = new ColorDrawable(Color.WHITE);
                caldroidFragment.setBackgroundDrawableForDate(Gray, blueDate);
                caldroidFragment.setTextColorForDate(R.color.caldroid_light_red, blueDate);
            }
        }
    }

    /**
     * Setting color for Teacher Holiday
     */
    private void setTeacherHolidays() {
        Date blueDate = Constants.d;

        Date currentDate = new Date();


        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {

            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable Gray = new ColorDrawable(Color.WHITE);
                caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);
                caldroidFragment.setTextColorForDate(R.color.caldroid_light_red, blueDate);
            }
        }
    }

    /**
     * Setting color for Principal Holidays
     */
    private void setPrinciHolidays() {

        Date blueDate = Constants.d;
        Date currentDate = new Date();


        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {


            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable Gray = new ColorDrawable(Color.WHITE);
                caldroidFragment.setBackgroundDrawableForDate(green, blueDate);

                caldroidFragment.setTextColorForDate(R.color.caldroid_light_red, blueDate);

            }
        }
    }

    /**
     * Setting color for Both Holidays
     */
    private void setBothHolidays() {

        Date blueDate = Constants.d;

        Date currentDate = new Date();


        if (currentDate.getDate() == blueDate.getDate()) {

            System.out.println("Eldhoss : Helloww");
            if (caldroidFragment != null) {
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.duskYellow));
                caldroidFragment.setBackgroundDrawableForDate(drawable, currentDate);
            }

        } else {


            if (caldroidFragment != null) {
                ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
                ColorDrawable green = new ColorDrawable(getResources().getColor(R.color.green));
                ColorDrawable Gray = new ColorDrawable(Color.CYAN);
                caldroidFragment.setBackgroundDrawableForDate(Gray, blueDate);
                caldroidFragment.setTextColorForDate(R.color.caldroid_light_red, blueDate);
            }
        }
    }

    //    Call<CalendarPhp> call1=null;
//    Call<CalendarPhp> call2=null;
//    Call<DateClick> call3=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar);

/*

        activity = this;
        progressBar= (ProgressBar) findViewById(R.id.progbar);
        progressBar.setVisibility(View.VISIBLE);
        findViewById(R.id.ErrorLayoutnew).setVisibility(View.GONE);




        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Calendar");


        SharedPreferences prefs = getSharedPreferences("CampusWallet", MODE_PRIVATE);
        parentPh = prefs.getString("PhoneNo", "");
        s_Id= prefs.getString("studentID", "");


        float density = getResources().getDisplayMetrics().density;
        System.out.println("CODMOB:Screensize "+ density);


        Calendar c = Calendar.getInstance();
        CurrYear = c.get(Calendar.YEAR);
        CurrMonth = c.get(Calendar.MONTH)+1;
        Defcalendarevnts();
        System.out.println("CurrYear "+CurrYear);
        System.out.println("CurrMonth "+CurrMonth);

        princitxt=(TextView)findViewById(R.id.TvCalendarContent);

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        Date currentDate = new Date();
        System.out.println("Eldhoss: Date: "+currentDate);

        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            caldroidFragment.setArguments(args);
        }



        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                dateevnt=formatter.format(date);
                dateevnt2=formatter2.format(date);

                eventslist.clear();
                progressBar.setVisibility(View.VISIBLE);
                calendarevntContents();

            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                System.out.println("onChangeMonth  "+text);
                try{
                    System.out.println("Monthcal  "+month);
                    CalMonth=month;
                    CalYear=year;
                    System.out.println("HereCurrentMonth "+CalMonth);
                }catch (Exception e){
                    System.out.println("ExceptionHere "+e.toString());
                }
                calendarevnts();


            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated()
            {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };



        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        final TextView textView = (TextView) findViewById(R.id.textview);

        final Button customizeButton = (Button) findViewById(R.id.customize_button);

        // Customize the calendar
        customizeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (undo) {
                    customizeButton.setText("customize");
                    textView.setText("");

                    // Reset calendar
                    caldroidFragment.clearDisableDates();
                    caldroidFragment.clearSelectedDates();
                    caldroidFragment.setMinDate(null);
                    caldroidFragment.setMaxDate(null);
                    caldroidFragment.setShowNavigationArrows(true);
                    caldroidFragment.setEnableSwipe(true);
                    caldroidFragment.refreshView();
                    undo = false;
                    return;
                }

                // Else
                undo = true;
                customizeButton.setText("Undo");
                Calendar cal = Calendar.getInstance();

                // Min date is last 7 days
                cal.add(Calendar.DATE, -7);
                Date minDate = cal.getTime();

                // Max date is next 7 days
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 14);
                Date maxDate = cal.getTime();

                // Set selected dates
                // From Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 2);
                Date fromDate = cal.getTime();

                // To Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 3);
                Date toDate = cal.getTime();

                // Set disabled dates
                ArrayList<Date> disabledDates = new ArrayList<>();
                for (int i = 5; i < 8; i++) {
                    cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, i);
                    disabledDates.add(cal.getTime());
                }

                // Customize
                caldroidFragment.setMinDate(minDate);
                caldroidFragment.setMaxDate(maxDate);
                caldroidFragment.setDisableDates(disabledDates);
                caldroidFragment.setSelectedDates(fromDate, toDate);
                caldroidFragment.setShowNavigationArrows(false);
                caldroidFragment.setEnableSwipe(false);

                caldroidFragment.refreshView();

                // Move to date
                // cal = Calendar.getInstance();
                // cal.add(Calendar.MONTH, 12);
                // caldroidFragment.moveToDate(cal.getTime());

                String text = "Today: " + formatter.format(new Date()) + "\n";
                text += "Min Date: " + formatter.format(minDate) + "\n";
                text += "Max Date: " + formatter.format(maxDate) + "\n";
                text += "Select From Date: " + formatter.format(fromDate)
                        + "\n";
                text += "Select To Date: " + formatter.format(toDate) + "\n";
                for (Date date : disabledDates) {
                    text += "Disabled Date: " + formatter.format(date) + "\n";
                }

                textView.setText(text);
            }
        });

        Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);

        final Bundle state = savedInstanceState;
        showDialogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Setup caldroid to use as dialog
                dialogCaldroidFragment = new CaldroidFragment();
                dialogCaldroidFragment.setCaldroidListener(listener);

                // If activity is recovered from rotation
                final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
                if (state != null) {
                    dialogCaldroidFragment.restoreDialogStatesFromKey(
                            getSupportFragmentManager(), state,
                            "DIALOG_CALDROID_SAVED_STATE", dialogTag);
                    Bundle args = dialogCaldroidFragment.getArguments();
                    if (args == null) {
                        args = new Bundle();
                        dialogCaldroidFragment.setArguments(args);
                    }
                } else {
                    // Setup arguments
                    Bundle bundle = new Bundle();
                    // Setup dialogTitle
                    dialogCaldroidFragment.setArguments(bundle);
                }
                try { dialogCaldroidFragment.show(getSupportFragmentManager(),
                        dialogTag);
                }catch (Exception e){

                }

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences("CampusWallet", Context.MODE_PRIVATE);
        s_Id=sharedpreferences.getString("studentID", "");
        parentPh=sharedpreferences.getString("PhoneNo", "");
        System.out.println("SharedPrefStudeny "+sharedpreferences.getString("studentID", ""));
        System.out.println("SharedPrefStudeny "+sharedpreferences.getString("PhoneNo", ""));
        // put your code here...

    }
    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onBackPressed() {


        if(call1!=null){

            call1.cancel();
        }

        if(call2!=null){

            call2.cancel();
        }

        if(call3!=null){

            call3.cancel();
        }

        super.onBackPressed();

    }

    */
/**
 * To get all events from calendar
 *//*

    private void calendarevnts()
    {

        System.out.println("SHANIL1 : ");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        call1=apiService.getcalendar("r_calender.php",parentPh,s_Id,CalYear,CalMonth);

        call1.enqueue(new Callback<CalendarPhp>() {
            @Override
            public void onResponse(Call<CalendarPhp> call1, retrofit2.Response<CalendarPhp> response) {
                System.out.println("SHANIL2 : "+response.body().getStatus());
                progressBar.setVisibility(View.GONE);
                CalendarStatus status=response.body().getStatus();
                String code=status.getCode();
                if (code.equals("200")){
                    System.out.println("SHANIL3 : ");
                    Log.i("shanil ", String.valueOf(response.body().getStatus()));
                    List<CalendarCode> relationship=response.body().getCode();

                    if (relationship.size()>0) {
                        System.out.println("SHANIL4 : ");
                        FragmentTransaction t1 = getSupportFragmentManager().beginTransaction();
                        t1.replace(R.id.calendar1, caldroidFragment);
                        t1.commitAllowingStateLoss();
                        for (int i = 0; i < relationship.size(); i++) {
                            System.out.println("SHANIL5 : ");
                            CalendarCode relncode=relationship.get(i);
                            String date = relncode.getMessageDueDate();
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                System.out.println("SHANIL6 : ");
                                Constants.d = simpleDate.parse(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            String event=relncode.getType();

                            System.out.println("EVENT : "+event);

                            if (event.equals("1")) {
                                System.out.println("SHANIL7 : ");
                                System.out.println("CODMOB: success");

                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success ");
                                    System.out.println("SHANIL8 : ");
                                    setCustomResourceForDates();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            } else if (event.equals("2")) {
                                System.out.println("SHANIL9 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success ");

                                    setStudentForDates();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            } else if (event.equals("3")) {
                                System.out.println("SHANIL10 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success ");
                                    System.out.println("SHANIL11 : ");
                                    setBothForDates();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            } else if (event.equals("4")) {
                                System.out.println("SHANIL12 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success ");

                                    setHolidays();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            }else if (event.equals("5")) {
                                System.out.println("SHANIL13 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success type 5");

                                    setTeacherHolidays();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            }else if (event.equals("6")) {
                                System.out.println("SHANIL14 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success  type 6");

                                    setPrinciHolidays();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            }else if (event.equals("7")) {
                                System.out.println("SHANIL15 : ");
                                if (caldroidFragment != null) {
                                    System.out.println("CODMOB: if success type 7 ");
                                    System.out.println("SHANIL16 : ");
                                    setBothHolidays();

                                    // Attach to the activity
                                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                    t.replace(R.id.calendar1, caldroidFragment);
                                    t.commitAllowingStateLoss();
                                }
                            }

                        }
                    }else {
                        System.out.println("SHANIL17 : ");
                        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                        t.replace(R.id.calendar1, caldroidFragment);
                        t.commitAllowingStateLoss();
                        progressBar.setVisibility(View.GONE);
                    }

                }else if(code.equals("400")){

                }else if (code.equals("204")){

                }else if (code.equals("401")){


                }else if (code.equals("500")){



                }
            }

            @Override
            public void onFailure(Call<CalendarPhp> call, Throwable t) {

                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);

            }
        });


    }


    */
/**
 *
 *//*

    private void Defcalendarevnts()
    {
        System.out.println("SHANIL18 : ");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


         call2=apiService.getcalendar("r_calender.php",parentPh,s_Id,CurrYear,CurrMonth);
         call2.enqueue(new Callback<CalendarPhp>() {
                         @Override
                         public void onResponse(Call<CalendarPhp> call, retrofit2.Response<CalendarPhp> response) {
                             System.out.println("SHANIL19 : ");

                             String URL=call.request().url().toString();
                             System.out.println("Retrofit URL : "+URL);

                             progressBar.setVisibility(View.GONE);
                             CalendarStatus status=response.body().getStatus();
                             System.out.println("SHANIL STATUS : "+status);
                             String code=status.getCode();
                             if (code.equals("200"))
                             {
                                 System.out.println("SHANIL20 : ");
                                 Log.i("shanil ", String.valueOf(response.body().getStatus()));
                                 List<CalendarCode> relationship=response.body().getCode();
                                 if (relationship.size()>0) {
                                     System.out.println("SHANIL21 : ");
                                     for (int i = 0; i < relationship.size(); i++) {
                                         System.out.println("SHANIL22 : ");
                                         CalendarCode relncode=relationship.get(i);
                                         String date = relncode.getMessageDueDate();
                                         @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                                         System.out.println("SHANIL DATE : "+date);
                                         try {
                                             System.out.println("SHANIL23 : ");
                                             Constants.d = simpleDate.parse(date);
                                         } catch (ParseException e) {
                                             e.printStackTrace();
                                         }
                                         String event=relncode.getType();
                                         System.out.println("SHANIL24 : ");
                                         if (event.equals("1")) {
                                             System.out.println("SHANIL25 : ");
                                             System.out.println("CODMOB: success");

                                             if (caldroidFragment != null) {
                                                 System.out.println("SHANIL26 : ");
                                                 System.out.println("CODMOB: if success ");

                                                 setCustomResourceForDates();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         } else if (event.equals("2")) {
                                             System.out.println("SHANIL27 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("CODMOB: if success ");
                                                 System.out.println("SHANIL28 : ");
                                                 setStudentForDates();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         } else if (event.equals("3")) {
                                             System.out.println("SHANIL29 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("SHANIL30 : ");
                                                 System.out.println("CODMOB: if success ");

                                                 setBothForDates();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         } else if (event.equals("4")) {
                                             System.out.println("SHANIL30 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("CODMOB: if success ");
                                                 System.out.println("SHANIL31 : ");
                                                 setHolidays();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         }else if (event.equals("5")) {
                                             System.out.println("SHANIL32 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("CODMOB: if success type 5");
                                                 System.out.println("SHANIL33 : ");
                                                 setTeacherHolidays();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         }else if (event.equals("6")) {
                                             System.out.println("SHANIL34 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("CODMOB: if success  type 6");
                                                 System.out.println("SHANIL35 : ");
                                                 setPrinciHolidays();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         }else if (event.equals("7")) {
                                             System.out.println("SHANIL36 : ");
                                             if (caldroidFragment != null) {
                                                 System.out.println("CODMOB: if success type 7 ");
                                                 System.out.println("SHANIL37 : ");
                                                 setBothHolidays();

                                                 // Attach to the activity
                                                 FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                                 t.replace(R.id.calendar1, caldroidFragment);
                                                 t.commitAllowingStateLoss();
                                             }
                                         }


                                     }
                                 }else {
                                     System.out.println("SHANIL38 : ");
                                     FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                                     t.replace(R.id.calendar1, caldroidFragment);
                                     t.commitAllowingStateLoss();
                                     progressBar.setVisibility(View.GONE);
                                 }

                             }else if(code.equals("400")){

                             }else if (code.equals("204")){


                             }else if (code.equals("401")){


                             }else if (code.equals("500")){



                             }

                         }

                         @Override
                         public void onFailure(Call<CalendarPhp> call, Throwable t) {
                             Log.i("SHANIL ","Call Failed"+call+ "  ");
                             t.printStackTrace();
                             progressBar.setVisibility(View.GONE);

                         }
                     });


    }




    */
/**
 * To get specific messages
 *//*

    private void calendarevntContents()
    {


        dlg2 = new Dialog(this);
        dlg2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg2.setContentView(R.layout.eventpopup);
        dlg2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView header = (TextView) dlg2.findViewById(R.id.header);
        mRecyclerView = (RecyclerView) dlg2.findViewById(R.id.eventlistviewid);
        header.setText(dateevnt2);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        call3=apiService.getevents("r_message_by_date.php",parentPh,s_Id,dateevnt);


        call3.enqueue(new Callback<DateClick>() {
            @Override
            public void onResponse(Call<DateClick> call, retrofit2.Response<DateClick> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);


                progressBar.setVisibility(View.GONE);
                DateClickStatus status=response.body().getStatus();
                String code=status.getCode();
                if (code.equals("200")){
                    events_content.clear();
                    events_creator_type.clear();
                    Log.i("shanil ", String.valueOf(response.body().getStatus()));
                    List<DateClickCode> relationship=response.body().getCode();
                    if (relationship.size()>0) {
                        for (int i = 0; i < relationship.size(); i++) {

                            DateClickCode relncode = relationship.get(i);

                            events_content.add(relncode.getMessageContent());
                            events_creator_type.add(relncode.getMessageCreatorType());

                            progressBar.setVisibility(View.GONE);
                            try {
                                dlg2.show();
                            }catch (Exception e){

                            }
                        }
                        if (events_content.size() > 0) {
                            mRecyclerView.setVisibility(View.VISIBLE);
                            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mAdapter = new CalendarListAdapter(events_content, events_creator_type,dateevnt2, activity, getApplicationContext());
                            mRecyclerView.setAdapter(mAdapter);
                            try {
                                dlg2.show();
                            }catch (Exception e){

                            }

                        } else {
//                            Toast.makeText(activity, "No Data to display", Toast.LENGTH_SHORT).show();
                            mRecyclerView.setVisibility(View.GONE);
//                            findViewById(R.id.ErrorLayoutnew).setVisibility(View.VISIBLE);
                        }
                    }else {
                        dlg2.dismiss();
                        String Msg=status.getMessage();
//                        Toast.makeText(CalendarNew.this, "  "+Msg, Toast.LENGTH_SHORT).show();

                        mRecyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
//                        findViewById(R.id.ErrorLayoutnew).setVisibility(View.VISIBLE);
                    }

                }else if(code.equals("400")){

                }else if (code.equals("204")){


                }else if (code.equals("401")){

                }else if (code.equals("500")){



                }
            }

            @Override
            public void onFailure(Call<DateClick> call, Throwable t) {

                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                dlg2.dismiss();


            }
        });


    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }

        if (dialogCaldroidFragment != null) {
            dialogCaldroidFragment.saveStatesToKey(outState,
                    "DIALOG_CALDROID_SAVED_STATE");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

*/
    }
}