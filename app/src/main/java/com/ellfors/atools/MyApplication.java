package com.ellfors.atools;

import com.ellfors.extools.base.ExBaseApplication;
import com.ellfors.extools.utils.L;

public class MyApplication extends ExBaseApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        L.init("ABC",2,true,0);
    }
}
