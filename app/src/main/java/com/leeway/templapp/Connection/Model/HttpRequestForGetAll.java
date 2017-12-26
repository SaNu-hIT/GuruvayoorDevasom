package com.leeway.templapp.Connection.Model;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.OnHttpRespoceForAll;
import com.leeway.templapp.Retrofit.ModelClass.HomeBean.HomeBea;
import com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetAll {
    OnHttpRespoceForAll onHttpRespoceForEvents;

    public HttpRequestForGetAll(OnHttpRespoceForAll onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getallRespons()
    {

        ApiInterface apiInterfaces= ApiClient.getClient().create(ApiInterface.class);
        Call<HomeBea> call=apiInterfaces.getallResponse();
        call.enqueue(new Callback<HomeBea>() {
            @Override
            public void onResponse(Call<HomeBea> call, Response<HomeBea> response) {
                if(response.message().equals("OK"))
                {
                    if(!response.body().getStatus().getCode().equals("200"))
                    {
                        INFO info=response.body().getINFO();

                        onHttpRespoceForEvents.onHttpEventSuccess(info);
                    }

                }
                else
                {
                    onHttpRespoceForEvents.onHttpEventFailed("Failed");
                }

            }

            @Override
            public void onFailure(Call<HomeBea> call, Throwable t) {

                onHttpRespoceForEvents.onHttpEventFailed("Server error");
            }
        });
    }

}
