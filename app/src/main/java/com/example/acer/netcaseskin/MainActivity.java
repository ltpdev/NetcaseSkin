package com.example.acer.netcaseskin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public void applySkin(View view){
         SkinManager.getInstance().loadApk(Environment.getExternalStorageDirectory().getAbsolutePath()+"/base.apk");
         skinFactory.apply();
    }
    public void skipSecondActivity(View view){
       startActivity(new Intent(this,SecondActivity.class));
    }


}
