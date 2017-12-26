package com.leeway.templapp.Connection;

import com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo;

import java.util.List;

/**
 * Created by intellyelabs on 29/06/17.
 */

public interface OnHttpRespoceForRemark {
    public void onHttpEventSuccess(List<Remarkinfo> eventsInfoList);
    public void onHttpEventFailed(String message);
}
