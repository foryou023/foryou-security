package com.foryou.security.core.properties;

public class ImageCodeProperties extends SmsCodeProperties{
    // 图片的宽度
    private int width = 120;
    // 图片的高度
    private int height = 40;

    public ImageCodeProperties (){
        setCodeCount(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
