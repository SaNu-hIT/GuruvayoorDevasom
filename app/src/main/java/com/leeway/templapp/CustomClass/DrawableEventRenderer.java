package com.leeway.templapp.CustomClass;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.tibolte.agendacalendarview.render.EventRenderer;
import com.leeway.templapp.Connection.Model.DrawableCalendarEvent;
import com.leeway.templapp.R;


/**
 * Created by Intellyze105 on 14-07-2017.
 */

public class DrawableEventRenderer extends EventRenderer<DrawableCalendarEvent> {

    // region Class - EventRenderer

    @Override
    public void render(View view, DrawableCalendarEvent event) {
//        ImageView imageView = (ImageView) view.findViewById(R.id.view_agenda_event_image);
        TextView txtTitle = (TextView) view.findViewById(com.github.tibolte.agendacalendarview.R.id.view_agenda_event_title);
        TextView description = (TextView) view.findViewById(R.id.description);
        LinearLayout descriptionContainer = (LinearLayout) view.findViewById(com.github.tibolte.agendacalendarview.R.id.view_agenda_event_description_container);


        descriptionContainer.setVisibility(View.VISIBLE);
//        Glide.with(view.getContext()).load(event.getDrawableId())
//                .crossFade()
//                .into(imageView);
//        imageView.setImageDrawable(view.getContext().getResources().getDrawable(event.getDrawableId()));

        txtTitle.setTextColor(view.getResources().getColor(android.R.color.white));

        txtTitle.setText(event.getTitle());
        description.setText(event.getDescription());


//        if (event.getTitle().equals("New event")) {
//            txtTitle.setVisibility(View.GONE);
////            imageView.setVisibility(View.GONE);
//        } else {
//            txtTitle.setTextColor(view.getResources().getColor(com.github.tibolte.agendacalendarview.R.color.theme_text_icons));
//        }
        descriptionContainer.setBackgroundColor(event.getColor());

    }

    @Override
    public int getEventLayout() {
        return R.layout.view_agenda_drawable_event;
    }

    @Override
    public Class<DrawableCalendarEvent> getRenderType() {
        return DrawableCalendarEvent.class;
    }

    // endregion
}
