package com.example.languagedemo.language;

import android.content.Context;
import android.content.res.Resources;

import com.example.languagedemo.MyApp;

public class LanguageUtils {

    public static String getString(int resourceId){
        return getString(MyApp.app(), resourceId);
    }

    public static String getString(Context context, int resourceId){
        if(context == null)
            return "";
        Resources res = context.getResources();
        if(res == null)
            return "";
        return res.getString(resourceId);
    }

}
