package com.leeway.templapp.Customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Intellyze105 on 14-07-2017.
 */

public class EditTextRobotoRegular extends android.support.v7.widget.AppCompatEditText {

    public EditTextRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextRobotoRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            setTypeface(tf);
        }
    }

}