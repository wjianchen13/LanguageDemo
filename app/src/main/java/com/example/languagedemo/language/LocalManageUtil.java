package com.example.languagedemo.language;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class LocalManageUtil {

    private static final String TAG = "LocalManageUtil";

    /**
     * 是否在设置页面切换了语言
     * 如果切换了改变记录在这个标记，应用强制重启
     */
    private static boolean isLanguageChanged;

    /**
     * 当前选择设置语言
     */
    private static String currentSelectLanguage = "";

    /**
     * 英语
     */
    public static final String LANGUAGE_EN = "en_US";

    /**
     * 印尼语言 en_US
     */
    public static final String LANGUAGE_IN = "in_ID";

    /**
     * 印尼语言 en_US
     */
    public static final String LANGUAGE_TH = "th_TH";

    /**
     * 葡萄牙语 pt_PT
     */
    public static final String LANGUAGE_PT = "pt_PT";

    /**
     * 繁体 ch_TW
     */
    public static final String LANGUAGE_TW = "ch_TW";

    /**
     * 默认语言 en_US
     */
    private static final String LANGUAGE_DEFAULT = LANGUAGE_EN;

    /**
     * 默认
     */
    public static final int DEFAULT = 0;

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

    /**
     * 泰语
     */
    public static final int PT = 4;

    /**
     * 繁体
     */
    public static final int TW = 5;

    @IntDef({DEFAULT, ENGLISH, INDONESIAN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Language {

    }

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    public static Locale getSystemLocale(Context context) {
        return SPUtil.getInstance(context).getSystemCurrentLocal();
    }

    /**
     * 获取应用内设置的语言
     * @param context
     * @return
     */
    public static String getSelectLanguage(Context context) {
        if(!TextUtils.isEmpty(currentSelectLanguage))
            return currentSelectLanguage;
        if(context == null) {
            currentSelectLanguage = getDefaultLanguage();
        }
        currentSelectLanguage = getLanguage(context, SPUtil.getInstance(context).getSelectLanguage());
        return currentSelectLanguage;
    }

    /**
     * 获取默认语言
     * @return
     */
    public static String getDefaultLanguage() {
        return LANGUAGE_DEFAULT;
    }

    /**
     * 获取应用内设置的语言设置
     *
     * @param context
     * @return
     */
    public static Locale getSetLanguageLocale(Context context) {
        switch (SPUtil.getInstance(context).getSelectLanguage()) {
            case DEFAULT:
                return getSystemLocale(context);
            case INDONESIAN:
                return new Locale("in");
            case THAI:
                return new Locale("th");
            case PT:
                return new Locale("pt");
            case TW:
                return Locale.TRADITIONAL_CHINESE;
            default:
                return Locale.ENGLISH;
        }
    }

    /**
     * 获取系统默认语言，只能是印尼语和英语，因为要上传到服务器匹配类型
     * @param context languageTag
     * @return
     */
    public static String getSystemLanguage(Context context) {
        Locale locale = MultiLanguage.getSystemLocal(context);
        if(locale != null) {
            // 这个方法可以转换locale.toLanguageTag()
            String language = locale.getLanguage() + "_" + getLocaleCountry(locale.getLanguage());
            return !LANGUAGE_EN.equals(language) 
                    && !LANGUAGE_IN.equals(language) 
                    && !LANGUAGE_TH.equals(language) 
                    ? LANGUAGE_EN : language;
        } else {
            return LANGUAGE_EN;
        }
    }

    /**
     * 通过获取的语言拼接成类似in_ID的格式
     * 因为有些手机除了设置语言，还能设置地区，例如设置印尼语的时候，可能会是in_CN,前面是印尼语，后面地区是中国
     * 所以不使用语言里面获取国家信息，而是直接拼接成服务器定义那样
     * in_ID 印尼语
     * en_US 英语
     * @return
     */
    private static String getLocaleCountry(String language) {
        String country = "US";
        if("in".equals(language)) { // 印尼语
            country = "ID";
        } else if("th".equals(language)) {
            country = "TH";
        }
        return country;
    }

    /**
     * 保存系统语言
     * @param context
     */
    public static void saveSystemCurrentLanguage(Context context) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(context));
        SPUtil.getInstance(context).setLanguageIfFirstStart(getSystemSelectLanguage(context));
    }

    /**
     * 保存系统语言
     * @param context
     * @param newConfig
     */
    public static void saveSystemCurrentLanguage(Context context, Configuration newConfig) {
        Locale locale = SPUtil.getInstance(context).getSystemCurrentLocal();
        String oldLanguage = "";
        
        if(locale != null)  
            oldLanguage = locale.getLanguage();
        if(TextUtils.isEmpty(oldLanguage)) {
            isLanguageChanged = false;
        } else {
            String newLanguage = "";
            Locale nLocale = MultiLanguage.getSystemLocal(newConfig);
            if(nLocale != null)
                newLanguage = nLocale.getLanguage();
            isLanguageChanged = !oldLanguage.equals(newLanguage);
        }
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(newConfig));
    }

    /**
     * 判断是否在设置页切换了语言
     * @return
     */
    public static boolean isLanguageChanged() {
        boolean isChanged = isLanguageChanged;
        if(isChanged)
            currentSelectLanguage = ""; // 初始化默认选择的語言，要不还会使用上一次切换系统语言之前保存的
        isLanguageChanged = false;
        return isChanged;
    }

    /**
     * 获取设置的语言类型
     * @param context
     * @return
     */
    public static int getSelectLanguageType(Context context) {
        String language = getSelectLanguage(context);
        return getLanguageType(language);
    }

    /**
     * 保存设置的语言类型
     * @param context
     * @return
     */
    public static void saveSelectLanguage(Context context, int select) {
        SPUtil.getInstance(context).saveLanguage(select);
        currentSelectLanguage = getLanguage(context, select);
        MultiLanguage.setApplicationLanguage(context);
    }

    /**
     * 获取语言
     * @return
     */
    private static String getLanguage(Context context, int type) {
        String language = getDefaultLanguage();
        if(type == ENGLISH) {
            language = LANGUAGE_EN;
        } else if(type == INDONESIAN) {
            language = LANGUAGE_IN;
        } else if(type == THAI) {
            language = LANGUAGE_TH;
        } else if(type == PT) {
            language = LANGUAGE_PT;
        } else if(type == TW) {
            language = LANGUAGE_TW;
        } else if(type == DEFAULT){
            language = getSystemLanguage(context);
        }
        return language;
    }

    /**
     * 获取系统语言
     * @param context
     * @return
     */
    public static int getSystemSelectLanguage(Context context) {
        String systemLanguage = getSystemLanguage(context);
        return getLanguageType(systemLanguage);
    }

    /**
     * 获取语言类型
     * @return
     */
    private static int getLanguageType(String language) {
        int select = ENGLISH;
        if(LANGUAGE_IN.equals(language)) {
            select = INDONESIAN;
        } else if(LANGUAGE_TH.equals(language)) {
            select = THAI;
        } else if(LANGUAGE_PT.equals(language)) {
            select = PT;
        } else if(LANGUAGE_TW.equals(language)) {
            select = TW;
        } else if(LANGUAGE_EN.equals(language)){
            select = ENGLISH;
        }
        return select;
    }
    
    public static void log11(String  str) {
        System.out.println("===============================> " + str);
    }
    
}
