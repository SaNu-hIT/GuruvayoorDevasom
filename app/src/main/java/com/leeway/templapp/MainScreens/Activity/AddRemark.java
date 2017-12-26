package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.RemarkModel.RemarkStatus;

import com.leeway.templapp.Connection.Model.RemarkModel.Status;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AddRemark extends AppCompatActivity {
    EditText remark;
    SessionManager sessionManager;

    ArrayList<String> nameSpinnerList;
    ArrayList<String> nameSpinnerIdList;
    private String userid;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_remark);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
ButterKnife.bind(this);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        sessionManager = new SessionManager(this);
        initiaize();

    }


    private void initiaize() {

        remark = (EditText) findViewById(R.id.description);

    }


    public void cancel(View view) {
        onBackPressed();
    }

    public void SaveCount(View view) {


    }


    private void AddRemar(String remark, String userid,String date) {
        Log.e( "AddRemar: ",""+remark+""+userid+""+date);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<RemarkStatus> call1 = apiService.AddRemarkNew(remark, userid,date);
        call1.enqueue(new Callback<RemarkStatus>() {
            @Override
            public void onResponse(Call<RemarkStatus> call, retrofit2.Response<RemarkStatus> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                progress.dismiss();
                System.out.println("Retrofimessafe : " + response.message());

                if (response.message().equals("OK")) {

                    Status status = response.body().getStatus();
                    String sts = status.getCode().toString();
                    if (sts.equals("200")) {
                        String message = status.getMessage().toString();
                        progress.cancel();
                        new SweetAlertDialog(AddRemark.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success?")
                                .setContentText("" + message)
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                        clearAll();
                                    }
                                })
                                .show();
//                        Toast.makeText(AddCount.this, message, Toast.LENGTH_SHORT).show();


                    } else {
                        String message = status.getMessage().toString();
                        Toast.makeText(AddRemark.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddRemark.this, "server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RemarkStatus> call, Throwable t) {
                Log.i("SHANIL ", "Call Failed" + call + "  ");
                t.printStackTrace();
                progress.cancel();
                Toast.makeText(AddRemark.this, "error", Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void clearAll() {

        remark.setText("");
        userid = "";


    }

    private boolean validate() {
        boolean flag = false;


        if (!remark.getText().equals("")) {
            flag = true;

            remark.setError(null);
        } else {
            remark.setError("Please enter remark");

            flag = false;
        }

        Log.e( "flag: ",""+flag );

        return flag;
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }


    public void SaveRemark(View view) {

        if (validate()) {
            progress.show();
            String userid = sessionManager.getUserId();
            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);
            AddRemar(remark.getText().toString(), userid,thisDate);

        }
    }
}
