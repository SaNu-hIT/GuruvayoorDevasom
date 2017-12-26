package com.leeway.templapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.Quantity;
import com.leeway.templapp.R;

import java.util.List;

/**
 * Created by intellyelabs on 10/04/17.
 */

public class CountRecyclerViewAdapter extends RecyclerView.Adapter<CountRecyclerViewAdapter.MyViewHolder> {
    List<Quantity> list;
Context context;



    public CountRecyclerViewAdapter(Context context, List<Quantity> arrayList) {
        this.list=arrayList;
        this.context=context;

        Log.e("onBindViewHolder: ",list.toString() );

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_count, parent, false);

        return new CountRecyclerViewAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        for(int i=0;i<list.size();i++)
        {
            holder.name_memmber.setText(list.get(position).getName());
            holder.illam.setText(list.get(position).getRemark());
            holder.count.setText(list.get(position).getCQuantity().toString());
            String date=list.get(position).getCDate().toString();
            Log.e( "onBindViewHolder: ",""+date );
            String year =date.substring (0, 4);
            String moth =date.substring (5, 7);
            String dates =date.substring (8, 10);
            Log.e( "onBindViewHolder: ",""+dates );
            Log.e( "onBindViewHolder: ",""+moth );
            Log.e( "onBindViewHolder: ",""+year);
            holder.year.setText(year);
            holder.date.setText(dates);
            holder.mpnth.setText(moth);

        }





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
TextView name_memmber,illam,count,date,year,mpnth;

        public MyViewHolder(View itemView) {
            super(itemView);
            name_memmber= (TextView) itemView.findViewById(R.id.name_memmber);
            illam= (TextView) itemView.findViewById(R.id.illam);
            count= (TextView) itemView.findViewById(R.id.count);
            date= (TextView) itemView.findViewById(R.id.date);
            year= (TextView) itemView.findViewById(R.id.year);
            mpnth= (TextView) itemView.findViewById(R.id.month);
        }
    }
}
