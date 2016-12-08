package com.ellfors.atools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ellfors.extools.adapter.ExBaseRcvAdapter;
import com.ellfors.extools.base.ExBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ExBaseActivity
{
    private Button btn_test_logger;
    private Button btn;
    private Dialog dialog;

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtn();
        initRecycler();
    }

    private void initBtn()
    {
        $(R.id.my_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Title");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        showToast("AAAAAA");
                    }
                });
                builder.setNegativeButton("取消", null);
                dialog = builder.create();
                dialog.show();
            }
        });

        $(R.id.btn_test_logger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,TestLoggerActivity.class));
            }
        });
    }

    private void initRecycler()
    {
        recyclerView = $(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this,getData());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ExBaseRcvAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                showToast("第" + (position + 1) + "条点击");
            }
        });

        adapter.setOnItemLongClickListener(new ExBaseRcvAdapter.OnItemLongClickListener()
        {
            @Override
            public void onItemLongClick(View view, int position)
            {
                showToast("第" + (position + 1) + "条长按");
            }
        });
    }

    private List<String> getData()
    {
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i ++)
        {
            list.add("第" + (i+1) + "条");
        }
        return list;
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }
}
