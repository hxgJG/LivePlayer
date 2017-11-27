package com.git.hexg.liveplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.git.hexg.liveplayer.R;
import com.git.hexg.liveplayer.base.BaseFragment;
import com.git.hexg.liveplayer.util.StringUtils;

/**
 * Created by HEXG on 2017/11/22.
 */

public class HomepageFragment extends BaseFragment {

    TextView tv_title;
    ProgressBar prgBar;
    private Handler handler = new Handler();

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        prgBar = (ProgressBar) view.findViewById(R.id.prgBar);
        return view;
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
                if (!StringUtils.isNullOrEmpty(getTitle())) {
                    tv_title.setText(getTitle());
                }
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
