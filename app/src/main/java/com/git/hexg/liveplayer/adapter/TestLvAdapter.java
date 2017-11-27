package com.git.hexg.liveplayer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.git.hexg.liveplayer.R;

import java.util.List;

/**
 * Created by HEXG on 2017/11/20.
 */

public class TestLvAdapter extends BaseAdapter {

    public static final int ITEM_TYPE_1 = 0;
    public static final int ITEM_TYPE_2 = 1;

    private List<String> list;
    private Context context;

    public TestLvAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null) {
            if (position % 2 == 0 || position % 5 == 0) {
                return ITEM_TYPE_2;
            }
        }
        return ITEM_TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        ListTextHolder textHolder = null;
        ListImageHolder imageHolder = null;
        try {
            final String s = list.get(position);
            switch (getItemViewType(position)) {
                case ITEM_TYPE_1:
                    if (convertView == null) {
                        view = View.inflate(context, R.layout.item_test_list, null);
                        textHolder = new ListTextHolder();
                        textHolder.text = (TextView) view.findViewById(R.id.text);
                        textHolder.ll_lv_item_1 = (LinearLayout) view.findViewById(R.id.ll_lv_item_1);
                        view.setTag(textHolder);
                    } else {
                        view = convertView;
                        textHolder = (ListTextHolder) view.getTag();
                    }
                    textHolder.text.setText(s);
                    textHolder.ll_lv_item_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case ITEM_TYPE_2:
                    if (convertView == null) {
                        view = View.inflate(context, R.layout.item_test_list2, null);
                        imageHolder = new ListImageHolder();
                        imageHolder.iv_test = (ImageView) view.findViewById(R.id.iv_test);
                        imageHolder.ll_lv_item_2 = (LinearLayout) view.findViewById(R.id.ll_lv_item_2);
                        view.setTag(imageHolder);
                    } else {
                        view = convertView;
                        imageHolder = (ListImageHolder) view.getTag();
                    }
                    imageHolder.iv_test.setImageResource(R.mipmap.ic_launcher);
                    imageHolder.ll_lv_item_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"图片:"+s,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TestLvAdapter", e.toString());
        }
        return view;
    }

    public class ListTextHolder {
        TextView text;
        LinearLayout ll_lv_item_1;
    }

    public class ListImageHolder {
        ImageView iv_test;
        LinearLayout ll_lv_item_2;
    }
}
