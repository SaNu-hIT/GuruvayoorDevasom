package com.leeway.templapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.Bus.Events.DemoEdit;
import com.leeway.templapp.R;

import java.util.ArrayList;

/**
 * Created by work on 7/27/2017.
 */

public class EditNewsAdapterImage extends BaseAdapter {
    //    ArrayList<Uri> listUri;
    private Activity activity;
    ArrayList<String> imgListUrl;
    ArrayList<String> imgListId;

    public EditNewsAdapterImage(Activity activity, ArrayList<String> imgListUrl, ArrayList<String> imgListId) {
        super();
        this.imgListUrl = imgListUrl;
        this.imgListId = imgListId;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imgListUrl.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return String.valueOf(imgListUrl.get(position));
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder {
        public ImageView imgViewFlag, img_close;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImagesAdapter.ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if (convertView == null) {
            view = new ImagesAdapter.ViewHolder();
            convertView = inflator.inflate(R.layout.news_grid_adapter, null);

            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);
            view.img_close = (ImageView) convertView.findViewById(R.id.img_close);
//            view.img_Background = (CircleImageView) convertView.findViewById(R.id.img_Background);


            convertView.setTag(view);
        } else {
            view = (ImagesAdapter.ViewHolder) convertView.getTag();
        }
        view.img_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg1) {
                // list.remove(position);
                if (imgListId.get(position).equals("local")) {
                    imgListUrl.remove(position);
                    imgListId.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(activity, "removed", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        BusFactory.getBus().post(new DemoEdit(imgListId.get(position)));
                        imgListUrl.remove(position);
                        imgListId.remove(position);
                        notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(activity, "Please try Again", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        Glide.with(activity).load(imgListUrl.get(position))
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.ic_placeholder_no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.imgViewFlag);

        return convertView;
    }

}