package com.thejavanerds.zhscraft.utils;

/**
 * Created by ZhsCraft on 2/6/2015.
 */

public class Time
{
    public static final long SECOND = 1000000000L;
    private static double delta;

    public static long getTime()
    {
        return System.nanoTime();
    }

    public static double getDelta()
    {
        return delta;
    }

    public static void setDelta(double delta)
    {
        delta = delta;
    }
}
