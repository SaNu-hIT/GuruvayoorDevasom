package com.leeway.templapp.MainScreens.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leeway.templapp.Adapter.CountRecyclerViewAdapter;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.ListCountModel;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Status;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;
import com.leeway.templapp.WrapContentLinearLayoutManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListCount extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountRecyclerViewAdapter mAdapter;
    private ProgressDialog progress;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_count);
sessionManager=new SessionManager(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

progress.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),AddCount.class);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        String role=sessionManager.getRole();
        if(role.equals("1")) {
            ListAllIllams("");
        }
        else
        {
String ss=sessionManager.getUserId();
            ListAllIllams(ss);
        }




    }

    private void ListAllIllams(String userId) {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


     Call<ListCountModel>   call=apiService.ListQuantity(userId);
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<ListCountModel>() {

            @Override
            public void onResponse(Call<ListCountModel> call, retrofit2.Response<ListCountModel> response) {
                progress.cancel();

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);

                Log.i("Shanil : ", response.toString());
                Status status=response.body().getStatus();
//                Integer code=response.body().getCode().getCode();
                String code=status.getCode().toString();
                String message=status.getMessage();
                if (code.equals("200")) {


                    List<Quantity> quantity = response.body().getQuantity();
                    if (quantity.size() > 0) {


                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setNestedScrollingEnabled(false);
                        mAdapter = new CountRecyclerViewAdapter(getBaseContext(),quantity);
                        recyclerView.setAdapter(mAdapter);

                    }                           else {



                    }
                }
                else
                {
//                    Toast.makeText(ListCount.this, "Parsing error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListCountModel> call, Throwable t) {
                progress.cancel();

                t.printStackTrace();
            }
        });



    }

    public void onBackPressed(View view) {
        onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
        progress.show();
        String ss=sessionManager.getUserId();
        ListAllIllams(ss);
    }
}
