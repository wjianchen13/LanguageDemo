package com.example.languagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.languagedemo.language.LocalManageUtil;

public class SettingActivity extends BaseActivity {

    /**
     * 英语
     */
    public static final int ENGLISH = 1;

    /**
     * 印尼语
     */
    public static final int INDONESIAN = 2;

    /**
     * 泰语
     */
    public static final int THAI = 3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onEn(View v) {
        selectLanguage(ENGLISH);
    }

    public void onIn(View v) {
        selectLanguage(INDONESIAN);
    }

    public void onTh(View v) {
        selectLanguage(THAI);
    }
    
    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);        // 将整个任务栈清空
        startActivity(i);
    }
}