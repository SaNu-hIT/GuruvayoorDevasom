package com.leeway.templapp.Customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Intellyze105 on 14-07-2017.
 */

public class EditTextRobotoBold extends android.support.v7.widget.AppCompatEditText {

    public EditTextRobotoBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextRobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextRobotoBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
            setTypeface(tf);
        }
    }

}