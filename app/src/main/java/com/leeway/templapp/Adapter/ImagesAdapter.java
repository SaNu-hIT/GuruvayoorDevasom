package com.leeway.templapp.Adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.leeway.templapp.R;

import java.util.ArrayList;

/**
 */

public class ImagesAdapter extends BaseAdapter {
    ArrayList<Uri> listUri;
    private Activity activity;

    public ImagesAdapter(Activity activity, ArrayList<Uri> listUri) {
        super();
        this.listUri = listUri;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listUri.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return String.valueOf(listUri.get(position));
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder {
        public ImageView imgViewFlag,img_close;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
       ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if (convertView == null) {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.news_grid_adapter, null);

            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);
            view.img_close = (ImageView) convertView.findViewById(R.id.img_close);
//            view.img_Background = (CircleImageView) convertView.findViewById(R.id.img_Background);


            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.img_close.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg1)
            {
                // list.remove(position);
                listUri.remove(position);
                notifyDataSetChanged();

            }
        });
        view.imgViewFlag.setImageURI(listUri.get(position));

        return convertView;
    }

}