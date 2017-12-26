package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.R;
import com.leeway.templapp.Retrofit.CreateUserRetrofit.CreateStatus;
import com.leeway.templapp.Retrofit.CreateUserRetrofit.CreateUser;
import com.leeway.templapp.Retrofit.ModelClass.Code;
import com.leeway.templapp.Retrofit.ModelClass.IllamModel;
import com.leeway.templapp.Retrofit.ModelClass.Illaminfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by intellyelabs on 30/07/17.
 */

public class AddMembers_Activity extends AppCompatActivity{


    EditText MemberId,Membername,MemberPhone,Memberillamid,Memberpassword;
    Button CreateButton;
    CoordinatorLayout maincordinatorlay;
    Call<CreateUser> call1=null;
    String name,password,phone;


    List<String> illamname;
    List<String> illamid;
    private MaterialSpinner memberillamid;
    String illamids="";
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmembers_layout);
     illamname =new ArrayList<>();
      illamid=new ArrayList<>();

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);




        MemberId=(EditText)findViewById(R.id.memberid);
        Membername=(EditText)findViewById(R.id.membername);
        MemberPhone=(EditText)findViewById(R.id.memberphone);
        memberillamid=(MaterialSpinner)findViewById(R.id.memberillamid);
        Memberpassword=(EditText)findViewById(R.id.memberpassword);
        maincordinatorlay=(CoordinatorLayout)findViewById(R.id.main);
progress.show();
        ListAllIllams();
        CreateButton=(Button)findViewById(R.id.createbtn);

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (MemberId.equals("")||illamids.equals("")||Membername.equals("")||Memberpassword.equals("")||MemberPhone.equals("")){

                    Snackbar snackbar = Snackbar.make(maincordinatorlay, "Please Fill All Fields..",
                            Snackbar.LENGTH_LONG);
                }else {

progress.show();
                    AddUserNew(Membername.getText().toString(),Memberpassword.getText().toString(),MemberPhone.getText().toString(),illamids);

                }

            }


        });

        memberillamid.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                illamids=illamid.get(position);
                Log.e( "onItemSelected: ",""+illamids );

            }
        });


    }

    private void ListAllIllams() {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<IllamModel>   call=apiService.getillam();
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<IllamModel>() {

            @Override
            public void onResponse(Call<IllamModel> call, retrofit2.Response<IllamModel> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
progress.cancel();
                Log.i("Shanil : ", response.toString());
                Code status=response.body().getCode();
//                Integer code=response.body().getCode().getCode();
                String code=status.getCode().toString();
                String message=status.getMessage();
                if (code.equals("200")) {

                    illamname.clear();
                    illamid.clear();
                    illamname.add("Select illam");
                    illamid.add("0");


                    List<Illaminfo> relationship = response.body().getIllaminfo();
                    if (relationship.size() > 0) {
                        for (int i = 0; i < relationship.size(); i++) {

                            Illaminfo relncode = relationship.get(i);
                            illamname.add(relncode.getIllamName());
                            illamid.add(relncode.getIllamId().toString());

                            memberillamid.setItems(illamname);


                        }


                    }else {



                    }
                }
                else
                {
                    Toast.makeText(AddMembers_Activity.this, "Parsing error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IllamModel> call, Throwable t) {
progress.cancel();
                t.printStackTrace();
            }
        });



    }
    private void AddUserNew(String name,String password,String phone,String illamids) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        call1=apiService.getuser(name,password,phone,illamids);
        call1.enqueue(new Callback<CreateUser>() {
            @Override
            public void onResponse(Call<CreateUser> call, retrofit2.Response<CreateUser> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
                System.out.println("Retrofimessafe : " + response.message());

              if(response.message().equals("OK"))
              {
                  progress.cancel();
                  CreateStatus otpstatus = response.body().getStatus();
                  Log.e( "onResponse: ",""+otpstatus );
                  String status = String.valueOf(otpstatus.getCode());
                  if (status.equals("200")) {

                      String message = String.valueOf(otpstatus.getMessage());
                      Toast.makeText(AddMembers_Activity.this, message, Toast.LENGTH_SHORT).show();
Membername.setText("");
                      MemberPhone.setText("");
                      Memberpassword.setText("");
                      MemberId.setText("");
                      memberillamid.setSelectedIndex(0);






                  } else {

                      String message = String.valueOf(otpstatus.getMessage());
                      Toast.makeText(AddMembers_Activity.this, message, Toast.LENGTH_SHORT).show();
                  }
              }
              else
              {
                  Toast.makeText(AddMembers_Activity.this, "server error", Toast.LENGTH_SHORT).show();
              }
            }
            @Override
            public void onFailure(Call<CreateUser> call, Throwable t) {
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(AddMembers_Activity.this, "Create UserFailed", Toast.LENGTH_SHORT).show();
                progress.cancel();
            }




        });



    }

    public void onBackPressed(View view) {
        onBackPressed();
    }
}
