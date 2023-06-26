package com.example.languagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.languagedemo.ar.BaseArActivity;
import com.example.languagedemo.bidi.BidiActivity;
import com.example.languagedemo.language.LocalManageUtil;

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

    public void onSystem(View v) {
        int system = LocalManageUtil.getSystemSelectLanguage(this);
        System.out.println("=====================> system: " + system);
    }

    public void onAr(View v) {
        startActivity(new Intent(this, BaseArActivity.class));
    }

    public void onBidi(View v) {
        startActivity(new Intent(this, BidiActivity.class));
    }

}