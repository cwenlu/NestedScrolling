package com.cwl.nestedscrolling;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class SimpleBehavior extends CoordinatorLayout.Behavior {

    public SimpleBehavior(){}

    public SimpleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if(dependency.getId()==R.id.decy){
            Log.i("tag",child+"--depends--"+dependency);

        }

        return dependency.getId()==R.id.decy;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.i("tag",child+"--change--"+dependency);
        child.setX(dependency.getX());
        child.setY(dependency.getBottom()+dependency.getTranslationY());
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        Log.i("tag",child+"--remove--"+dependency);
        super.onDependentViewRemoved(parent, child, dependency);
    }

    //一般返回false采用默认布局，child 表示behavior
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        if (child.getId()== R.id.tv) {
            ((TextView) child).setText("aldfaf");

            child.layout(500,500,500+child.getMeasuredWidth(),500+child.getMeasuredHeight());
        }
        return /*super.onLayoutChild(parent, child, layoutDirection)*/true;
    }
}
