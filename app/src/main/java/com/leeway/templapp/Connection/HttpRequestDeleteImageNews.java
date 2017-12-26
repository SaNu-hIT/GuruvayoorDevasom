package com.leeway.templapp.Connection;


import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.News.Model_Two.DeleteImageModel;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteImageNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/27/2017.
 */

public class HttpRequestDeleteImageNews {
    OnHttpResponseDeleteImageNews onHttpResponseDeleteImageNews;

    public HttpRequestDeleteImageNews(OnHttpResponseDeleteImageNews onHttpResponseDeleteImageNews) {
        this.onHttpResponseDeleteImageNews = onHttpResponseDeleteImageNews;
    }


    public void DeleteImageNews(int imageId){
        ApiInterface apiClient= ApiClient.getClient().create(ApiInterface.class);
        Call<DeleteImageModel> call=apiClient.getDeleteImageNewsResponse(imageId);
        call.enqueue(new Callback<DeleteImageModel>() {
            @Override
            public void onResponse(Call<DeleteImageModel> call, Response<DeleteImageModel> response) {


                String responce_string=response.toString();

                if(response.message().equals("OK"))
                {
                    if(!response.body().getError())
                    {
                        onHttpResponseDeleteImageNews.OnSuccessDeleteImageNews(response.body().getError(),response.body().getMessage());
                    }
                    else
                    {
                        onHttpResponseDeleteImageNews.OnFailedDeleteImageNews(response.body().getMessage());
                    }

                }else
                {
                    onHttpResponseDeleteImageNews.OnFailedDeleteImageNews("Server Error");

                }



            }

            @Override
            public void onFailure(Call<DeleteImageModel> call, Throwable t) {
                onHttpResponseDeleteImageNews.OnFailedDeleteImageNews("Server Error in retrofit");
            }
        });
    }
}
