package com.cwl.nestedscrolling;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BottomSheetActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                System.out.println(newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

//    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    public void controlAction(View view){
        int state = bottomSheetBehavior.getState();
        switch (state){
            case BottomSheetBehavior.STATE_SETTLING:
                System.out.println("settling");

                break;
            case BottomSheetBehavior.STATE_COLLAPSED:
                System.out.println("collapsed");

                break;
            case BottomSheetBehavior.STATE_EXPANDED:
                System.out.println("expanded");

                break;
            case BottomSheetBehavior.STATE_HIDDEN:
                System.out.println("hidden");

                break;
        }
    }


    BottomSheetDialog bottomSheetDialog=null;
    public void dialogSheetAction(View view){
        if(bottomSheetDialog==null){
            bottomSheetDialog=new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(R.layout.dialog_sheet);

            View v = bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(v);
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });

        }
        if(bottomSheetDialog.isShowing()){
            bottomSheetDialog.dismiss();
        }else{
            bottomSheetDialog.show();
        }

    }



}
