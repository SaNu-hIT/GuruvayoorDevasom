package com.leeway.templapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.leeway.templapp.MainScreens.Activity.Login_Page;

/**
 * Created by intellyelabs on 21/07/17.
 */

public class Confirm_Password extends AppCompatActivity {

    EditText passwrdEdt,confrmpasswordEdt;
    Button GoButton;
    ProgressBar progressbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_password);

        progressbar=(ProgressBar)findViewById(R.id.prog1);
        progressbar.setVisibility(View.GONE);

        passwrdEdt=(EditText)findViewById(R.id.passwrd);
        confrmpasswordEdt=(EditText)findViewById(R.id.cfrmpasswrd);
        GoButton=(Button) findViewById(R.id.gobutton);

        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwrdEdt.getText().toString().equals("")&&confrmpasswordEdt.getText().toString().equals("")){

                    passwrdEdt.setError("Enter Password");
                    confrmpasswordEdt.setError("Confirm Password");

                }else {
                    progressbar.setVisibility(View.VISIBLE);
                    Intent in=new Intent(getApplicationContext(),Login_Page.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);

                }

            }
        });
    }



}
