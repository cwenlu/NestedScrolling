package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class ExternalView extends FrameLayout implements NestedScrollingParent{
    NestedScrollingParentHelper parentHelper;
    public ExternalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentHelper=new NestedScrollingParentHelper(this);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes){
        return true;
    }


    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes){
        parentHelper.onNestedScrollAccepted(child,target,nestedScrollAxes);
    }


    public void onStopNestedScroll(View target){
        parentHelper.onStopNestedScroll(target);
    }


    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed){

    }


    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed){
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
}
