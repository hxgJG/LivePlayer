package com.git.hexg.liveplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.git.hexg.liveplayer.base.BaseFragment;

import java.util.List;

/**
 * Created by HEXG on 2017/11/27.
 */

public class MainFragAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> list;
    public MainFragAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.list = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (list != null && list.size() > 0){
            return list.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
