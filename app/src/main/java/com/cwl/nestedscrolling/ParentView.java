package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class ParentView extends LinearLayout implements NestedScrollingParent{

    private NestedScrollingParentHelper parentHelper;
    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parentHelper=new NestedScrollingParentHelper(this);
    }

    /**
     *
     * @param child 协同处理的父view的子view
     * @param target 启动nestedscrolling的目标view
     * @param nestedScrollAxes 滚动轴常量
     * @return true 协同处理，false不协同处理
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i("tag",child.toString()+"---"+target.toString()+"---");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.i("tag","onNestedScrollAccepted");
        parentHelper.onNestedScrollAccepted(child,target,nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.i("tag","onStopNestedScroll");
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("tag",dxConsumed+"--"+dyConsumed+"---"+dxUnconsumed+"--"+dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] cosumed) {
        Log.i("tag",dx+"---"+dy);
        //消费50,100
        cosumed[0]=50;
        cosumed[1]=100;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("tag",velocityX+"--"+consumed);
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i("tag",velocityX+"");
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }
}
