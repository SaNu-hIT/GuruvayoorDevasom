package com.leeway.templapp.Connection;


import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.News.Model_Two.AddNewsModel;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.NewsAddBody;
import com.leeway.templapp.Interfaces.OnHttpResponseAddNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/26/2017.
 */

public class HttpRequestAddNews {
    OnHttpResponseAddNews onHttpResponseAddNews;

    public HttpRequestAddNews(OnHttpResponseAddNews onHttpResponseAddNews) {
        this.onHttpResponseAddNews = onHttpResponseAddNews;
    }


    public void AddNewsEvent(NewsAddBody dataJson) {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        Call<AddNewsModel> call = apiClient.createNews(dataJson);
        call.enqueue(new Callback<AddNewsModel>() {
            @Override
            public void onResponse(Call<AddNewsModel> call, Response<AddNewsModel> response) {


                String responce_string = response.toString();

                if (response.message().equals("OK")) {
                    if (!response.body().getError()) {
                        onHttpResponseAddNews.OnSuccessAddNews(response.body().getError(), response.body().getMessage());
                    } else {
                        onHttpResponseAddNews.OnFailedAddNews(response.body().getMessage());
                    }

                } else {
                    onHttpResponseAddNews.OnFailedAddNews("Server Error");

                }


            }

            @Override
            public void onFailure(Call<AddNewsModel> call, Throwable t) {
                onHttpResponseAddNews.OnFailedAddNews(t.getMessage());
                System.out.println("errroe" + t.getMessage());
            }
        });
    }
}
