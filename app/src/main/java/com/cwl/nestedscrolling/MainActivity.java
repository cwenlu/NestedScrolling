package com.cwl.nestedscrolling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void simpleAction(View v){
        startActivity(new Intent(this,SimpleActivity.class));
    }

    public void nestedAction(View v){
        startActivity(new Intent(this,NestedActivity.class));
    }

    public void viewUseAction(View v){
        startActivity(new Intent(this,ViewUseActivity.class));
    }
}
