package com.git.hexg.liveplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.git.hexg.liveplayer.R;
import com.git.hexg.liveplayer.adapter.HomepageAdapter;
import com.git.hexg.liveplayer.base.BaseFragment;
import com.git.hexg.liveplayer.base.BaseRecyclerAdapter;
import com.git.hexg.liveplayer.bean.HomeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEXG on 2017/11/22.
 */

public class HomepageFragment extends BaseFragment {
    RecyclerView recyclerView;
    ProgressBar prgBar;
    private Handler handler = new Handler();
    private List<HomeInfo> homeInfos = null;
    private HomepageAdapter adapter;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);
        prgBar = (ProgressBar) view.findViewById(R.id.prgBar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void initUI(){
        adapter = new HomepageAdapter(mActivity,homeInfos);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        this.setTitle("首页");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isPrepared()){
                    Log.i("Fragment","initData：目标已被回收");
                    return;
                }
                homeInfos = new ArrayList<HomeInfo>();
                homeInfos.add(new HomeInfo("AAA", "哈哈哈哈", 111));
                homeInfos.add(new HomeInfo("BBB", "呵呵呵呵", 222));
                homeInfos.add(new HomeInfo("CCC", "嘿嘿嘿嘿", 333));
                homeInfos.add(new HomeInfo("DDD", "ddd", 444));
                homeInfos.add(new HomeInfo("EEE", "eee", 555));
                homeInfos.add(new HomeInfo("FFF", "fff", 666));
                homeInfos.add(new HomeInfo("GGG", "ggg", 777));
                homeInfos.add(new HomeInfo("HHH", "hhh", 888));
                homeInfos.add(new HomeInfo("III", "iii", 999));
                initUI();
                prgBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    public void initVariables(Bundle bundle) {
        //bundle.getParcelable(key);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
