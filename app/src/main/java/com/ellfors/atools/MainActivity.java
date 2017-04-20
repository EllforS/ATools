package com.ellfors.atools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.ellfors.extools.adapter.ExBaseRcvAdapter;
import com.ellfors.extools.base.ExBaseActivity;
import com.ellfors.extools.view.DividerGridItemDecoration;
import com.ellfors.extools.view.LoadingRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ExBaseActivity
{
    @BindView(R.id.btn_test_logger)
    Button btn_test_logger;
    @BindView(R.id.my_button)
    Button btn;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private Dialog dialog;

    private LoadingRecyclerView recyclerView;
    private MyAdapter adapter;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecycler();
    }

    private void initRecycler()
    {
        recyclerView = $(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));

        adapter = new MyAdapter(this, getData());
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        adapter = new MyAdapter(mContext, getData());
                        recyclerView.setAdapter(adapter);
                        recyclerView.init(swipeRefreshLayout, adapter);
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setEnd(false);
                    }
                }, 1000);
            }
        });

        recyclerView.init(swipeRefreshLayout, adapter);
        recyclerView.setOnLoadingListener(new LoadingRecyclerView.OnLoadingListener()
        {
            @Override
            public void onLoading()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (!adapter.isEnd())
                        {
                            list.add("新增_1");
                            list.add("新增_2");
                            recyclerView.isLoadingEnd();
                            checkListSize();
                        }
                    }
                }, 1000);
            }
        });
    }

    @OnClick(R.id.my_button)
    void doTouchMeClick()
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

    @OnClick(R.id.btn_test_logger)
    void doLoggerClick()
    {
        startActivity(new Intent(mContext, TestLoggerActivity.class));
    }

    private List<String> getData()
    {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            list.add("第" + (i + 1) + "条");
        }
        return list;
    }

    private void checkListSize()
    {
        if (list.size() > 26)
            adapter.setEnd(true);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
