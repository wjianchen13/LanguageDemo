package com.example.languagedemo.bidi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;

public class BidiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidi);
    }

    /**
     * 基础测试
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, BidiTestActivity.class));
    }

    /**
     *
     * @param v
     */
    public void onTest2(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest3(View v) {

    }

    /**
     *
     * @param v
     */
    public void onTest4(View v) {

    }


    /**
     *
     * @param v
     */
    public void onTest5(View v) {

    }

}