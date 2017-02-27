package com.cwl.nestedscrolling;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

public class ViewUseActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    AppBarLayout.OnOffsetChangedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_use);

        appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        listener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i("tag", verticalOffset + "");
            }
        };
        appBarLayout.addOnOffsetChangedListener(listener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        appBarLayout.removeOnOffsetChangedListener(listener);
    }
}
