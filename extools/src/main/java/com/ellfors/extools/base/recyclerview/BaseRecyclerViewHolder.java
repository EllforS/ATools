package com.ellfors.extools.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView类型对象基类
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder
{
    private BaseRecyclerAdapter adapter;
    private View mItemView;

    public BaseRecyclerViewHolder(View itemView, final BaseRecyclerAdapter adapter)
    {
        super(itemView);
        this.mItemView = itemView;
        this.adapter = adapter;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T $(int id)
    {
        return (T) mItemView.findViewById(id);
    }

    /**
     * 设置公用的点击事件
     */
    public void setOnClickListener(View view)
    {
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getRealPosition() != RecyclerView.NO_POSITION && adapter.items != null && adapter.items.size() > 0 && adapter.mListener != null)
                    adapter.mListener.onItemClick(v, getRealPosition(), adapter.getData(getRealPosition()));
            }
        });
    }

    /**
     * 设置公用的长按事件
     */
    public void setOnLongClickListener(View view)
    {
        view.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                if (getRealPosition() != RecyclerView.NO_POSITION && adapter.items != null && adapter.items.size() > 0 && adapter.mLongListener != null)
                    adapter.mLongListener.onItemLongClick(view, getRealPosition(), adapter.getData(getRealPosition()));
                return true;
            }
        });
    }

    /**
     * 获取真实的指针位置
     */
    private int getRealPosition()
    {
        int pos = getAdapterPosition();
        if (pos == RecyclerView.NO_POSITION)
            return getPosition();
        else
            return pos;
    }

}