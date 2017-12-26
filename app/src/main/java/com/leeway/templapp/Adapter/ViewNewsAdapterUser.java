package com.leeway.templapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leeway.templapp.Connection.NewsList;
import com.leeway.templapp.External.ClickableViewPager;
import com.leeway.templapp.MainScreens.Activity.BaseActivity;
import com.leeway.templapp.R;

import java.util.List;

/**
 * Created by work on 7/31/2017.
 */

public class ViewNewsAdapterUser extends RecyclerView.Adapter<ViewNewsAdapterUser.NavigationViewHolder> {
    BaseActivity baseActivity;
    private Context context;
    List<NewsList> listNewsItem;

    public ViewNewsAdapterUser(Context context, List<NewsList> listNewsItem) {
        this.context = context;
        this.listNewsItem = listNewsItem;
        baseActivity = new BaseActivity();

    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view_news_user, parent, false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NavigationViewHolder holder, final int positionParent) {
        holder.setIsRecyclable(false);
        holder.tv_Titile.setText(listNewsItem.get(positionParent).getTitle());
        holder.tv_description.setText(listNewsItem.get(positionParent).getDescription());
        holder.img_LeftSwipe.setVisibility(View.INVISIBLE);


        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(context, listNewsItem.get(positionParent).getNewsImage());
        holder.mViewPager.setAdapter(customPagerAdapter);
        holder.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int positionSlider) {
                if (positionSlider == 0) {
                    holder.img_LeftSwipe.setVisibility(View.INVISIBLE);
                } else

                {
                    holder.img_LeftSwipe.setVisibility(View.VISIBLE);
                }

                if (listNewsItem.get(positionParent).getNewsImage().size() - 1 == positionSlider) {
                    holder.img_RightSwipe.setVisibility(View.INVISIBLE);

                } else {
                    holder.img_RightSwipe.setVisibility(View.VISIBLE);
                }

            }
        });
        holder.mViewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.view_image_photos);

                ImageView img_fullView = (ImageView) dialog.findViewById(R.id.img_fullView);
                Glide.with(context).load(listNewsItem.get( positionParent).getNewsImage().get(position).getNewsImageurl())
                        .thumbnail(0.5f)
                        .error(R.drawable.ic_placeholder_no_image)
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
        });
        switch (listNewsItem.get(positionParent).getNewsImage().size()) {
            case 0:
                holder.lnr_side_buton.setVisibility(View.GONE);
                break;
            case 1:
                holder.lnr_side_buton.setVisibility(View.GONE);
                break;
        }


        holder.img_LeftSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mViewPager.arrowScroll(View.FOCUS_LEFT);
            }
        });


        holder.img_RightSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mViewPager.arrowScroll(View.FOCUS_RIGHT);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNewsItem.size();
    }


    class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_Titile, tv_description;
        private ImageView img_LeftSwipe;
        private ImageView img_RightSwipe;
        private LinearLayout lnr_side_buton;

        ClickableViewPager mViewPager;

        public NavigationViewHolder(View itemView) {
            super(itemView);
            tv_Titile = (TextView) itemView.findViewById(R.id.tv_Titile);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            mViewPager = (ClickableViewPager) itemView.findViewById(R.id.pager);
            img_LeftSwipe = (ImageView) itemView.findViewById(R.id.img_LeftSwipe);
            img_RightSwipe = (ImageView) itemView.findViewById(R.id.img_RightSwipe);
            lnr_side_buton = (LinearLayout) itemView.findViewById(R.id.frm_side);
        }

        @Override
        public void onClick(View v) {

        }
    }
}