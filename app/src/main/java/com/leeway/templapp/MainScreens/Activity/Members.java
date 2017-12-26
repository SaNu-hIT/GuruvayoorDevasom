package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.leeway.templapp.Adapter.MembersRecyclerViewAdapter;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.MainScreens.ModelClass.Code;
import com.leeway.templapp.MainScreens.ModelClass.MemberListMain;
import com.leeway.templapp.MainScreens.ModelClass.Userinfo;
import com.leeway.templapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by intellyelabs on 21/07/17.
 */

public class Members extends AppCompatActivity {

RecyclerView recyclerView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_layout);
recyclerView= (RecyclerView) findViewById(R.id.listview);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();


        recyclerView= (RecyclerView) findViewById(R.id.listview);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_members);
        fab.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent=new Intent(getBaseContext(),AddMembers_Activity.class);
        startActivity(intent);
        }
        });

        getMembers();



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
progress.cancel();
String messege=response.message();
                if(messege.equals("OK"))
                {
                    Code code=response.body().getCode();
                    String codevalue=code.getCode().toString();
                    String messagealue=code.getMessage();
                    if(codevalue.equals("200"))
                    {
                      List<Userinfo> userifno=response.body().getUserinfo();
                        MembersRecyclerViewAdapter eventRecyclerViewAdapter=new MembersRecyclerViewAdapter(getBaseContext(),userifno);

                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(eventRecyclerViewAdapter);
eventRecyclerViewAdapter.notifyDataSetChanged();

                    }

                }

                Log.i("Shanil : ", response.message().toString());



            }

            @Override
            public void onFailure(Call<MemberListMain> call, Throwable t) {

                t.printStackTrace();
                progress.cancel();
            }
        });



    }

    public void onBackPressed(View view) {
        onBackPressed();
    }
}
