package com.leeway.templapp.Connection;


import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.News.Model_Two.DeleteNewsModel;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/26/2017.
 */

public class HttpRequestDeleteNews {
    OnHttpResponseDeleteNews onHttpResponseDeleteNews;

    public HttpRequestDeleteNews(OnHttpResponseDeleteNews onHttpResponseDeleteNews) {
        this.onHttpResponseDeleteNews = onHttpResponseDeleteNews;
    }


    public void DeleteNews(int newsId){
        ApiInterface apiClient= ApiClient.getClient().create(ApiInterface.class);
        Call<DeleteNewsModel> call=apiClient.getDeleteNewsResponse(newsId);
        call.enqueue(new Callback<DeleteNewsModel>() {
            @Override
            public void onResponse(Call<DeleteNewsModel> call, Response<DeleteNewsModel> response) {


                String responce_string=response.toString();

                if(response.message().equals("OK"))
                {
                    if(!response.body().getError())
                    {
                        onHttpResponseDeleteNews.OnSuccessDeleteNews(response.body().getError(),response.body().getMessage());
                    }
                    else
                    {
                        onHttpResponseDeleteNews.OnFailedDeleteNews(response.body().getMessage());
                    }

                }else
                {
                    onHttpResponseDeleteNews.OnFailedDeleteNews("Server Error");

                }



            }

            @Override
            public void onFailure(Call<DeleteNewsModel> call, Throwable t) {
                onHttpResponseDeleteNews.OnFailedDeleteNews("Server Error in retrofit");
            }
        });
    }
}
