package com.ellfors.extools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * RecyclerView Adapter
 */
public abstract class ExBaseRcvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public Context context;
    public List<T> list;

    public ExBaseRcvAdapter(Context context, List<T> list)
    {
        this.context = context;
        this.list = list;
    }

    public abstract void onBindHolder(RecyclerView.ViewHolder holder, int position);

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.onItemLongClickListener = listener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener
    {
        void onItemLongClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        onBindHolder(holder, position);

        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

        if (onItemLongClickListener != null)
        {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    onItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }
}
