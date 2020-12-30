package com.example.languagedemo;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.languagedemo.language.MultiLanguage;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguage.setLocal(newBase));
    }
    
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

    public Context getContext() {
        return this;
    }
}