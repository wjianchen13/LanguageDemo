package com.example.languagedemo;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.languagedemo.language.LocalManageUtil;
import com.example.languagedemo.language.MultiLanguage;


/**
 * application基类，可通过ILibsDispatcher来调用95xiu相关方法
 * Created by Administrator on 2017/10/24 0024.
 */

public class MyApp extends Application {

    private static MyApp mApplication = null;

    public static MyApp app() {
        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguage.setLocal(base));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @TargetApi(28)
    @Override
    public void onCreate() {
        super.onCreate();
        MultiLanguage.setApplicationLanguage(this);
        mApplication = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 用户在系统设置页面切换语言时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(getApplicationContext(), newConfig);
        MultiLanguage.onConfigurationChanged(getApplicationContext());
    }
}
