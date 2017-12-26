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
import com.leeway.templapp.Retrofit.ModelClass.AddIllamModel.ResponceCommon;
import com.leeway.templapp.Retrofit.ModelClass.AddIllamModel.Status;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Sha baby on 02-Aug-17.
 */

public class AddIllam extends AppCompatActivity {


    EditText IllamName,IllamPlace;
    Button CreateButton;
    CoordinatorLayout maincordinatorlay;

    List<String> illamname;
    List<String> illamid;
    private MaterialSpinner memberillamid;
    String illamids="";
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addillam_layout);

       illamname=new ArrayList<>();
        illamid=new ArrayList<>();

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        IllamName=(EditText)findViewById(R.id.illamname);
        IllamPlace=(EditText)findViewById(R.id.illamplace);

        maincordinatorlay=(CoordinatorLayout)findViewById(R.id.main);

        CreateButton=(Button)findViewById(R.id.createbtn);

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (IllamName.equals("")||IllamPlace.equals("")){

                    Snackbar snackbar = Snackbar.make(maincordinatorlay, "Please Fill All Fields..",
                            Snackbar.LENGTH_LONG);


                }else {


                    progress.show();

                    AddIllamToServe(IllamName.getText().toString(),IllamPlace.getText().toString());

                }

            }


        });


    }

    private void AddIllamToServe(String s, String s1) {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


      Call<ResponceCommon>  call=apiService.AddIllam(s,s1);
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<ResponceCommon>() {

            @Override
            public void onResponse(Call<ResponceCommon> call, retrofit2.Response<ResponceCommon> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);

                progress.cancel();

                Log.i("Shanil : ", response.toString());

                Status code=response.body().getStatus();

                String codestring=code.getCode().toString();



                if(codestring.equals("200"))
                {

                    final String message=code.getMessage();
                    Toast.makeText(AddIllam.this, ""+message, Toast.LENGTH_SHORT).show();
                    IllamName.setText("");
                    IllamPlace.setText("");

//                    AddIllam.this.runOnUiThread(new Runnable() {
//                        public void run() {

//                            new SweetAlertDialog(getApplication(), SweetAlertDialog.SUCCESS_TYPE)
//                                    .setTitleText("SUCCESS!")
//                                    .setContentText(message)
//                                    .setConfirmText("Ok")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                            IllamName.setText("");
//                                            IllamPlace.setText("");
//                                        }
//                                    })
//                                    .show();


//                        }
//                    });




                }
                else
                {
                    final String messages=code.getMessage();
                    Toast.makeText(AddIllam.this, ""+messages, Toast.LENGTH_SHORT).show();
//                    AddIllam.this.runOnUiThread(new Runnable() {
//                        public void run() {

//                            new SweetAlertDialog(getApplication(), SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText("Failed")
//                            .setContentText(messages)
//                            .show();
//                        }
//                    });

                }

//                Status status=response.body().getStatus();
//                Integer code=response.body().getCode().getCode();
//                String code=status.getCode().toString();
//                String message=status.getMessage();


            }

            @Override
            public void onFailure(Call<ResponceCommon> call, Throwable t) {
progress.cancel();
                t.printStackTrace();
            }
        });




    }


    public void onBackPressed(View view) {
        onBackPressed();
    }
}
