package com.jcodee.mod2class2.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jcodee.mod2class2.MainActivity;

/**
 * Author: johannfjs
 * Date: 10/9/16
 * Time: 11:19
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public RecyclerTouchListener(Context context,
                                 final RecyclerView recyclerView,
                                 final ClickListener clickListener) {
        //Setteamos el valor del ClickListener
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            //Verificamos que se haya dado un simple toque en el item
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            //Validamos si es que se ha seleccionado por un periodo largo el item
            @Override
            public void onLongPress(MotionEvent e) {
                //Verificamos si el lugar de selección es dentro de un item
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                //Validamos que la vista sea correcta y que la opción del clickListener no sea nula
                if (view != null && clickListener != null) {
                    //Ejecutamos el comando onLongClick
                    clickListener.onLongClick(view, recyclerView.getChildPosition(view));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if (view != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(view, rv.getChildPosition(view));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
