package com.leeway.templapp.Interfaces;

/**
 * Created by work on 7/28/2017.
 */

public interface OnHttpResponseUpdateNews {
    void OnSuccessUpdateNews(boolean stautus, String message);
    void OnFailedUpdateNews(String message);
}
