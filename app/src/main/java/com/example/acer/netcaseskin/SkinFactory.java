package com.example.acer.netcaseskin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


public class SkinFactory implements LayoutInflater.Factory {
    private static final String[] VIEW_PREFIX = {"android.view.", "android.widget.", "android.webkit."};
    private List<SkinItem>skinItems=new ArrayList<>();

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.i("name", name);
        View view = createView(name, context, attrs);
        parseSkinAttrs(view,context,attrs);
        return view;
    }

    private void parseSkinAttrs(View view, Context context, AttributeSet attrs) {
        //收集可能需要换肤的属性集合
        List<SkinAttr>skinAttrs=new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName=attrs.getAttributeName(i);
            String attrValue=attrs.getAttributeValue(i);
            Log.i("attrName", "attrName&&"+attrName+":attrValue&&"+attrValue);
            if (attrValue.startsWith("@")){
                int id=Integer.parseInt(attrValue.substring(1));
                String typeName=context.getResources().getResourceTypeName(id);
                String entryName=context.getResources().getResourceEntryName(id);
                Log.d("attrValue", "typeName &&"+typeName+"  entryNameName &&"+entryName);
                skinAttrs.add(new SkinAttr(id,attrName,typeName,entryName));
            }
        }

        if (!skinAttrs.isEmpty()){
            skinItems.add(new SkinItem(view,skinAttrs));
        }
    }


    public void apply(){
        for (SkinItem skinItem:skinItems){
            skinItem.apply();
        }
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        View view = null;
        //自定义view
        if (name.contains(".")) {
            view = getView(name, context, attrs);
        } else {//系统view
            for (String prefix : VIEW_PREFIX) {
                String trueName = prefix + name;
                view = getView(trueName, context, attrs);
                if (view != null) {
                    break;
                }
            }
        }
        return view;
    }

    private View getView(String name, Context context, AttributeSet attrs) {
        View view = null;
        try {
            Class<?> clazz = context.getClassLoader().loadClass(name);
            Constructor<?> constructor = clazz.getConstructor(new Class[]{Context.class, AttributeSet.class});
            view = (View) constructor.newInstance(context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
