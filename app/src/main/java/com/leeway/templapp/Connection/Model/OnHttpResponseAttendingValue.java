package com.leeway.templapp.Connection.Model;

/**
 * Created by work on 7/26/2017.
 */

public interface OnHttpResponseAttendingValue {
    void OnSuccessAttendingValue(boolean stautus, String message);

    void OnFaildAttendingValue(String message);
}
