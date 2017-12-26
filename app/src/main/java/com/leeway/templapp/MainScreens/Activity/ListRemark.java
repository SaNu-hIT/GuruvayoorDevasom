package com.leeway.templapp.MainScreens.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leeway.templapp.Adapter.ListRemerkAdapter;
import com.leeway.templapp.Connection.HttpRequestForGetRemarks;
import com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo;
import com.leeway.templapp.Connection.OnHttpRespoceForRemark;
import com.leeway.templapp.R;
import com.leeway.templapp.WrapContentLinearLayoutManager;

import java.util.List;

public class ListRemark extends BaseActivity implements OnHttpRespoceForRemark {

    private RecyclerView mRecyclerView;
    ListRemerkAdapter listRemerkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_remark);
        setProgressBar();
        showProgress("Please wait while processing...");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        HttpRequestForGetRemarks httpRequestForGetRemarks=new HttpRequestForGetRemarks(this);
        httpRequestForGetRemarks.getEvents("","");

    }

    @Override
    public void onHttpEventSuccess(List<Remarkinfo> eventsInfoList) {
        cancelProgress();
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        listRemerkAdapter = new ListRemerkAdapter(this,eventsInfoList);
        mRecyclerView.setAdapter(listRemerkAdapter);
        
    }

    @Override
    public void onHttpEventFailed(String message) {

    }

    public void onBackPressed(View view) {
        onBackPressed();
    }
}
