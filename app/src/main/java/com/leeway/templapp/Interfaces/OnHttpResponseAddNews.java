package com.leeway.templapp.Interfaces;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponseAddNews {
    void OnSuccessAddNews(boolean stautus, String message);
    void OnFailedAddNews(String message);
}
