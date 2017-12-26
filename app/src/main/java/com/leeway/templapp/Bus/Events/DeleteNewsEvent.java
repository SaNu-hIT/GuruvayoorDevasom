package com.leeway.templapp.Bus.Events;

/**
 * Created by work on 7/26/2017.
 */

public class DeleteNewsEvent {
    private String newsId;

    public DeleteNewsEvent(String newsId) {
        this.newsId = newsId;
    }
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }




}
