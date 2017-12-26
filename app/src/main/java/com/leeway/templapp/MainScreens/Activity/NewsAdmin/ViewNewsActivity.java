package com.leeway.templapp.MainScreens.Activity.NewsAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leeway.templapp.Adapter.ViewNewsAdapter;
import com.leeway.templapp.Bus.Events.DeleteNewsEvent;
import com.leeway.templapp.Bus.Events.EditNewsDetailsEvent;
import com.leeway.templapp.Bus.Events.RefreshNewsListEvent;
import com.leeway.templapp.Connection.HttpRequestDeleteNews;
import com.leeway.templapp.Connection.HttpRequestNewsListDetails;
import com.leeway.templapp.Connection.NewsList;
import com.leeway.templapp.Connection.OnHttpResponseListNewsDetails;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteNews;
import com.leeway.templapp.MainScreens.Activity.BaseActivity;
import com.leeway.templapp.R;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellyze105 on 17-07-2017.
 */

public class ViewNewsActivity extends BaseActivity implements OnHttpResponseListNewsDetails, OnHttpResponseDeleteNews {


    RecyclerView rv_Newslist;
    private LinearLayoutManager mLayoutManager;
    private ViewNewsAdapter itemAdapter;

    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);
        initview();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setProgressBar();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("View News");
        }
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void initview() {
        rv_Newslist = (RecyclerView) findViewById(R.id.rv_Newslist);

    }


    public void initRecyclerView() {
        showProgress("Please wait while processing..");
        HttpRequestNewsListDetails httpRequestNewsListDetails = new HttpRequestNewsListDetails(this);
        httpRequestNewsListDetails.GetNewsList("");
    }

    @Override
    protected void onResume() {
        flag = 1;
        super.onResume();

    }

    @Override
    public void OnSuccessNewsDetailList(boolean stautus, List<NewsList> newsLists) {
        cancelProgress();
        rv_Newslist.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_Newslist.setLayoutManager(mLayoutManager);
        itemAdapter = new ViewNewsAdapter(ViewNewsActivity.this, newsLists);
        rv_Newslist.setAdapter(itemAdapter);
    }

    @Subscribe
    public void RefresListEvent(DeleteNewsEvent event) {
        String eventId = event.getNewsId();
        showProgress("Please wait while processing..");
        HttpRequestDeleteNews httpRequestDeleteNews = new HttpRequestDeleteNews(this);
        httpRequestDeleteNews.DeleteNews(Integer.parseInt(eventId));
//        onResume();
    }

    @Subscribe
    public void NewsDetailsEvent(EditNewsDetailsEvent event) {
        if (flag == 1) {
            flag = 0;
            String newsId = event.getNewsId();
            String description = event.getDescription();
            String title = event.getTitle();
            ArrayList<String> imgListUrl = new ArrayList<String>();
            ArrayList<String> imgListId = new ArrayList<String>();
            for (int i = 0; i < event.getNewsImage().size(); i++) {
                imgListUrl.add(event.getNewsImage().get(i).getNewsImageurl());
                imgListId.add(event.getNewsImage().get(i).getIds());
            }
            Intent intent = new Intent(this, EditNewsActivity.class);
            intent.putStringArrayListExtra("imgListUrl", imgListUrl);
            intent.putStringArrayListExtra("imgListId", imgListId);
            intent.putExtra("newsId", newsId);
            intent.putExtra("description", description);
            intent.putExtra("title", title);
            startActivity(intent);
        }

//
    }

    @Subscribe
    public void RefreshNews(RefreshNewsListEvent event) {
        initRecyclerView();
    }

    @Override
    public void OnFaildNewsDetailList(String errorMessage) {
        cancelProgress();
        showAlert(errorMessage);
    }

    @Override
    public void OnSuccessDeleteNews(boolean stautus, String message) {
        cancelProgress();
        showAlertSucces(message);
        initRecyclerView();
    }

    @Override
    public void OnFailedDeleteNews(String errorMessage) {
        cancelProgress();
        showAlert(errorMessage);
    }


}
