package com.spring.diploma.util;

public class Timer
{
    private long initTime;
    private long endTime;
    public long start()
    {
        return initTime = System.currentTimeMillis();
    }

    public long stop()
    {
        return endTime = System.currentTimeMillis();
    }

    public String elapsedTime()
    {
        long diff = endTime-initTime;
        return Long.toString(diff)+" milisegundos";
        //return Long.toString(diff/1000)+" segundos";
    }

}