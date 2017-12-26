
package com.leeway.templapp.Connection;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NewsList {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("news_id")
    private String mNewsId;
    @SerializedName("News_Image")
    private List<NewsImage> mNewsImage;
    @SerializedName("title")
    private String mTitle;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getNewsId() {
        return mNewsId;
    }

    public void setNewsId(String newsId) {
        mNewsId = newsId;
    }

    public List<NewsImage> getNewsImage() {
        return mNewsImage;
    }

    public void setNewsImage(List<NewsImage> NewsImage) {
        mNewsImage = NewsImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
