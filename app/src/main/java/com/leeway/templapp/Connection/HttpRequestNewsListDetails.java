package com.leeway.templapp.Connection;


import android.util.Log;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/26/2017.
 */

public class HttpRequestNewsListDetails {
    OnHttpResponseListNewsDetails onHttpResponseListNewsDetails;

    public HttpRequestNewsListDetails(OnHttpResponseListNewsDetails onHttpResponseListNewsDetails) {
        this.onHttpResponseListNewsDetails = onHttpResponseListNewsDetails;
    }


    public void GetNewsList(String newsid) {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        Call<ListNewsDetailsModel> call = apiClient.getNewsResponse();
        call.enqueue(new Callback<ListNewsDetailsModel>() {
            @Override
            public void onResponse(Call<ListNewsDetailsModel> call, Response<ListNewsDetailsModel> response) {


                String responce_string = response.toString();
                Log.e( "onResponse: ",""+responce_string );

                if (response.message().equals("OK")) {
                    if (!response.body().getError()) {
                        onHttpResponseListNewsDetails.OnSuccessNewsDetailList(response.body().getError(), response.body().getNewsList());
                        ;
                    } else {
                        onHttpResponseListNewsDetails.OnFaildNewsDetailList(response.body().getMessage());
                    }

                } else {
                    onHttpResponseListNewsDetails.OnFaildNewsDetailList("Server Error");

                }


            }

            @Override
            public void onFailure(Call<ListNewsDetailsModel> call, Throwable t) {
                onHttpResponseListNewsDetails.OnFaildNewsDetailList("Server Error in retrofit");
            }
        });
    }
}
