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
```Java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiLanguage.setLocal(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MultiLanguage.setLocal(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MultiLanguage.setLocal(this);
    }
```
Activity也有可能需要在onConfigurationChanged()处理语言切换，不过现在都是切换了重启所有Activity，所以暂时没有处理
onConfigurationChanged

接入之后泰语只有一直没有效果
看了一下是因为这个配置排除掉了
        resConfigs "en", "in", "th"

# 接入到95最开始不显示繁体
95原来是resConfigs "zh"
改为
resConfigs "zh-rTW"

# Fragment中的问题  
https://blog.csdn.net/k393393/article/details/78711973  

# AndroidX的问题  
https://blog.csdn.net/u012527560/article/details/108816692  
https://www.it610.com/article/1296455207361781760.htm  

# 参考资料
新添加泰语
https://www.jb51.net/article/38537.htm
https://blog.csdn.net/qq_31097291/article/details/76546368

# 新加葡萄牙语
https://blog.csdn.net/qq_36376387/article/details/73069347
Locale里面有几个常量，对应几个常见国家，如果你要切换的语言这几个常量里没有的话，就自己new一个Locale对象就好啦,
构造方法里传入对应语言的代码字符串就好（就是values文件夹-后面的）。配置完该信息后需要退出activity，下次进入才
能看到效果，所以我这里用广播的方式关闭了所有的activity，再重新开启MainActivity回到主页！不管你用什么方法，能实现就好。

# 直播间语言混乱问题
新安装一个包，切换到葡萄牙语，进入一个直播间呆10分钟内，会出现app的locale自动改变的情况，例如，如果是葡萄牙语，pt，会自动改变成en
导致聊天列表一部分显示成葡萄牙语，一部分显示成 英语，但是Context的locale是没有改变的。
解决方法是，每次使用App的Application时，判断locale和Context的locale是否相等，如果不相等，则强制设置App的Application locale语言和当前设置的语言一直

2022-01-11 17:00:35.674 31221-31408/com.honeychat.arabic I/language_test:  
    ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
    │ pool-21-thread-6, com.dinosdk.dino_util.DinoLog.json(DinoLog.java:105)
    ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
    │ ===========> welcome app locale: pt   app real: Bem-vindo    context locale: pt   context real: Bem-vindo 
    └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
2022-01-11 17:04:35.510 31221-31396/com.honeychat.arabic I/language_test:  
    ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
    │ pool-21-thread-2, com.dinosdk.dino_util.DinoLog.json(DinoLog.java:105)
    ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
    │ ===========> welcome app locale: en   app real: Welcome    context locale: pt   context real: Bem-vindo 
    └────────────────────────────────────────────────────────────────────────────────────────────────────────────────



翻译注意事项
1.翻译标签<string>xxx</string>里面的xxx内容，后面的 <!-- --> 是参考中文，不要改动
例如 <string name="dino_common_charge">Top-Up</string> <!-- 充值 -->
翻译内容是Top-Up，翻译完成直接替换原来的内容
<string name="dino_common_charge">翻译后的Top-Up</string> <!-- 充值 -->
2.后面<!-- --> 是参考中文，以<string> </string>的英文内容为准
3.文件不要删除和添加多余的信息，翻译后都是直接替换<string> </string>标签里面的内容，如果没有翻译，不要改动
4.翻译内容里面含有@string的不需要翻译，并且不要改动
5.翻译内容里面为空的不需要翻译，并且不要改动













