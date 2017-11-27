package com.git.hexg.liveplayer.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HEXG on 2017/11/22.
 */

public abstract class BaseFragment extends Fragment {

    private Activity mActivity;//用mActivity代替getActivity()
    public String fragmentTitle;
    private boolean isFragmentVisible;//是否可见状态
    /**
     * 标志位，View已经初始化完成。
     * 用isAdded()属性代替
     * isPrepared还是准一些,isAdded有可能出现onCreateView没走完但是isAdded了
     */
    private boolean isPrepared;
    private boolean isFirstLoad = true;
    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link BaseFragment#setForceLoad(boolean)}来让Fragment下次执行initData
     */
    private boolean forceLoad = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0){
            initVariables(bundle);
        }
    }
    /**
     * 被ViewPager移出的Fragment 下次显示时会从getArguments()中重新获取数据
     * 所以若需要刷新被移除Fragment内的数据需要重新put数据 eg:
     * Bundle args = getArguments();
     * if (args != null) {
     *      args.putParcelable(KEY, info);
     * }
     */
    public void initVariables(Bundle bundle) {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        View view = initViews(inflater, container, savedInstanceState);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (isPrepared() && isFragmentVisible()) {
            if (forceLoad || isFirstLoad()) {
                forceLoad = false;
                isFirstLoad = false;
                initData();
            }
        }
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    protected abstract void initData();

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     */
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    public String getTitle() {
        if (null == fragmentTitle) {
            setDefaultFragmentTitle(null);
        }
        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
    }

    public void setTitle(String title) {
        fragmentTitle = title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    protected void onInvisible() {
        isFragmentVisible = false;
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 设置fragment的Title直接调用 {@link BaseFragment#setTitle(String)},若不显示该title 可以不做处理
     *
     * @param title 一般用于显示在TabLayout的标题
     */
    protected abstract void setDefaultFragmentTitle(String title);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
