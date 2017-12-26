package com.leeway.templapp.Connection;


import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.UpdateNewsBody;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.UpdateNewsModel;
import com.leeway.templapp.Interfaces.OnHttpResponseUpdateNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/28/2017.
 */

public class HttpRequestUpdateNews {
    OnHttpResponseUpdateNews onHttpResponseUpdateNews;

    public HttpRequestUpdateNews(OnHttpResponseUpdateNews onHttpResponseUpdateNews) {
        this.onHttpResponseUpdateNews = onHttpResponseUpdateNews;
    }


    public void UpdateNews(UpdateNewsBody dataJson) {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        Call<UpdateNewsModel> call = apiClient.updateNews(dataJson);
        call.enqueue(new Callback<UpdateNewsModel>() {
            @Override
            public void onResponse(Call<UpdateNewsModel> call, Response<UpdateNewsModel> response) {


                String responce_string = response.toString();

                if (response.message().equals("OK")) {
                    if (!response.body().getError()) {
                        onHttpResponseUpdateNews.OnSuccessUpdateNews(response.body().getError(), response.body().getMessage());
                    } else {
                        onHttpResponseUpdateNews.OnFailedUpdateNews(response.body().getMessage());
                    }

                } else {
                    onHttpResponseUpdateNews.OnFailedUpdateNews("Server Error");

                }


            }

            @Override
            public void onFailure(Call<UpdateNewsModel> call, Throwable t) {
                onHttpResponseUpdateNews.OnFailedUpdateNews(t.getMessage());
                System.out.println("errroe" + t.getMessage());
            }
        });
    }
}
