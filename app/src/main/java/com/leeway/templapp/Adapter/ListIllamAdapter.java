package com.leeway.templapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leeway.templapp.Api.ApiClient;
import com.leeway.templapp.Api.ApiInterface;
import com.leeway.templapp.Interfaces.OnHttpResponseDeleteIllam;
import com.leeway.templapp.R;
import com.leeway.templapp.Retrofit.DeleteIllam.DeleteDetails;
import com.leeway.templapp.Retrofit.DeleteIllam.DeleteStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by Lester Oliver on 1/24/2015.
 */
public class ListIllamAdapter extends RecyclerView.Adapter<ListIllamAdapter.ViewHolder> {

    private List<String> illam_name = new ArrayList<>();
    private List<Integer> Illam_id = new ArrayList<>();
    private List<String> illam_address = new ArrayList<>();
    OnHttpResponseDeleteIllam onHttpResponseDeleteIllam;
    private final int rowLayout;
    private final Context mContext;
    private final Activity activity;
    String PINN;
    int pos;
    private Dialog Dlg;
    String parameters;

    public ListIllamAdapter(List<String> illam_name, List<Integer> Illam_id, List<String> illam_address, Activity activity, Context context) {
        this.illam_name = illam_name;
        this.Illam_id = Illam_id;
        this.illam_address = illam_address;

        this.activity = activity;
        this.rowLayout = R.layout.item_illam_layout;
        this.mContext = context;




    }
public void   setListener(OnHttpResponseDeleteIllam listener)
{
    this.onHttpResponseDeleteIllam=listener;
}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.NameTxt.setText(""+illam_name.get(position));



        holder.LayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Popup(illam_name.get(position),Illam_id.get(position));


            }
        });


    }
    private void Popup(final String Name, final Integer id)
    {
        System.out.println("SHANIL : ");
        Dlg = new Dialog(activity);
        Dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dlg.setContentView(R.layout.confirm);
        Dlg.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Dlg.show();

        LinearLayout Confirm=(LinearLayout)Dlg.findViewById(R.id.confirmlay);
        LinearLayout Cancel=(LinearLayout)Dlg.findViewById(R.id.cancellay);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteIllam(Name,id);
                Dlg.cancel();

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dlg.cancel();

            }
        });



    }
    private void DeleteIllam(String Name,Integer id) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<DeleteDetails> call1=apiService.getdelete(String.valueOf(id));
        Log.e( "Name: ","na"+Name );
        Log.e( "id: ","ids"+id );
        call1.enqueue(new Callback<DeleteDetails>() {
            @Override
            public void onResponse(Call<DeleteDetails> call, retrofit2.Response<DeleteDetails> response) {

                String URL = call.request().url().toString();
                System.out.println("Retrofit URL : " + URL);

                DeleteStatus otpstatus = response.body().getStatus();
                String status = String.valueOf(otpstatus.getCode());
                Log.e( "status: ",""+status );
                if (status.equals("200")) {

                    Toast.makeText(mContext, "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                    Intent in=new Intent(mContext, ListAllIllam.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(in);

                    refresh();


                } else {
                    Toast.makeText(mContext, "Cannot Deleted", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DeleteDetails> call, Throwable t) {
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();

            }




        });



    }

    private void refresh() {
        Log.e( "refresh: ", "onHttpResponseDeleteIllam");
        onHttpResponseDeleteIllam.OnSuccessDelete();
    }

    @Override
    public int getItemCount() {
        return Illam_id.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView NameTxt;
        RelativeLayout LayoutMain;


        public ViewHolder(View itemView) {
            super(itemView);

            NameTxt=(TextView)itemView.findViewById(R.id.nameid);
            LayoutMain=(RelativeLayout)itemView.findViewById(R.id.lay1);

        }
    }

}
