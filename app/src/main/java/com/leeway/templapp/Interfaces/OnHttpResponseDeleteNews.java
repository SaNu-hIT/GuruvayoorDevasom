package com.leeway.templapp.Interfaces;

/**
 * Created by work on 7/26/2017.
 */

public interface OnHttpResponseDeleteNews {
    void OnSuccessDeleteNews(boolean stautus, String message);

    void OnFailedDeleteNews(String errorMessage);
}
