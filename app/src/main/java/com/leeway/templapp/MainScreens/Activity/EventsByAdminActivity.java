package com.leeway.templapp.MainScreens.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.golovin.fluentstackbar.FluentSnackbar;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.Bus.Events.UpdateAgendaCalendarEvent;
import com.leeway.templapp.Connection.HttpRequestAttendingValue;
import com.leeway.templapp.Connection.OnHttpResponseAttendingValue;
import com.leeway.templapp.Customfont.TextViewRobotoRegular;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by work on 7/25/2017.
 */

public class EventsByAdminActivity extends Activity implements OnHttpResponseAttendingValue {
    @BindView(R.id.tv_toolbar_heading)
    TextViewRobotoRegular tv_toolbar_heading;
    @BindView(R.id.tv_title)
    TextViewRobotoRegular tv_Titile;
    @BindView(R.id.tv_Details)
    TextViewRobotoRegular tv_Details;
    @BindView(R.id.tv_Date)
    TextViewRobotoRegular tv_Date;
    @BindView(R.id.tv_Time)
    TextViewRobotoRegular tv_Time;
    @BindView(R.id.tv_Venue)
    TextViewRobotoRegular tv_Venue;

    SessionManager sessionManager;
    private FluentSnackbar mFluentSnackbar;
    private ProgressDialog progressBar;
    String eventId = "";
    @BindView(R.id.myRadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rb_Attend)
    RadioButton rb_Attend;
    @BindView(R.id.rb_noAttend)
    RadioButton rb_noAttend;
    @BindView(R.id.tv_attendedOrNot)
    TextView tv_attendedOrNot;
    @BindView(R.id.tv_ViewAtendedOrNot)
    TextView tv_ViewAtendedOrNot;
@BindView(R.id.img_eventIcon)
    CircleImageView img_eventIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_by_admin);

        ButterKnife.bind(this);
        BusFactory.getBus().register(this);
        mFluentSnackbar = FluentSnackbar.create(this);
        sessionManager = new SessionManager(getApplicationContext());

        setProgressBar();

        String headingName = getIntent().getExtras().getString("titleFunction", "");
        String Details = getIntent().getExtras().getString("Details", "");
        String date = getIntent().getExtras().getString("Date", "");
        String Time = getIntent().getExtras().getString("Time", "");
        String Venue = getIntent().getExtras().getString("Venue", "");
        String eventTitle = getIntent().getExtras().getString("eventTitle", "");
        String atendingValue = getIntent().getExtras().getString("atendingValue", "");
        String eventIcon = getIntent().getExtras().getString("eventIcon", "");
        eventId = getIntent().getExtras().getString("eventId", "");
        final int userId = Integer.parseInt(sessionManager.getUserId());
        Glide.with(getApplicationContext()).load(eventIcon)
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.ic_placeholder_no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_eventIcon);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate = null;

        String k = sdf.format(new Date());


        try {

            strDate = sdf.parse(date);
            if (new Date().before(strDate) || date.equals(k)) {
                if (atendingValue.equals("NA")) {
                    rb_Attend.setChecked(false);
                    rb_noAttend.setChecked(false);
                } else if (atendingValue.equals("Yes")) {
                    rb_Attend.setChecked(true);
                } else if (atendingValue.equals("No")) {
                    rb_noAttend.setChecked(true);
                }
            } else {

                radioGroup.setVisibility(View.GONE);
                tv_attendedOrNot.setVisibility(View.VISIBLE);
                if (atendingValue.equals("NA") || atendingValue.equals("No")) {
                    tv_ViewAtendedOrNot.setText("Status :");
                    tv_attendedOrNot.setText("Not attended");
                } else if (atendingValue.equals("Yes")) {
                    tv_attendedOrNot.setText("Attended");
                    tv_ViewAtendedOrNot.setText("Status :");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (isConnectedToNet(EventsByAdminActivity.this)) {
                    if (checkedId == R.id.rb_Attend) {

                        rb_Attend.setChecked(true);
                        AttendRequest(userId, Integer.parseInt(eventId), "Yes");

                    } else {

                        rb_noAttend.setChecked(true);
                        AttendRequest(userId, Integer.parseInt(eventId), "No");


                    }
                } else {
                    Toast.makeText(EventsByAdminActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tv_toolbar_heading.setText(headingName);
        tv_Titile.setText(eventTitle);
        tv_Details.setText(Details);
        tv_Date.setText(date);
        tv_Time.setText(Time);
        tv_Venue.setText(Venue);


    }

    private void AttendRequest(int userId, int eventId, String message) {
        showProgress("Please wait while processing...");
        HttpRequestAttendingValue httpRequestAttendingValue = new HttpRequestAttendingValue(this);
        httpRequestAttendingValue.AttendEvent(eventId, userId, message);
    }

    @OnClick(R.id.img_ButtonBack)
    public void NavigateBack(ImageView button) {

        finish();
    }

    @Override
    public void OnSuccessAttendingValue(boolean stautus, String message) {
        cancelProgress();
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();
        BusFactory.getBus().post(new UpdateAgendaCalendarEvent());

    }

    @Override
    public void OnFaildAttendingValue(String message) {
        cancelProgress();
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();
//        showAlert(message);
    }

    public boolean isConnectedToNet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }

    public void setProgressBar() {

        progressBar = new ProgressDialog(EventsByAdminActivity.this,
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

}
