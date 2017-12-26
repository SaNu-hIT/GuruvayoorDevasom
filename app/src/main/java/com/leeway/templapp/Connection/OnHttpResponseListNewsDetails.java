package com.leeway.templapp.Connection;

import java.util.List;

/**
 * Created by work on 7/26/2017.
 */

public interface OnHttpResponseListNewsDetails {
    void OnSuccessNewsDetailList(boolean stautus, List<NewsList> newsLists);
    void OnFaildNewsDetailList(String errorMessage);
}
