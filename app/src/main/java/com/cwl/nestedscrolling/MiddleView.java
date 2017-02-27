package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class MiddleView extends FrameLayout implements NestedScrollingParent ,NestedScrollingChild{

    NestedScrollingParentHelper parentHelper;
    private NestedScrollingChildHelper childHelper;
    public MiddleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentHelper=new NestedScrollingParentHelper(this);
        childHelper=new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    //parent

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes){
        startNestedScroll(nestedScrollAxes);
        return true;
    }


    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes){
        parentHelper.onNestedScrollAccepted(child,target,nestedScrollAxes);
    }


    public void onStopNestedScroll(View target){
        parentHelper.onStopNestedScroll(target);
        stopNestedScroll();
    }


    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed){

    }


    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed){
        //这里没有关注父view消费情况
        dispatchNestedPreScroll(dx,dy,null,null);

        //移动的offset为滑动距离
        offsetLeftAndRight(dx);
        offsetTopAndBottom(dy);
    }


    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed){
        return false;
    }


    public boolean onNestedPreFling(View target, float velocityX, float velocityY){
        return false;
    }


    public int getNestedScrollAxes(){
        return parentHelper.getNestedScrollAxes();
    }


    //child
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
