# android 多语言使用记录  
android项目接入多语言，使用的框架是：https://github.com/MichaelJokAr/MultiLanguages  

1.在Appllication里面添加如下代码，记得写全，不要少写任何一句代码  
之前接入少写了attachBaseContext里面的一句代码，搞了很久很久。。。  
```Java
    @Override
    protected void attachBaseContext(Context base) {
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguage.setLocal(base));
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
```
2.在BaseActivity里:  
```Java
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguage.setLocal(newBase));
    }
```

# 遇到问题  
接入后发现了一个比较严重的问题，切换到印尼语，进入直播大厅，再进入直播间返回直播大厅，部分页面特别是Fragment  
的页面都有变成英文的现象，造成了同一页面同时存在英文和印尼语。  
大致和这个描述的差不多：https://blog.csdn.net/k393393/article/details/78711973  
经过测试，发现同一个app里面有2个Resource，分别是：  
getApplicationContext().getResources() // 在工具类中需要加载String时会采用ApplicationContext  
getResources() // 在Activity里，直接获取  
造成这个现象的原因是因为切换到印尼语的时候，开始getResources()和getApplicationContext().getResources()都  
切换成功了，但是使用了一段时间，部分手机系统的getApplicationContext()的Locale会自动切换到英语（目前测试华为  
和Vivo都发现这种情况)，所以项目中使用getResource().getString()获取字符串资源是正常的，但是使用  
getApplicationContext().getResource().getString()就会变成英文。  
描述这个问题的文章：https://blog.csdn.net/xiaocheng0404/article/details/107564983  
现在处理这个问题的办法是，在公共基类BaseActivity的onCreate，onStart和onResume方法里强制设置getApplicationContext()  
为选择的系统语言。  

# Fragment中的问题  
https://blog.csdn.net/k393393/article/details/78711973  

# AndroidX的问题  
https://blog.csdn.net/u012527560/article/details/108816692  
https://www.it610.com/article/1296455207361781760.htm  

