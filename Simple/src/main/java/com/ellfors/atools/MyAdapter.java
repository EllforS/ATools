package com.ellfors.atools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ellfors.extools.adapter.ExBaseRcvAdapter;
import com.ellfors.extools.base.ExBaseViewHolder;

import java.util.List;

public class MyAdapter extends ExBaseRcvAdapter<String>
{
    public MyAdapter(Context context, List<String> list)
    {
        super(context, list, true, true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_ITEM)
        {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_recycler, parent, false));
        }
        if (viewType == TYPE_HEADER)
        {
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_header, parent, false));
        }
        if (viewType == TYPE_FOOTER)
        {
            return new FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            ((ItemViewHolder) holder).text.setText(list.get(position));
        }
        else if (holder instanceof HeaderViewHolder)
        {
            ((HeaderViewHolder) holder).title.setText("标题");
        }
        else if (holder instanceof FooterViewHolder)
        {
            if (isEnd())
            {
                ((FooterViewHolder) holder).bar.setVisibility(View.GONE);
                ((FooterViewHolder) holder).bottom.setText("到底了");
            }
            else
            {
                ((FooterViewHolder) holder).bar.setVisibility(View.VISIBLE);
                ((FooterViewHolder) holder).bottom.setText("底部");
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size() + 2;
    }

    public class ItemViewHolder extends ExBaseViewHolder
    {
        private TextView text;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            text = $(R.id.item_text);
        }
    }

    public class HeaderViewHolder extends ExBaseViewHolder
    {
        private TextView title;
        public HeaderViewHolder(View itemView)
        {
            super(itemView);
            title = $(R.id.item_header);
        }
    }

    public class FooterViewHolder extends ExBaseViewHolder
    {
        private ProgressBar bar;
        private TextView bottom;
        public FooterViewHolder(View itemView)
        {
            super(itemView);
            bar = $(R.id.item_footer_bar);
            bottom = $(R.id.item_footer_text);
        }
    }
}