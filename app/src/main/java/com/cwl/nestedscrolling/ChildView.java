package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewParent;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class ChildView extends View implements NestedScrollingChild {

    private NestedScrollingChildHelper childHelper;

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        childHelper=new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);

    }




    VelocityTracker velocityTracker = null;
    int[] consumed=new int[2];
    int[] offsetInWindow=new int[2];
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float startY = 0;

        if(velocityTracker!=null){
            velocityTracker.addMovement(event);
        }
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startY=event.getY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);

                velocityTracker=VelocityTracker.obtain();

                break;
            case MotionEvent.ACTION_MOVE:
                int distanceY=(int)(event.getY()-startY);
                //返回true，说明父view进行了处理
                if (dispatchNestedPreScroll(0,distanceY,consumed,offsetInWindow)) {
                    //父view指定消费x=50，y=100
                    Log.i("tag",consumed[0]+"+++"+consumed[1]);

                    //启动nestedscrolling的target view没有发生窗体位置变化
                    Log.i("tag",offsetInWindow[0]+"+++"+offsetInWindow[1]);
                }

                //返回true，说明还有可消费scrolling，不关心窗体位置，传null
                dispatchNestedScroll(100,100,50,50,null);

                velocityTracker.computeCurrentVelocity(1000);
                float xVelocity = VelocityTrackerCompat.getXVelocity(velocityTracker, event.getPointerId(event.getActionIndex()));

                dispatchNestedPreFling(xVelocity,0);

                dispatchNestedFling(xVelocity,0,true);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                velocityTracker.clear();
                velocityTracker.recycle();
                velocityTracker=null;
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
