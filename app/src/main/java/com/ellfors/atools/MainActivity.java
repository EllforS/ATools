package com.ellfors.atools;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ellfors.extools.base.ExBaseActivity;

public class MainActivity extends ExBaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
