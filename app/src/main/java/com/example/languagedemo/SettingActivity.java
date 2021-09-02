package com.example.languagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.languagedemo.language.LocalManageUtil;

public class SettingActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onEn(View v) {
        selectLanguage(LocalManageUtil.ENGLISH);
    }

    public void onIn(View v) {
        selectLanguage(LocalManageUtil.INDONESIAN);
    }

    public void onTh(View v) {
        selectLanguage(LocalManageUtil.THAI);
    }
    
    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);        // 将整个任务栈清空
        startActivity(i);
    }
}