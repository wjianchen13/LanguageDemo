package com.example.languagedemo.bidi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;

import com.example.languagedemo.BaseActivity;
import com.example.languagedemo.R;

import java.util.Locale;

public class BidiTestActivity extends BaseActivity {

    private TextView tvTest1;
    private TextView tvTest2;
    private TextView tvTest3;
    private TextView tvTest4;
    private TextView tvTest5;
    private TextView tvTest6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidi_test);
        tvTest1 = findViewById(R.id.tv_test1);
        tvTest2 = findViewById(R.id.tv_test2);
        tvTest3 = findViewById(R.id.tv_test3);
        tvTest4 = findViewById(R.id.tv_test4);
        tvTest5 = findViewById(R.id.tv_test5);
        tvTest6 = findViewById(R.id.tv_test6);
    }

    public void onTest1(View v) {
        String content = "我是中国人";
        tvTest1.setText(content);
    }

    public void onTest2(View v) {
        String content = "我是中国人";
        tvTest2.setText(biDiFormat(content));
    }

    public void onTest3(View v) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("يبيب");
        stringBuffer.append("中国人");
        tvTest3.setText(stringBuffer);
    }

    public void onTest4(View v) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("يبيب");
        stringBuffer.append("中国人");
        tvTest4.setText(biDiFormat(stringBuffer.toString()));
    }

    /**
     * 输入阿拉伯语，再次输入数据，数字会自动插入到阿拉伯语前面
     * 参考下面的连接
     * http://www.taodudu.cc/news/show-4645035.html?action=onClick
     *
     * https://www.codenong.com/10975176/
     * 我认为Android的比迪分析算法有一些缺陷。 Unicode具有两个不可见的，方向性强的字符，它们可能会帮助解决这些问题：
     *
     * U + 200E-左右标记
     * U + 200F-从右到左标记
     * 对于数字顺序问题，请尝试在数字序列的两侧放置从左到右的标记(U + 200E)。
     *
     * Unicode还具有以下bidi格式代码：
     *
     * U + 202A-从左到右嵌入
     * U + 202B-从右到左嵌入
     * U + 202C-弹出方向格式化(取消先前的嵌入或覆盖)
     * U + 202D-左右覆盖
     * U + 202E-从右到左覆盖
     * @param v
     */
    public void onTest5(View v) {
//        String content = "Wwwwwwعبدهمصطفى93 is coming";
//        String content = "Wwwwwwwwwwwwasdljfalskdfjklaskljdfjkasdkj134567989123456789123456789";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\u200F");
        stringBuffer.append("Wwwwwwwwwwww");
        stringBuffer.append("عبدهمصطفى");
        stringBuffer.append("93");
//        String content = "\u200FWwwwwwwwwwwwعبدهمصطفى93";
        String content = biDiFormat(stringBuffer.toString());
        tvTest5.setText(biDiFormat(content));
    }

    public void onTest6(View v) {


    }

    private static String biDiFormat(String str) {
        return BidiFormatter.getInstance(Locale.getDefault()).unicodeWrap(str, TextDirectionHeuristicsCompat.LOCALE);
    }


}


