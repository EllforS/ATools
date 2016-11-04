package com.ellfors.atools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ellfors.extools.adapter.BaseRcvAdapter;


import java.util.List;

public class MyAdapter extends BaseRcvAdapter
{
    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list)
    {
        super(true,true);
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType)
    {
        Log.e("AAA",viewType + "");

        if(viewType == TYPE_ITEM)
        {
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_recycler,parent,false));
        }
        if(viewType == TYPE_HEADER)
        {
            return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_recycler,parent,false));
        }
        if(viewType == TYPE_FOOTER)
        {
            return new FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.listitem_recycler,parent,false));
        }
        return null;
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int position)
    {
        if(holder instanceof ItemViewHolder)
        {
            ((ItemViewHolder) holder).text.setText(list.get(position));
        }
        else if(holder instanceof HeaderViewHolder)
        {
            ((HeaderViewHolder) holder).title.setText("标题");
        }
        else if(holder instanceof FooterViewHolder)
        {
            ((FooterViewHolder) holder).bottom.setText("底部");
        }
    }

    @Override
    public int getItemSize()
    {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == 0)
        {
            return TYPE_HEADER;
        }
        else if(position + 1 == getItemSize())
        {
            return TYPE_FOOTER;
        }
        else
        {
            return TYPE_ITEM;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView text;
        public ItemViewHolder(View itemView)
        {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        public HeaderViewHolder(View itemView)
        {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder
    {
        private TextView bottom;
        public FooterViewHolder(View itemView) {
            super(itemView);
            bottom = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}