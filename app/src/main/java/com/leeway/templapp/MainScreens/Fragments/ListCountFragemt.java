package com.leeway.templapp.MainScreens.Fragments;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.templapp.Adapter.CountRecyclerViewAdapter;
import com.leeway.templapp.Adapter.ViewNewsAdapterUser;
import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.ListCountModel;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Status;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Summary;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;
import com.leeway.templapp.WrapContentLinearLayoutManager;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListCountFragemt.OnFragmentInteractionListenerNew} interface
 * to handle interaction events.
 * Use the {@link ListCountFragemt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCountFragemt extends BaseFragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ViewNewsAdapterUser itemAdapter;
    TextView current_count,current_month,total;

    private OnFragmentInteractionListenerNew mListener;
    private CountRecyclerViewAdapter mAdapter;
    SessionManager sessionManager;


    public ListCountFragemt() {
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
    public static ListCountFragemt newInstance(String param1, String param2) {
        ListCountFragemt fragment = new ListCountFragemt();
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
        sessionManager=new SessionManager(getActivity());
        ListCount();


        View rootview = inflater.inflate(R.layout.fragment_view_count, container, false);



        recyclerView = (RecyclerView) rootview.findViewById(R.id.rv_Newslist);
        current_count = (TextView) rootview.findViewById(R.id.current_count);
        current_month = (TextView) rootview.findViewById(R.id.current_month);
        total = (TextView) rootview.findViewById(R.id.total_count);
        DateFormat dateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("MMMM");
            Date date = new Date();
            Log.d("Month",dateFormat.format(date));
            current_month.setText(dateFormat.format(date));


        }
        // Inflate the layout for this fragment

        if (mListener != null) {
            mListener.onFragmentInteractionnew("JOBS");
        }
        initRecyclerView();
        return rootview;

    }

    public void initRecyclerView() {
//        showProgress("Please wait while processing..");
//        HttpRequestNewsListDetails httpRequestNewsListDetails;
//        httpRequestNewsListDetails = new HttpRequestNewsListDetails(this);
//        httpRequestNewsListDetails.GetNewsList("");


    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListenerNew) {
            mListener = (OnFragmentInteractionListenerNew) context;
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
    public interface OnFragmentInteractionListenerNew {
        // TODO: Update argument type and name
        void onFragmentInteractionnew(String uri);
    }

    private void ListCount() {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String ss=sessionManager.getUserId();

        Log.e( "User id : ",""+ss );

        Call<ListCountModel> call=apiService.ListQuantity(ss);
        String URL=call.request().url().toString();
        System.out.println("Retrofit URL2 : "+URL);
        call.enqueue(new Callback<ListCountModel>() {

            @Override
            public void onResponse(Call<ListCountModel> call, retrofit2.Response<ListCountModel> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);

                Log.i("Shanil : ", response.toString());
                Status status=response.body().getStatus();
//                Integer code=response.body().getCode().getCode();
                String code=status.getCode().toString();
                String message=status.getMessage();
                if (code.equals("200")) {


                    List<Quantity> quantity = response.body().getQuantity();

                    List<Summary> summaryList=response.body().getSummary();

                    String dailysummary=summaryList.get(0).getDailySummary().toString();
                    String mothlysummary=summaryList.get(0).getMonthsummary().toString();
                    current_count.setText(dailysummary);
                    total.setText(mothlysummary);


                    Log.e( "dailysummary: ",dailysummary );
                    Log.e( "mothlysummary: ",mothlysummary );

                    if (quantity.size() > 0)
                    {
                        recyclerView.setHasFixedSize(true);
//                        mLayoutManager = new LinearLayoutManager(getActivity());
//                        recyclerView.setLayoutManager(mLayoutManager);
//
//                        recyclerView.setAdapter(itemAdapter);
//


                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setNestedScrollingEnabled(false);
                        mAdapter = new CountRecyclerViewAdapter(getActivity(),quantity);
                        recyclerView.setAdapter(mAdapter);

                    }                           else {



                    }
                }
                else
                {
//                    Toast.makeText(getActivity(), "Parsing error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListCountModel> call, Throwable t) {

                t.printStackTrace();
            }
        });



    }
}
