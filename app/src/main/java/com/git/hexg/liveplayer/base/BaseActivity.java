package com.git.hexg.liveplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HEXG on 2017/11/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentViewLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        init();
    }

    protected void init() {

    }
}
