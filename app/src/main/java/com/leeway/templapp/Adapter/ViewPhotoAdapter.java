package com.leeway.templapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leeway.templapp.Bus.BusFactory;
import com.leeway.templapp.Bus.Events.DeletePhotoEvent;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.SidebarImageList;
import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;

import java.util.List;

/**
 * Created by work on 7/31/2017.
 */

public class ViewPhotoAdapter extends RecyclerView.Adapter<ViewPhotoAdapter.RecyclerViewHolders> {

    List<SidebarImageList> itemList;
    private Context context;

    public ViewPhotoAdapter(Context context, List<SidebarImageList> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_list_adapter, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        holder.countryName.setText(itemList.get(position).getSidebarCaption());
        Glide.with(context).load(itemList.get(position).getSidebarImageUrl())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.ic_placeholder_no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.countryPhoto);
        SessionManager sessionManager = new SessionManager(context);
        String role = sessionManager.getRole();
        if (!role.equals("Admin")) holder.img_close.setVisibility(View.INVISIBLE);
        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BusFactory.getBus().post(new DeletePhotoEvent(itemList.get(position).getSidebarImageId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView countryName;
        public ImageView countryPhoto, img_close;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
            img_close = (ImageView) itemView.findViewById(R.id.img_close);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_image_photos);

            ImageView img_fullView = (ImageView) dialog.findViewById(R.id.img_fullView);
            Glide.with(context).load(itemList.get( getPosition()).getSidebarImageUrl())
                    .thumbnail(0.5f)
                    .crossFade()
                    .error(R.drawable.ic_placeholder_no_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_fullView);

            Button dialogButton = (Button) dialog.findViewById(R.id.img_delete);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

}