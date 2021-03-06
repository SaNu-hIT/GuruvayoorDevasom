package com.leeway.templapp.Customfont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by work on 7/21/2017.
 */

public class ButtonRobotoRegular extends android.support.v7.widget.AppCompatButton {

    public ButtonRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ButtonRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonRobotoRegular(Context context) {
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