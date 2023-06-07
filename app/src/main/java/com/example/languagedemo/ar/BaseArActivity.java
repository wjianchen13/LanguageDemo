package com.example.languagedemo.ar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;

public class BaseArActivity extends BaseActivity {

    private ConstraintLayout clFrame;
    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ar);
        tvTest1 = findViewById(R.id.tv_test1);
        clFrame = findViewById(R.id.cl_frame);
    }

    /**
     * 测试TextView
     * @param v
     */
    public void onTextView(View v) {
        startActivity(new Intent(this, TextViewArActivity.class));
    }

    /**
     * 判断是否是RTL
     * @param v
     */
    public void onRtl(View v) {
        boolean isRtl = isRtl();
        System.out.println("=========================> isRtl: " + isRtl);
    }

    public boolean isRtl() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(
                getContext().getResources().getConfiguration().locale) == ViewCompat.LAYOUT_DIRECTION_RTL;
    }

    /**
     * 测试RelativeLayout
     * @param v
     */
    public void onTest3(View v) {
        startActivity(new Intent(this, RelativeLayoutArActivity.class));
    }



}