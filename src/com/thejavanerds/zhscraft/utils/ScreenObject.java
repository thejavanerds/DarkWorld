package com.thejavanerds.zhscraft.utils;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public abstract interface ScreenObject
{
    public abstract void init();

    public abstract void initGL();

    public abstract void update();

    public abstract void render();

    public abstract void dispose();
}
