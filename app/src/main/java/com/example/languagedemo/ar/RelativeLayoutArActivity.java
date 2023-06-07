package com.example.languagedemo.ar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;

public class RelativeLayoutArActivity extends BaseActivity {

    private ConstraintLayout clFrame;
    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout_ar);
        tvTest1 = findViewById(R.id.tv_test1);
        clFrame = findViewById(R.id.cl_frame);
    }

    public void onTest(View v) {

    }


}