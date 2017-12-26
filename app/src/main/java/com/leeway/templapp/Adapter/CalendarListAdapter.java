package com.leeway.templapp.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.leeway.templapp.R;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by Lester Oliver on 1/24/2015.
 */
@SuppressWarnings("ALL")
public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListAdapter.ViewHolder> {


    private List<String> type = new ArrayList<>();
    private List<String> content = new ArrayList<>();
    private List<String> by = new ArrayList<>();
    private final int rowLayout;
    private final Context mContext;
    private final Activity activity;
    private final ProgressDialog progressDialog;
    String PINN;
    private final String dates;
    int pos;
    private String currentData;
    private int date1;
    private int month1;
    private int year1;
    private String [] datearray;
    String parameters;

    public CalendarListAdapter(List<String> content, List<String> type, String date, Activity activity, Context context) {
        this.content = content;
        this.type = type;
        this.activity = activity;
        this.rowLayout = R.layout.eventpopup_listitem;
        this.mContext = context;
        this.dates=date;
        progressDialog = new ProgressDialog(activity);

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        System.out.println("calendar date : "+dates);

        datearray=dates.split("-");
        date1= Integer.parseInt(datearray[0]);
        month1= Integer.parseInt(datearray[1]);
        year1= Integer.parseInt(datearray[2]);

        System.out.println("calendar dates : "+currentData);

        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        try {
            holder.desc.setText(content.get(position));

            if (type.get(position).equals("Principal")){

                holder.desc.setTextColor(mContext.getResources().getColor(R.color.green));

            }else if(type.get(position).equals("Teacher")){
                holder.desc.setTextColor(mContext.getResources().getColor(R.color.blue));
            }else {

                holder.desc.setTextColor(mContext.getResources().getColor(R.color.black));
            }

            holder.calendarimgvw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent calIntent = new Intent(Intent.ACTION_EDIT);
                    calIntent.setData(CalendarContract.Events.CONTENT_URI);
                    calIntent.putExtra(CalendarContract.Events.TITLE, String.valueOf(content.get(position)));

                    GregorianCalendar calDate = new GregorianCalendar(year1,month1,date1);
                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            calDate.getTimeInMillis());
                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                            calDate.getTimeInMillis());
                    activity.startActivity(calIntent);
                    activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });


        } catch (Exception ignored) {

        }
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView desc;
        final ImageView calendarimgvw;


        public ViewHolder(View itemView) {
            super(itemView);

            desc = (TextView) itemView.findViewById(R.id.TvCalendarContent);
            calendarimgvw=(ImageView)itemView.findViewById(R.id.calendarimagview);



        }
    }

}
