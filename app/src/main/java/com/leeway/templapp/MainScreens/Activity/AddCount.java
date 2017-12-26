package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.MainScreens.ModelClass.AddCountBean.Status;
import com.leeway.templapp.MainScreens.ModelClass.AddCountBean.StatusBean;
import com.leeway.templapp.MainScreens.ModelClass.Code;
import com.leeway.templapp.MainScreens.ModelClass.MemberListMain;
import com.leeway.templapp.MainScreens.ModelClass.Userinfo;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class AddCount extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
EditText date,count,remark;
SessionManager sessionManager;
    MaterialSpinner selelct_person;
    ArrayList<String> nameSpinnerList;
    ArrayList<String> nameSpinnerIdList;
    private String userid;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_count);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getMembers();
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        selelct_person= (MaterialSpinner) findViewById(R.id.selelct_person);
        nameSpinnerList=new ArrayList<>();
nameSpinnerIdList=new ArrayList<>();
        selelct_person.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                userid=nameSpinnerIdList.get(position);
            }
        });

        sessionManager=new SessionManager(this);
initiaize();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ShowDate();
            }
        });
    }

    public  void ShowDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                AddCount.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }


//        SimpleDateFormat formats = new SimpleDateFormat("dd-MM-yyyy");


//
//        new SingleDateAndTimePickerDialog.Builder(this)
////                .bottomSheet()
////                .curved()
////                .minutesStep(15)
//
//                .displayHours(false)
//                .displayMinutes(false)
//
//.setDayFormatter(formats)
//
//                .title("Simple")
//                .listener(new SingleDateAndTimePickerDialog.Listener() {
//                    @Override
//                    public void onDateSelected(Date dates) {
//
//                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy  hh:mm a");
//                        String event = format.format(dates);
//                        Log.e("onDateSelected: ", "" + event);
//
//                        String datevalue = event.substring(0, 10);
//                        String timevalue = event.substring(12, 20);
//                        Log.e("onDateSelected: ", "datevalue" + datevalue);
//                        Log.e("onDateSelected: ", "timevalue" + timevalue);
//
//                        date.setText(datevalue);
//
//
//
//                    }
//                }).display();
//        SimpleDateFormat forma= new SimpleDateFormat("dd-MM-yyyy");
//        new SingleDateAndTimePickerDialog.Builder(this)
//                .bottomSheet()
//
//                .curved()
//                .displayDays(true)
//                .displayHours(false)
//                .displayMinutes(false)
//
//.mustBeOnFuture()
//                .setDayFormatter(formats)
//
//                .backgroundColor(Color.GREEN)
//                .mainColor(Color.BLACK)
//          .titleTextColor(Color.WHITE)
//
//
//                .listener(new SingleDateAndTimePickerDialog.Listener() {
//                    @Override
//                    public void onDateSelected(Date dates) {
//
//
//                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//                        String event = format.format(dates);
//                        Log.e("onDateSelected: ", "" + event);
//
//                        String datevalue = event.substring(0, 10);
////                        String timevalue = event.substring(12, 20);
////                        Log.e("onDateSelected: ", "datevalue" + datevalue);
////                        Log.e("onDateSelected: ", "timevalue" + timevalue);
//
//                        date.setText(datevalue);
//
//
//
//
//
//                    }
//                }).display();
//    }

    private void initiaize() {
        date= (EditText) findViewById(R.id.date);
        count= (EditText) findViewById(R.id.quantity);
        remark= (EditText) findViewById(R.id.description);

    }


    public void cancel(View view) {
        onBackPressed();
    }

    public void SaveCount(View view) {

        if(validate())
        {
progress.show();
AddCount(date.getText().toString(),count.getText().toString(),remark.getText().toString(),userid);

        }
    }


    private void AddCount(String date,String count,String remark,String userid) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


      Call<StatusBean>  call1=apiService.AddCount(date,count,remark,userid);
        call1.enqueue(new Callback<StatusBean>() {
            @Override
            public void onResponse(Call<StatusBean> call, retrofit2.Response<StatusBean> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                System.out.println("Retrofimessafe : " + response.message());

                if(response.message().equals("OK"))
                {

                    Status status = response.body().getStatus();
                    String sts = status.getCode().toString();
                    if (sts.equals("200")) {
                        String message = status.getMessage().toString();
                        progress.cancel();
                        new SweetAlertDialog(AddCount.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success?")
                                .setContentText(""+message)
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
                        Toast.makeText(AddCount.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddCount.this, "server error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StatusBean> call, Throwable t) {
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(AddCount.this, "Create UserFailed", Toast.LENGTH_SHORT).show();
            }




        });



    }

    private void clearAll() {
        date.setText("");
        count.setText("");
        remark.setText("");
        userid="";

        selelct_person.setSelectedIndex(0);

    }

    private boolean validate() {
        boolean flag=false;
        if(!date.getText().toString().equals(""))
        {
            flag=true;
            if(!count.getText().equals(""))
            {
                flag=true;
                if(!remark.getText().equals(""))
                {
                    flag=true;

                }
                else
                {
                    remark.setError("Please enter remark");
                    flag=false;
                }

            }
            else
            {
                count.setError("Please enter count");
                flag=false;
            }

        }
        else
        {
            date.setError("Please enter date");
            flag=false;
        }
        return flag;
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    private void getMembers() {




        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<MemberListMain> call=apiService.getUsers();
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<MemberListMain>() {

            @Override
            public void onResponse(Call<MemberListMain> call, retrofit2.Response<MemberListMain> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                String messege=response.message();
                nameSpinnerIdList.clear();
                nameSpinnerList.clear();
                nameSpinnerList.add("Select Person");
                nameSpinnerIdList.add("0");
                if(messege.equals("OK"))
                {
                    Code code=response.body().getCode();
                    String codevalue=code.getCode().toString();
                    String messagealue=code.getMessage();
                    progress.cancel();
                    if(codevalue.equals("200"))
                    {
                        List<Userinfo> userifno=response.body().getUserinfo();
                        for (int i=0;i<userifno.size();i++) {
                            String username = userifno.get(i).getName();
                            Log.e( "onResponse: ",""+username );
                            Log.e( "size: ",""+userifno.size() );
                            String userid = userifno.get(i).getUserId().toString();
                            nameSpinnerIdList.add(userid);
                            nameSpinnerList.add(username);



                        }
                        selelct_person.setItems(nameSpinnerList);

                    }

                }

                Log.i("Shanil : ", response.message().toString());



            }

            @Override
            public void onFailure(Call<MemberListMain> call, Throwable t) {
progress.cancel();
                t.printStackTrace();
            }
        });



    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
    }
}
