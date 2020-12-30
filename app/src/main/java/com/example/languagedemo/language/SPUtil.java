package com.example.languagedemo.language;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * 框架提供的
 * 在Doby.app()之前就用到，所以不用SharePreUtils
 */
public class SPUtil {

    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private final String TAG_SYSTEM_FIRST_START = "system_first_start";
    private static volatile SPUtil instance;

    private final SharedPreferences mSharedPreferences;

    private Locale systemCurrentLocal = Locale.ENGLISH;
    
    public static SPUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    instance = new SPUtil(context);
                }
            }
        }
        return instance;
    }
    
    public SPUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
    
    /**
     * 如果是第一次启动，设置APP语言为当前系统语言
     */
    public void setLanguageIfFirstStart(int select) {
        if(isFirstStart()) {
            saveFirstStart();
            saveLanguage(select);
        }
    }

    /**
     * 是否第一次启动应用
     */
    private void saveFirstStart() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean(TAG_SYSTEM_FIRST_START, true);
        edit.commit();
    }

    private boolean isFirstStart() {
        return !mSharedPreferences.getBoolean(TAG_SYSTEM_FIRST_START, false);
    }

    public void saveLanguage(int select) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(TAG_LANGUAGE, select);
        edit.commit();
    }

    public int getSelectLanguage() {
        return mSharedPreferences.getInt(TAG_LANGUAGE, 0);
    }

    public Locale getSystemCurrentLocal() {
        return systemCurrentLocal;
    }

    public void setSystemCurrentLocal(Locale local) {
        systemCurrentLocal = local;
    }
    
}
