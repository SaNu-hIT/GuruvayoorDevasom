package com.leeway.templapp.Bus.Events;


import com.leeway.templapp.Connection.NewsImage;

import java.util.List;

/**
 * Created by work on 7/27/2017.
 */

public class EditNewsDetailsEvent {
    private String newsId;
    private String description;
    private String title;
    List<NewsImage> newsImage;

    public EditNewsDetailsEvent(String newsId, String description, String title, List<NewsImage> newsImage) {
        this.description = description;
        this.newsId = newsId;
        this.title = title;
        this.newsImage = newsImage;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewsImage> getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(List<NewsImage> newsImage) {
        this.newsImage = newsImage;
    }


}
