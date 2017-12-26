package com.leeway.templapp.MainScreens.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.leeway.templapp.Bus.BusFactory;


/**
 * Created by work on 7/31/2017.
 */

public class BaseFragment extends Fragment {


    private boolean enableBackButton = false;
    private TextView toolBarTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusFactory.getBus().register(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);

    }
}
