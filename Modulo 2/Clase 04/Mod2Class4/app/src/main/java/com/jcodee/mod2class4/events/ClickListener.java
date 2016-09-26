package com.jcodee.mod2class4.events;

import android.view.View;

/**
 * Author: johannfjs
 * Date: 10/9/16
 * Time: 11:16
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}