package com.git.hexg.liveplayer.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HEXG on 2017/11/27.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseHolder> {

    protected OnItemClickListener listener = null;
    protected Context context;
    protected List<T> list;

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BaseRecyclerAdapter.BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(getLayoutId(), parent, false);
        return getViewHolder(view);
    }

    protected abstract BaseRecyclerAdapter.BaseHolder getViewHolder(View view);

    protected abstract int getLayoutId();

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseHolder holder, final int position) {
        if (list == null || list.size() <= 0) return;
        final T t = list.get(position);
        holder.bindView(position, t);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onRecyclerViewItemClick(position, t);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null) {
                    listener.onRecyclerViewItemLongClick(position, t);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class BaseHolder extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }

        public void bindView(final int position, final T t) {
        }
    }

    public interface OnItemClickListener<T> {
        void onRecyclerViewItemClick(final int position, final T t);

        void onRecyclerViewItemLongClick(final int position, final T t);
    }
}
