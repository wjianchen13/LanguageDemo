package com.example.languagedemo;

import android.os.Bundle;
import android.widget.TextView;

import com.example.languagedemo.language.LanguageUtils;
import com.example.languagedemo.language.LocalManageUtil;

public class TestActivity extends BaseActivity {
    
    private TextView tvTest2;
    private TextView tvTest3;
    private TextView tvTest4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvTest2 = findViewById(R.id.tv_test2);
        tvTest3 = findViewById(R.id.tv_test3);
        tvTest4 = findViewById(R.id.tv_test4);

        tvTest2.setText(LanguageUtils.getString(R.string.test));
        tvTest3.setText(getApplicationContext().getResources().getString(R.string.test));
        tvTest4.setText(getResources().getString(R.string.test));

        log();
    }
    
    private void log() {
        LocalManageUtil.log11("TestActivity onCreate getApplicationContext().getResources() : " + getApplicationContext().getResources());
        LocalManageUtil.log11("TestActivity onCreate getResources() : " + getResources());
        LocalManageUtil.log11("TestActivity onCreate DobyApp.app().getResources() : " + MyApp.app().getResources());
        LocalManageUtil.log11("TestActivity onCreate getContext().getResources() : " + getContext().getResources());


        String lang1 = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        String lang2 = getResources().getConfiguration().locale.getLanguage();
        String lang3 = MyApp.app().getResources().getConfiguration().locale.getLanguage();
        String lang4 = getContext().getResources().getConfiguration().locale.getLanguage();

        LocalManageUtil.log11("TestActivity onCreate lang1: " + lang1);
        LocalManageUtil.log11("TestActivity onCreate lang2: " + lang2);
        LocalManageUtil.log11("TestActivity onCreate lang3: " + lang3);
        LocalManageUtil.log11("TestActivity onCreate lang4: " + lang4);

        LocalManageUtil.log11("TestActivity onCreate test1: " + LanguageUtils.getString(R.string.test));
        LocalManageUtil.log11("TestActivity onCreate test2: " + getApplicationContext().getResources().getString(R.string.test));
        LocalManageUtil.log11("TestActivity onCreate test3: " + getResources().getString(R.string.test));
        LocalManageUtil.log11("TestActivity onCreate test4: " + MyApp.app().getResources().getString(R.string.test));
        LocalManageUtil.log11("TestActivity onCreate test5: " + getContext().getResources().getString(R.string.test));
    }
}