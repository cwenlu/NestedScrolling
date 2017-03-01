package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class TestView extends View {

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        int pointerCount = event.getPointerCount();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                scrollTo(50,50);
                System.out.println(event.getX()+"--self--"+event.getY());
                System.out.println(event.getRawX()+"--screen--"+event.getRawY());
                System.out.println("trans---"+getTranslationX()+","+getTranslationY());

                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(5f+getTranslationX());
                setTranslationY(-5f+getTranslationY());
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(getX()+"-x/y-"+getY());

                System.out.println(getScrollX()+"--scroll--"+getScrollY());
                break;
        }


        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //getLeft,....
        System.out.println(left+","+top+","+right+","+bottom);
    }
}
