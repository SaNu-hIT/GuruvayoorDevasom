
package com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SidebarImageList {

    @SerializedName("Sidebar_Caption")
    private String mSidebarCaption;
    @SerializedName("Sidebar_ImageId")
    private String mSidebarImageId;
    @SerializedName("SidebarImageUrl")
    private String mSidebarImageUrl;

    public String getSidebarCaption() {
        return mSidebarCaption;
    }

    public void setSidebarCaption(String SidebarCaption) {
        mSidebarCaption = SidebarCaption;
    }

    public String getSidebarImageId() {
        return mSidebarImageId;
    }

    public void setSidebarImageId(String SidebarImageId) {
        mSidebarImageId = SidebarImageId;
    }

    public String getSidebarImageUrl() {
        return mSidebarImageUrl;
    }

    public void setSidebarImageUrl(String SidebarImageUrl) {
        mSidebarImageUrl = SidebarImageUrl;
    }

}
