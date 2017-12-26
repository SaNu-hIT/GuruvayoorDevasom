package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.R;
import com.leeway.templapp.Retrofit.ModelClass.LoginModel;
import com.leeway.templapp.Retrofit.ModelClass.Status;
import com.leeway.templapp.Retrofit.ModelClass.Userinfo;
import com.leeway.templapp.SessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by intellyelabs on 21/07/17.
 */

public class Login_Page extends AppCompatActivity {

    EditText phoneEdt,passEdt;
    Button LoginButton;
    TextView forgetTxt;

    Call<LoginModel> call1=null;

    String Userid,name,phone,api_key;
    SessionManager sessionManager;
    private String role;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        phoneEdt=(EditText)findViewById(R.id.phonedt);
       forgetTxt=(TextView)findViewById(R.id.forgetpasstxt);
        passEdt=(EditText)findViewById(R.id.passedt);
       LoginButton=(Button) findViewById(R.id.loginbutton);
//        phoneEdt.setText("9890098900");
//        passEdt.setText("12345");
        sessionManager=new SessionManager(this);



        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (phoneEdt.getText().toString().equals("")||passEdt.getText().toString().equals("")){

                    phoneEdt.setError("Enter Phone Number");
                    passEdt.setError("Enter Password");

                }else {
                    progress.show();

                    LoginUser();

                }



            }
        });

    }

    private void LoginUser() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        call1=apiService.getlogin("userlogin",phoneEdt.getText().toString(),passEdt.getText().toString());
        call1.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, retrofit2.Response<LoginModel> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);
//                System.out.println("Retrofit Response : "+response.body().getStatus().toString() );

progress.cancel();
                String error=response.message();
                Log.e( "onResponse: ", ""+error);

                Status stat=response.body().getStatus();

                String status=stat.getCode().toString();
//                String code=response.body().getCode()

//                Status status=response.body().getStatus();
//                Code code=response.body().getCode();
////                LoginCode otpstatus = response.body().getCode();

//                String status = code.get
                if (status.equals("200")) {


                    Userinfo code=response.body().getUserinfo();
//                    LoginStatus log = response.body().getStatus();
                    Userid = String.valueOf(code.getUId());
                    name = code.getName();
                    phone = code.getPhone().toString();
                    role = code.getRole().toString();


                    sessionManager.SaveCredentials(name,phone);
                    sessionManager.SaveRole(role);
                    sessionManager.SaveUseId(Userid);



                    if(role.equals("0")) {
                        Intent i = new Intent(getApplicationContext(), UserSide.class);
                        Bundle b = new Bundle();
                        b.putString("userPhone", phone);
                        i.putExtras(b);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                    else if(role.equals("1"))
                    {
                        Intent i = new Intent(getApplicationContext(), AdminActivity.class);
                        Bundle b = new Bundle();
                        b.putString("userPhone", phone);
                        i.putExtras(b);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                    else
                    {
                        Toast.makeText(Login_Page.this, "Contact admin now", Toast.LENGTH_SHORT).show();

                    }



                } else {
                    Toast.makeText(Login_Page.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(Login_Page.this, "Login Failed", Toast.LENGTH_SHORT).show();
progress.cancel();            }




        });



    }


    }



