package com.jcodee.mod1class4_2.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Author: johannfjs
 * Date: 20/8/16
 * Time: 11:04
 */
public class ButtonCustom extends Button {
    public ButtonCustom(Context context) {
        super(context);
        init(context);
    }

    public ButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //Cambiar el tipo de letra
        Typeface typeface =
                Typeface.createFromAsset(
                        context.getAssets(), "fonts/AmaticSC_Regular.ttf");
        //Asignamos el tipo de letra al componente
        setTypeface(typeface);
    }
}
