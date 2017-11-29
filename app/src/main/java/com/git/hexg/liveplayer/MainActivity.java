package com.git.hexg.liveplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.git.hexg.liveplayer.adapter.MainFragAdapter;
import com.git.hexg.liveplayer.base.BaseActivity;
import com.git.hexg.liveplayer.base.BaseFragment;
import com.git.hexg.liveplayer.fragment.ExtendFragment;
import com.git.hexg.liveplayer.fragment.HomepageFragment;
import com.git.hexg.liveplayer.fragment.MoreFragment;
import com.git.hexg.liveplayer.util.T;
import com.git.hexg.liveplayer.view.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private TextView tv_test,tab1,tab2,tab3;
    private ViewPager viewPager;
    private List<BaseFragment> fragments;
    private MainFragAdapter fragAdapter;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initData();
        initView();
        setClickListener();
        initFragment();
    }

    private void initData() {
        context = this;
    }

    private void initView() {
        tv_test = (TextView) findViewById(R.id.tv_test);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);
    }

    private void setClickListener() {
        tv_test.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomepageFragment());
        fragments.add(new ExtendFragment());
        fragments.add(new MoreFragment());
        fragAdapter = new MainFragAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                refreshTab(position);
            }
        });
    }

    private void refreshTab(int position){
        switch (position)
        {
            case 0:
                tab1.setTextColor(getResources().getColor(R.color.white));
                tab1.setBackgroundColor(getResources().getColor(R.color.selected_bg));
                tab2.setTextColor(getResources().getColor(R.color.black));
                tab2.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                tab3.setTextColor(getResources().getColor(R.color.black));
                tab3.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                break;
            case 1:
                tab1.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                tab2.setTextColor(getResources().getColor(R.color.white));
                tab2.setBackgroundColor(getResources().getColor(R.color.selected_bg));
                tab3.setTextColor(getResources().getColor(R.color.black));
                tab3.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                break;
            case 2:
                tab1.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                tab2.setTextColor(getResources().getColor(R.color.black));
                tab2.setBackgroundColor(getResources().getColor(R.color.unselected_bg));
                tab3.setTextColor(getResources().getColor(R.color.white));
                tab3.setBackgroundColor(getResources().getColor(R.color.selected_bg));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_test:
                gotoTest();
                break;
            case R.id.tab1:
                clickTab1();
                break;
            case R.id.tab2:
                clickTab2();
                break;
            case R.id.tab3:
                clickTab3();
                break;
        }
    }

    private void clickTab1() {
        T.showShort(this,"首页");
        viewPager.setCurrentItem(0);
    }
    private void clickTab2() {
        T.showShort(this,"扩展");
        viewPager.setCurrentItem(1);
    }
    private void clickTab3() {
        T.showShort(this,"更多");
        viewPager.setCurrentItem(2);
    }

    private void gotoTest() {
        this.startActivity(new Intent(this, TestActivity.class));
    }
}
