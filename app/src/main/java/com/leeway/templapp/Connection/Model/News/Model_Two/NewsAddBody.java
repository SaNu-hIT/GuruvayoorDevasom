
package com.leeway.templapp.Connection.Model.News.Model_Two;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NewsAddBody {

    @SerializedName("image_list")
    private List<ImageList> mImageList;
    @SerializedName("news_description")
    private String mNewsDescription;
    @SerializedName("news_title")
    private String mNewsTitle;

    public List<ImageList> getImageList() {
        return mImageList;
    }

    public void setImageList(List<ImageList> imageList) {
        mImageList = imageList;
    }

    public String getNewsDescription() {
        return mNewsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        mNewsDescription = newsDescription;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        mNewsTitle = newsTitle;
    }

}
