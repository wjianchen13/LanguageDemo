package com.example.languagedemo.ar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;
import com.example.languagedemo.utils.Utils;

public class TextViewArActivity extends BaseActivity {

    private ConstraintLayout clFrame;
    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_ar);
        tvTest1 = findViewById(R.id.tv_test1);
        clFrame = findViewById(R.id.cl_frame);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onTest(View v) {
        int direction = clFrame.getLayoutDirection();
        Utils.log("direction: " + direction);
        int direction1 = tvTest1.getTextDirection();
        Utils.log("direction1: " + direction1);
//        tvTest1.setLayoutDirection();
    }


}