package com.leeway.templapp.Interfaces;


import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.SidebarImageList;

import java.util.List;

/**
 * Created by work on 7/28/2017.
 */

public interface OnHttpResponsePhotoImageList {
    void OnSuccessPhotoImageList(boolean stautus, List<SidebarImageList> listModel);
    void OnFailedPhotoImageList(String errorMessage);
}
