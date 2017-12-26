package com.leeway.templapp.MainScreens.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leeway.templapp.Customfont.TextViewRobotoRegular;
import com.leeway.templapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EventsActivity extends BaseActivity {
    @BindView(R.id.tv_toolbar_heading)
    TextViewRobotoRegular tv_toolbar_heading;
    @BindView(R.id.img_eventIcon)
    CircleImageView img_eventIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        String headingName = getIntent().getExtras().getString("titleFunction");
        String eventIcon = getIntent().getExtras().getString("eventIcon");
        Glide.with(getApplicationContext()).load(eventIcon)
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.ic_placeholder_no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_eventIcon);

        tv_toolbar_heading.setText(headingName);


    }

    @OnClick(R.id.img_ButtonBack)
    public void NavigateBack(ImageView button) {
        finish();
    }

}
