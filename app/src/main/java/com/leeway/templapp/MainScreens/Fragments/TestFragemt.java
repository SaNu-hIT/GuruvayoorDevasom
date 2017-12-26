package com.leeway.templapp.MainScreens.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeway.templapp.Adapter.ViewNewsAdapterUser;
import com.leeway.templapp.Bus.Events.RefreshNewsUserSideEvent;
import com.leeway.templapp.Connection.HttpRequestNewsListDetails;
import com.leeway.templapp.Connection.NewsList;
import com.leeway.templapp.Connection.OnHttpResponseListNewsDetails;
import com.leeway.templapp.R;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragemt.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragemt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragemt extends BaseFragment implements OnHttpResponseListNewsDetails {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView rv_Newslist;
    private LinearLayoutManager mLayoutManager;
    private ViewNewsAdapterUser itemAdapter;

    private OnFragmentInteractionListener mListener;

    public TestFragemt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragemt.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragemt newInstance(String param1, String param2) {
        TestFragemt fragment = new TestFragemt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootview = inflater.inflate(R.layout.fragment_view_news, container, false);
        rv_Newslist = (RecyclerView) rootview.findViewById(R.id.rv_Newslist);
        // Inflate the layout for this fragment

        if (mListener != null) {
            mListener.onFragmentInteraction("NEWS");
        }
        initRecyclerView();
        return rootview;

    }

    public void initRecyclerView() {
//        showProgress("Please wait while processing..");
        HttpRequestNewsListDetails httpRequestNewsListDetails;
        httpRequestNewsListDetails = new HttpRequestNewsListDetails(this);
        httpRequestNewsListDetails.GetNewsList("");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnSuccessNewsDetailList(boolean stautus, List<NewsList> newsLists) {
        rv_Newslist.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_Newslist.setLayoutManager(mLayoutManager);
        itemAdapter = new ViewNewsAdapterUser(getActivity(), newsLists);
        rv_Newslist.setAdapter(itemAdapter);
    }

    @Subscribe
    public void RefreshEvent(RefreshNewsUserSideEvent event) {
//        showProgress("Please wait while processing..");
        HttpRequestNewsListDetails httpRequestNewsListDetails = new HttpRequestNewsListDetails(this);
        httpRequestNewsListDetails.GetNewsList("");
    }

    @Override
    public void OnFaildNewsDetailList(String errorMessage) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
}
