package com.leeway.templapp.Connection.Model;

import com.leeway.templapp.Connection.Model.ListSchedules.Sheduleinfo;

import java.util.List;

/**
 * Created by intellyelabs on 29/06/17.
 */

public interface OnHttpRespoceForEvents {
    public void onHttpEventSuccess(List<Sheduleinfo> eventsInfoList);
    public void onHttpEventFailed(String message);
}
