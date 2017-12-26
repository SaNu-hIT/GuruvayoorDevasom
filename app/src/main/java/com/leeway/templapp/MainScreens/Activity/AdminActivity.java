package com.leeway.templapp.MainScreens.Activity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leeway.templapp.Connection.Model.HttpRequestForGetAll;
import com.leeway.templapp.Connection.OnHttpRespoceForAll;
import com.leeway.templapp.MainScreens.Activity.NewsAdmin.AddNewsActivity;
import com.leeway.templapp.MainScreens.Activity.NewsAdmin.ViewNewsActivity;
import com.leeway.templapp.R;
import com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO;
import com.leeway.templapp.SessionManager.SessionManager;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdminActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnHttpRespoceForAll {
    SessionManager sessionManager;
    @BindView(R.id.month_count)
    TextView month_count;
    @BindView(R.id.total_count)
    TextView total_count;
    @BindView(R.id.user_cont)
    TextView user_cont;
    @BindView(R.id.illam_count)
    TextView illam_count;
    @BindView(R.id.date_month)
    TextView date_month;


    LinearLayout sandel, jobs, news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Guruvayur Devaswom");
//showProgress("Loading");
        getDetails();


        DateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("MMMM");
            Date date = new Date();
            Log.d("Month",dateFormat.format(date));
            date_month.setText(dateFormat.format(date));


        }

        sessionManager = new SessionManager(this);
        sandel = (LinearLayout) findViewById(R.id.sandel);
        jobs = (LinearLayout) findViewById(R.id.jobs);
        news = (LinearLayout) findViewById(R.id.news);

        sandel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListCount.class);
                startActivity(intent);
            }
        });
        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), JobsListing.class);
                startActivity(intent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ViewNewsActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getDetails() {
        HttpRequestForGetAll httpRequestForGetAll = new HttpRequestForGetAll(this);
        httpRequestForGetAll.getallRespons();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.switchss) {


            Intent intent = new Intent(getBaseContext(), UserSide.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent intent = new Intent(getBaseContext(), AddIllam.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getBaseContext(), AddMembers_Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(getBaseContext(), Members.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getBaseContext(), ListAllIllam.class);
            startActivity(intent);

        } else if (id == R.id.Remarks_id) {
            Intent intent = new Intent(getBaseContext(), ListRemark.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Log.e("onNav", "logout");


            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setCancelText("No")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();

                        }
                    })
                    .setConfirmText("Yes")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            sessionManager.ClearAll();
                            Intent intent = new Intent(getBaseContext(), Login_Page.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .show();

//            sessionManager.ClearAll();
//            Intent intent=new Intent(getBaseContext(),Login_Page.class);
//            startActivity(intent);
//            finish();


        }
//        else if (id == R.id.nav_send) {
//
//
//            Intent intent = new Intent(getBaseContext(), ContactUs.class);
//            startActivity(intent);
//        }
        else if (id == R.id.add_news) {


            Intent intent = new Intent(getBaseContext(), AddNewsActivity.class);
            startActivity(intent);
        } else if (id == R.id.usersview) {


            Intent intent = new Intent(getBaseContext(), UserSide.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onHttpEventSuccess(INFO info) {

        String member = info.getUsers().toString();
        String illam = info.getIllam().toString();
        String current = info.getCurrentWorkAssigned().toString();
        String total = info.getTotalWorkAssigned().toString();
        month_count.setText(current);
        total_count.setText(total);
        user_cont.setText(member);
        illam_count.setText(illam);
//        cancelProgress();


    }

    @Override
    public void onHttpEventFailed(String message) {
//        cancelProgress();

    }
}
