package com.leeway.templapp.Connection;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.ListRemarkModel.RemarkModelForList;
import com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetRemarks {
    OnHttpRespoceForRemark onHttpRespoceForEvents;

    public HttpRequestForGetRemarks(OnHttpRespoceForRemark onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getEvents(String eventid,String User_Id)
    {

        ApiInterface apiInterfaces= ApiClient.getClient().create(ApiInterface.class);
        Call<RemarkModelForList> call=apiInterfaces.ListRemark();
        call.enqueue(new Callback<RemarkModelForList>() {
            @Override
            public void onResponse(Call<RemarkModelForList> call, Response<RemarkModelForList> response) {
                if(response.message().equals("OK"))
                {
                    if(!response.body().getStatus().getCode().equals("200"))
                    {
                        List<Remarkinfo> events=response.body().getRemarkinfo();

                        onHttpRespoceForEvents.onHttpEventSuccess(events);
                    }

                }
                else
                {
                    onHttpRespoceForEvents.onHttpEventFailed("Failed");
                }

            }

            @Override
            public void onFailure(Call<RemarkModelForList> call, Throwable t) {

                onHttpRespoceForEvents.onHttpEventFailed("Server error");
            }
        });
    }

}
