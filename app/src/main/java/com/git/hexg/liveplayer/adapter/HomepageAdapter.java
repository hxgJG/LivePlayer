package com.git.hexg.liveplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.git.hexg.liveplayer.R;
import com.git.hexg.liveplayer.base.BaseRecyclerAdapter;
import com.git.hexg.liveplayer.bean.HomeInfo;
import com.git.hexg.liveplayer.util.StringUtils;
import com.git.hexg.liveplayer.util.T;
import com.git.hexg.liveplayer.view.LivePlayerActivity;

import java.util.List;

/**
 * Created by HEXG on 2017/11/27.
 */

public class HomepageAdapter extends BaseRecyclerAdapter<HomeInfo> {

    public HomepageAdapter(final Context context, List<HomeInfo> homeInfos) {
        super(context, homeInfos);
        this.setOnItemClickListener(new OnItemClickListener<HomeInfo>() {
            @Override
            public void onRecyclerViewItemClick(int position, HomeInfo info) {
                Intent intent = new Intent(context, LivePlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("homeInfo",info);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            @Override
            public void onRecyclerViewItemLongClick(int position, HomeInfo info) {
                T.showShort(context, "长按了：" + position);
            }
        });
    }

    @Override
    protected BaseHolder getViewHolder(View view) {
        return new HomeHolder(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_homepage_fragment;
    }

    class HomeHolder extends BaseHolder {
        private TextView tv_1, tv_2;
        private HomepageAdapter adapter;

        public HomeHolder(View itemView) {
            super(itemView);
            tv_1 = (TextView) itemView.findViewById(R.id.tv_1);
            tv_2 = (TextView) itemView.findViewById(R.id.tv_2);
            adapter = new HomepageAdapter(context, list);
        }

        @Override
        public void bindView(int position, HomeInfo homeInfo) {
            super.bindView(position, homeInfo);
            if (homeInfo == null) return;
            if (!StringUtils.isNullOrEmpty(homeInfo.getStr1())) {
                tv_1.setText(homeInfo.getStr1());
            }
            if (!StringUtils.isNullOrEmpty(homeInfo.getStr2())) {
                tv_2.setText(homeInfo.getStr2());
            }
        }
    }
}
