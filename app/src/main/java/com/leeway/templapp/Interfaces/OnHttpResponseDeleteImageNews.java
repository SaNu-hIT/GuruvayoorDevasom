package com.leeway.templapp.Interfaces;

/**
 * Created by work on 7/27/2017.
 */

public interface OnHttpResponseDeleteImageNews {
    void OnSuccessDeleteImageNews(boolean stautus, String message);

    void OnFailedDeleteImageNews(String errorMessage);
}
