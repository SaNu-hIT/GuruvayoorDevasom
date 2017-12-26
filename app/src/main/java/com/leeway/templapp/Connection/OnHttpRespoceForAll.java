package com.leeway.templapp.Connection;

import com.leeway.templapp.Retrofit.ModelClass.HomeBean.INFO;

/**
 * Created by intellyelabs on 29/06/17.
 */

public interface OnHttpRespoceForAll {
    public void onHttpEventSuccess(INFO info);
    public void onHttpEventFailed(String message);
}
