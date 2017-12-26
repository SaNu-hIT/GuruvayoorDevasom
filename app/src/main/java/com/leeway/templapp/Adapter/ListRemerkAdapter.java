package com.leeway.templapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leeway.templapp.Connection.Model.ListRemarkModel.Remarkinfo;
import com.leeway.templapp.R;

import java.util.List;

/**
 * Created by intellyelabs on 10/04/17.
 */

public class ListRemerkAdapter extends RecyclerView.Adapter<ListRemerkAdapter.MyViewHolder> {
    List<Remarkinfo> list;
Context context;



    public ListRemerkAdapter(Context context, List<Remarkinfo> arrayList) {
        this.list=arrayList;
        this.context=context;

        Log.e("onBindViewHolder: ",list.toString() );

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_remark, parent, false);

        return new ListRemerkAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        for(int i=0;i<list.size();i++)
        {
            holder.name_memmber.setText(list.get(position).getName());
            holder.illam.setText(list.get(position).getContent().toString());
            holder.date_id.setText(list.get(position).getRDate().toString());
        }





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
TextView name_memmber,illam,date_id;

        public MyViewHolder(View itemView) {
            super(itemView);
            name_memmber= (TextView) itemView.findViewById(R.id.name_memmber);
            illam= (TextView) itemView.findViewById(R.id.remak_id);
            date_id= (TextView) itemView.findViewById(R.id.date_id);
        }
    }
}
