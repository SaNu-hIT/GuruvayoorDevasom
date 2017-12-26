package com.leeway.templapp.Connection;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.ListSchedules.ListSchedules;
import com.leeway.templapp.Connection.Model.ListSchedules.Sheduleinfo;
import com.leeway.templapp.Connection.Model.OnHttpRespoceForEvents;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetEvents {
    OnHttpRespoceForEvents onHttpRespoceForEvents;

    public HttpRequestForGetEvents(OnHttpRespoceForEvents onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getEvents(String eventid,String User_Id)
    {

        ApiInterface apiInterfaces= ApiClient.getClient().create(ApiInterface.class);
        Call<ListSchedules> call=apiInterfaces.ListSchedules();
        call.enqueue(new Callback<ListSchedules>() {
            @Override
            public void onResponse(Call<ListSchedules> call, Response<ListSchedules> response) {
                if(response.message().equals("OK"))
                {
                    if(!response.body().getStatus().getCode().equals("200"))
                    {
                        List<Sheduleinfo> events=response.body().getSheduleinfo();

                        onHttpRespoceForEvents.onHttpEventSuccess(events);
                    }

                }
                else
                {
                    onHttpRespoceForEvents.onHttpEventFailed("Failed");
                }

            }

            @Override
            public void onFailure(Call<ListSchedules> call, Throwable t) {

                onHttpRespoceForEvents.onHttpEventFailed("Server error");
            }
        });
    }

}
