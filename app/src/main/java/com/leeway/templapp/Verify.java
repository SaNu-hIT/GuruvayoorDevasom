package com.leeway.templapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by intellyelabs on 21/07/17.
 */

public class Verify extends AppCompatActivity {


    EditText edt1,edt2,edt3,edt4,edt5,edt6;
    Button VerifyButon;
    String phonenumber;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_layout);

        Bundle b=new Bundle();
        phonenumber=b.getString("phone");


        edt1=(EditText)findViewById(R.id.text1);
        edt2=(EditText)findViewById(R.id.text2);
        edt3=(EditText)findViewById(R.id.text3);
        edt4=(EditText)findViewById(R.id.text4);
        edt5=(EditText)findViewById(R.id.text5);
        edt6=(EditText)findViewById(R.id.text6);
        VerifyButon=(Button) findViewById(R.id.verifybtn);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Toast.makeText(Verify.this, "Verification Completed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Verify.this, "Verification Completed", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        VerifyButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt1.getText().toString().equals("")&&edt2.getText().toString().equals("")&&edt3.getText().toString().equals("")&&edt4.getText().toString().equals("")){

                    Toast.makeText(Verify.this, "Please fill all the fields...", Toast.LENGTH_SHORT).show();

                }else {

                    Intent in=new Intent(getApplicationContext(),Confirm_Password.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    startPhoneNumberVerification(phonenumber);

                }

            }
        });


    }




    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }




}
