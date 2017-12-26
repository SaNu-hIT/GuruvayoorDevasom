package com.leeway.templapp.Connection;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Connection.Model.AttendEventModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by work on 7/26/2017.
 */

public class HttpRequestAttendingValue {
    OnHttpResponseAttendingValue onHttpResponseAttendingValue;

    public HttpRequestAttendingValue(OnHttpResponseAttendingValue onHttpResponseAttendingValue) {
        this.onHttpResponseAttendingValue = onHttpResponseAttendingValue;
    }


    public void AttendEvent(int event_id, int userId, String attendVal) {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        Call<AttendEventModel> call = apiClient.getAttendEventResponse(event_id, userId, attendVal);
        call.enqueue(new Callback<AttendEventModel>() {
            @Override
            public void onResponse(Call<AttendEventModel> call, Response<AttendEventModel> response) {


                String responce_string = response.toString();

                if (response.message().equals("OK")) {
                    if (!response.body().getError()) {
                        onHttpResponseAttendingValue.OnSuccessAttendingValue(response.body().getError(), response.body().getMessage());
                    } else {
                        onHttpResponseAttendingValue.OnFaildAttendingValue(response.body().getMessage());
                    }

                } else {
                    onHttpResponseAttendingValue.OnFaildAttendingValue("Server Error");

                }


            }

            @Override
            public void onFailure(Call<AttendEventModel> call, Throwable t) {
                onHttpResponseAttendingValue.OnFaildAttendingValue("Server Error in retrofit");
            }
        });
    }
}
