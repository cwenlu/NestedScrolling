package com.cwl.nestedscrolling;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class BehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        View decy = findViewById(R.id.decy);
        decy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    v.setX(event.getRawX()-v.getWidth()/2);
                    v.setY(event.getRawY()-v.getHeight()/2);
                    return true;
                }
                return false;
            }
        });

        View bc = findViewById(R.id.bind_ck);

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CoordinatorLayout.LayoutParams) findViewById(R.id.ck).getLayoutParams()).setBehavior(new SimpleBehavior());
                ((CoordinatorLayout.LayoutParams) findViewById(R.id.tv).getLayoutParams()).setBehavior(null);
            }
        });

    }
}
