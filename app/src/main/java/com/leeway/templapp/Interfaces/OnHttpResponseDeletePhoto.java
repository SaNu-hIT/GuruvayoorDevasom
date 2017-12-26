package com.leeway.templapp.Interfaces;

/**
 * Created by work on 7/28/2017.
 */

public interface OnHttpResponseDeletePhoto {
    void OnSuccessDeletePhoto(boolean stautus, String message);
    void OnFailedDeletePhoto(String errorMmessage);
}
