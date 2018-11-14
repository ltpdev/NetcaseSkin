package com.example.acer.netcaseskin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.lang.reflect.Method;

public class SkinManager {
    private static final SkinManager ourInstance = new SkinManager();
    private Resources skinResources;
    private Context context;
    private String skinPkgName;

    public static SkinManager getInstance() {
        return ourInstance;
    }

    private SkinManager() {
    }



    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public void loadApk(String apkPath) {
        try {
            if (TextUtils.isEmpty(apkPath)){
                skinResources=null;
                skinPkgName=null;
                return;
            }
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath", new Class[]{String.class});
            method.invoke(assetManager, apkPath);
            //获取皮肤apk的包名
            skinPkgName = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES).packageName;
            //获取资源apk的对象
            skinResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


       public int getColor(int id){
        int color=context.getResources().getColor(id);
        if (skinResources!=null){
            String entryName=context.getResources().getResourceEntryName(id);
            String typeName=context.getResources().getResourceTypeName(id);
            int resId=skinResources.getIdentifier(entryName,"color",skinPkgName);
            if (resId>0){
                color=skinResources.getColor(resId);
            }
        }
        return color;
    }


    public Drawable getDrawable(int id){
        Drawable drawable=context.getResources().getDrawable(id);
        if (skinResources!=null){
            String entryName=context.getResources().getResourceEntryName(id);
            String typeName=context.getResources().getResourceTypeName(id);
            int resId=skinResources.getIdentifier(entryName,"drawable",skinPkgName);
            if (resId>0){
                drawable=skinResources.getDrawable(resId);
            }
        }
        return drawable;
    }

}
