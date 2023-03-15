package com.example.languagedemo.ar;

import android.os.Bundle;
import android.view.View;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;
import com.example.languagedemo.language.LocalManageUtil;

public class BaseArActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_ar);
    }

    public void onEn(View v) {

    }


}