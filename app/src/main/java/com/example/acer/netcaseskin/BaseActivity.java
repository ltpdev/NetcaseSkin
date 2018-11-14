package com.example.acer.netcaseskin;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    protected SkinFactory skinFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        skinFactory=new SkinFactory();
        LayoutInflater.from(this).setFactory(skinFactory);
        SkinManager.getInstance().init(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
        skinFactory.apply();
    }
}
