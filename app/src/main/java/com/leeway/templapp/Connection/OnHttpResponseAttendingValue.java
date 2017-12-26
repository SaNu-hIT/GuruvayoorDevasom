package com.leeway.templapp.Connection;

/**
 * Created by work on 7/26/2017.
 */

public interface OnHttpResponseAttendingValue {
    void OnSuccessAttendingValue(boolean stautus, String message);

    void OnFaildAttendingValue(String message);
}
