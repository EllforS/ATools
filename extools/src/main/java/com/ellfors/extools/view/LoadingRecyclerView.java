package com.ellfors.extools.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * 上拉加载RecyclerView
 */
public class LoadingRecyclerView extends RecyclerView
{
    private boolean isLoading = false;
    private int lastVisibleItem;

    private SwipeRefreshLayout swipe;
    private Adapter adapter;

    private OnLoadingListener onLoadingListener;

    public interface OnLoadingListener
    {
        void onLoading();
    }

    public void setOnLoadingListener(OnLoadingListener listener)
    {
        this.onLoadingListener = listener;
    }

    public LoadingRecyclerView(Context context)
    {
        super(context);
    }

    public LoadingRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public LoadingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void init(final SwipeRefreshLayout s, final Adapter a)
    {
        this.swipe = s;
        this.adapter = a;

        addOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

                if (!isLoading && !swipe.isRefreshing() && newState ==
                        RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == adapter.getItemCount() - 1)
                {
                    isLoading = true;

                    if(onLoadingListener != null)
                    {
                        onLoadingListener.onLoading();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                if(getLayoutManager() instanceof LinearLayoutManager)
                {
                    lastVisibleItem = ((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
                }
                else if(getLayoutManager() instanceof GridLayoutManager)
                {
                    lastVisibleItem = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                }
                else if(getLayoutManager() instanceof StaggeredGridLayoutManager)
                {
                    StaggeredGridLayoutManager sgm = (StaggeredGridLayoutManager) getLayoutManager();
                    int[] positions = sgm.findLastVisibleItemPositions(null);
                    lastVisibleItem = Math.max(positions[0], positions[1]);
                }
            }
        });
    }

    /**
     * 刷新结束
     */
    public void isLoadingEnd()
    {
        adapter.notifyDataSetChanged();
        isLoading = false;
    }
}
