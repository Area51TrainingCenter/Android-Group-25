package com.jcodee.mod1class4_2.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 11:11
 */
public class EditTextCustom extends EditText {
    public EditTextCustom(Context context) {
        super(context);
        init(context);
    }

    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface typeface = Typeface.createFromAsset(
                context.getAssets(), "fonts/Raleway_Regular.ttf");
        setTypeface(typeface);
    }
}
