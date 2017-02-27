package com.cwl.nestedscrolling;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

import static android.R.attr.startX;
import static android.R.attr.startY;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class InternalView extends View implements NestedScrollingChild{
    private NestedScrollingChildHelper childHelper;
    public InternalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper=new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);


    }

    int[] offsetInWindow=new int[2];
    int lastX ;
    int lastY ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL | ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:

                int distanceX= x-lastX;
                int distanceY= y-lastY;
                lastX = x;
                lastY = y;
                dispatchNestedPreScroll(distanceX,distanceY,null,offsetInWindow);

                //关注了parent view的窗体偏移，移动的是parent view距离屏幕边缘的距离
                offsetLeftAndRight(offsetInWindow[0]);
                offsetTopAndBottom(offsetInWindow[1]);

                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return /*super.onTouchEvent(event)*/true;
    }

    public void setNestedScrollingEnabled(boolean enabled){
        childHelper.setNestedScrollingEnabled(enabled);
    }


    public boolean isNestedScrollingEnabled(){
        return childHelper.isNestedScrollingEnabled();
    }


    public boolean startNestedScroll(int axes){
        return childHelper.startNestedScroll(axes);
    }


    public void stopNestedScroll(){
        childHelper.stopNestedScroll();
    }


    public boolean hasNestedScrollingParent(){
        return childHelper.hasNestedScrollingParent();
    }


    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow){
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed,dxUnconsumed, dyUnconsumed, offsetInWindow);
    }


    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow){
        return childHelper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow);
    }


    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed){
        return childHelper.dispatchNestedFling(velocityX,velocityY,consumed);
    }


    public boolean dispatchNestedPreFling(float velocityX, float velocityY){
        return childHelper.dispatchNestedPreFling(velocityX,velocityY);
    }


}
