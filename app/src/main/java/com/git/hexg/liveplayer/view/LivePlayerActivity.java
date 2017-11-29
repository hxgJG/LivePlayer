package com.git.hexg.liveplayer.view;

import android.os.Parcelable;
import android.widget.TextView;

import com.git.hexg.liveplayer.R;
import com.git.hexg.liveplayer.base.BaseActivity;
import com.git.hexg.liveplayer.bean.HomeInfo;
import com.git.hexg.liveplayer.util.StringUtils;

import org.w3c.dom.Text;

/**
 * Created by HEXG on 2017/11/29.
 */

public class LivePlayerActivity extends BaseActivity {

    private TextView tv_title;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_live_play;
    }

    @Override
    protected void init() {
        HomeInfo homeInfo = getIntent().getParcelableExtra("homeInfo");
        if (homeInfo == null) return;
        String str1 = homeInfo.getStr1();
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!StringUtils.isNullOrEmpty(str1)) {
            tv_title.setText(str1);
        }

    }
}
