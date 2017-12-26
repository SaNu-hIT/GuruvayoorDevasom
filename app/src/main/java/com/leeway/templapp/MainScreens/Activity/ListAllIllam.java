package com.leeway.templapp.MainScreens.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leeway.templapp.Adapter.ListIllamAdapter;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteIllam;
import com.leeway.templapp.R;
import com.leeway.templapp.Retrofit.ModelClass.Code;
import com.leeway.templapp.Retrofit.ModelClass.IllamModel;
import com.leeway.templapp.Retrofit.ModelClass.Illaminfo;
import com.leeway.templapp.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Sha baby on 02-Aug-17.
 */

public class ListAllIllam extends AppCompatActivity implements OnHttpResponseDeleteIllam {


    RecyclerView mRecyclerView;
    Call<IllamModel> call=null;
    ListIllamAdapter mAdapter;
    private final List<Integer> Illam_id = new ArrayList<>();
    private final List<String> illam_name = new ArrayList<>();
    private final List<String> illam_address = new ArrayList<>();

    Activity activity;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity=this;
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        ListAllIllams();
        mRecyclerView = (RecyclerView) findViewById(R.id.count_details);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent in=new Intent(getApplicationContext(),AddIllam.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
            }
        });
    }
    private void ListAllIllams() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        call=apiService.getillam();
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<IllamModel>() {
            @Override
            public void onResponse(Call<IllamModel> call, retrofit2.Response<IllamModel> response) {
                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                Log.i("Shanil : ", response.toString());
                Code status=response.body().getCode();
//                Integer code=response.body().getCode().getCode();
                String code=status.getCode().toString();
                String message=status.getMessage();
                progress.cancel();
                if (code.equals("200")) {
                    illam_address.clear();
                    illam_name.clear();
                    Illam_id.clear();
                    List<com.leeway.templapp.Retrofit.ModelClass.Illaminfo> relationship = response.body().getIllaminfo();
                    if (relationship.size() > 0) {
                        for (int i = 0; i < relationship.size(); i++) {
                            Illaminfo relncode = relationship.get(i);
                            illam_address.add(relncode.getIllamAddress());
                            illam_name.add(relncode.getIllamName());
                            Illam_id.add(Integer.valueOf(relncode.getIllamId().toString()));
                        }
                        try {


                            if (Illam_id.size() > 0) {
                           setAdapter();

                            } else {
                                mRecyclerView.setVisibility(View.GONE);
                            }
                        } catch (Exception e) // no guruji
                        {
                            e.printStackTrace();
                        }
                    }else {

                    }
                }
                else
                {
                    progress.cancel();
                    Toast.makeText(ListAllIllam.this, "Parsing error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IllamModel> call, Throwable t) {
                t.printStackTrace();
                progress.cancel();
            }
        });
    }

    private void setAdapter() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter = new ListIllamAdapter(illam_name, Illam_id, illam_address, activity, getApplicationContext());
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void onBackPressed(View view) {
        onBackPressed();
    }

    @Override
    public void OnSuccessDelete() {
        ListAllIllams();
    }
}
