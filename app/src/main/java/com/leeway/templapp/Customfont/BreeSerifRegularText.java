package com.leeway.templapp.Customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by intellyelabs on 15/08/17.
 */

public class BreeSerifRegularText extends android.support.v7.widget.AppCompatTextView {

    public BreeSerifRegularText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BreeSerifRegularText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BreeSerifRegularText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BreeSerif-Regular.ttf");
            setTypeface(tf);
        }
    }

}