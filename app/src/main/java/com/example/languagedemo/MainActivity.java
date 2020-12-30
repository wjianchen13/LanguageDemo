package com.example.languagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSetting(View v) {
        startActivity(new Intent(this, SettingActivity.class));
    }
    
    public void onJump(View v) {
        startActivity(new Intent(this, TestActivity.class));
    }
}