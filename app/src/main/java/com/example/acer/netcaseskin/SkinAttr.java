package com.example.acer.netcaseskin;

public class SkinAttr {
    private int id;
    private String attrName;
    private String typeName;
    private String entryName;

    public SkinAttr(int id, String attrName, String typeName, String entryName) {
        this.id = id;
        this.attrName = attrName;
        this.typeName = typeName;
        this.entryName = entryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
}
