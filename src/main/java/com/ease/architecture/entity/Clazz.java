package com.ease.architecture.entity;

public class Clazz {

    private int id;
    private String clazzName;
    private String clazzDesc;

    public Clazz() {
    }

    public Clazz(String clazzName, String clazzDesc) {
        this.clazzName = clazzName;
        this.clazzDesc = clazzDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getClazzDesc() {
        return clazzDesc;
    }

    public void setClazzDesc(String clazzDesc) {
        this.clazzDesc = clazzDesc;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", clazzName='" + clazzName + '\'' +
                ", clazzDesc='" + clazzDesc + '\'' +
                '}';
    }
}
