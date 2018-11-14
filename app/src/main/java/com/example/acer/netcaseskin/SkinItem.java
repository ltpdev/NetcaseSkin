package com.example.acer.netcaseskin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SkinItem {
    private View view;
    private List<SkinAttr>skinAttrs;

    public SkinItem(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        this.skinAttrs = skinAttrs;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getSkinAttrs() {
        return skinAttrs;
    }

    public void setSkinAttrs(List<SkinAttr> skinAttrs) {
        this.skinAttrs = skinAttrs;
    }

    public void apply() {
        for (SkinAttr skinAttr:skinAttrs) {
             if (skinAttr.getAttrName().equals("background")){
                  if (skinAttr.getTypeName().equals("color")){
                          view.setBackgroundColor(SkinManager.getInstance().getColor(skinAttr.getId()));
                  }else if (skinAttr.getTypeName().equals("drawable")){
                         view.setBackgroundDrawable(SkinManager.getInstance().getDrawable(skinAttr.getId()));
                  }
             }else if(skinAttr.getAttrName().equals("textColor")&&view instanceof TextView){
                 ((TextView) view).setTextColor(SkinManager.getInstance().getColor(skinAttr.getId()));
             }else if(skinAttr.getAttrName().equals("src")&&view instanceof ImageView){
                 ((ImageView) view).setImageDrawable(SkinManager.getInstance().getDrawable(skinAttr.getId()));
             }
        }
    }
}
