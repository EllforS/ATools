package com.ellfors.extools.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerView Adapter
 */
public abstract class BaseRcvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TYPE_ITEM = 11;
    public static final int TYPE_FOOTER = 12;
    public static final int TYPE_HEADER = 13;

    private boolean isHasHeader = false;
    private boolean isHasFooter = false;

    public BaseRcvAdapter(boolean isHasHeader, boolean isHasFooter)
    {
        this.isHasHeader = isHasHeader;
        this.isHasFooter = isHasFooter;
    }

    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);
    public abstract void onBindHolder(RecyclerView.ViewHolder holder, int position);
    public abstract int getItemSize();

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return onCreateHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        onBindHolder(holder,position);

        if(onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    onItemClickListener.onItemLongClick(holder.itemView,holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return getItemSize();
    }

    /**
     * 为GridViewLayout FootView设置占位为一行
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        if (manager instanceof GridLayoutManager)
        {

            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
            {
                @Override
                public int getSpanSize(int position)
                {
                    //footview
                    if(getItemViewType(position) % TYPE_FOOTER == 0 && isHasFooter)
                    {
                        return gridManager.getSpanCount();
                    }
                    //headview
                    else if(position == 0 && isHasHeader)
                    {
                        return gridManager.getSpanCount();
                    }
                    else
                    {
                        return 1;
                    }
                }
            });
        }
    }

    /**
     * 为StaggeredGridLayout FootView设置占位为一行
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }
}
