package com.example.languagedemo.ar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;

/**
 * 阿语图标测试
 */
public class ImageArActivity extends BaseActivity {

    private ConstraintLayout clFrame;
    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_ar);
        tvTest1 = findViewById(R.id.tv_test1);
        clFrame = findViewById(R.id.cl_frame);
    }

    public void onTest(View v) {

    }


}