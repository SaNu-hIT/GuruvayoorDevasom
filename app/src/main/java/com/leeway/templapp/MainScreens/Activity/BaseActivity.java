package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.golovin.fluentstackbar.FluentSnackbar;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by work on 7/21/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private FluentSnackbar mFluentSnackbar;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusFactory.getBus().register(this);
        mFluentSnackbar = FluentSnackbar.create(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);
    }
    public boolean isConnectedToNet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }
    public String ConvertDateFormat(String dateToConvert) {

        String myFormat = "EEE, MMM d, yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateFormat originalFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }
    public void setProgressBar() {

        progressBar = new ProgressDialog(this,
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
    public void showAlert(String message) {
        mFluentSnackbar.create(message)
                .maxLines(2) // default is 1 line
                .backgroundColorRes(R.color.red_btn_bg_color) // default is #323232
                .textColorRes(R.color.colorBackroundWhite) // default is Color.WHITE
                .duration(Snackbar.LENGTH_SHORT) // default is Snackbar.LENGTH_LONG
                .actionTextColorRes(R.color.colorAccent)
                .important()
                .show();
    }
    public void showAlertSucces(String message) {
        mFluentSnackbar.create(message)
                .maxLines(2) // default is 1 line
                .backgroundColorRes(R.color.colorsuccessGreen) // default is #323232
                .textColorRes(R.color.colorBackroundWhite) // default is Color.WHITE
                .duration(Snackbar.LENGTH_SHORT) // default is Snackbar.LENGTH_LONG
                .actionTextColorRes(R.color.colorAccent)
                .important()
                .show();
    }
}
