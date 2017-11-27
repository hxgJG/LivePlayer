package com.git.hexg.liveplayer.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.git.hexg.liveplayer.R;
import com.git.hexg.liveplayer.adapter.TestLvAdapter;
import com.git.hexg.liveplayer.base.BaseActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEXG on 2017/11/19.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener,GestureDetector.OnGestureListener {

    private TextView tv_test;
    private ListView listView;
    private List<String> list;
    private TestLvAdapter lvAdapter;
    private GestureDetector detector;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
        detector = new GestureDetector(this, this);
        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_test.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        initData();
        lvAdapter = new TestLvAdapter(this, list);
        listView.setAdapter(lvAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 'A'; i<='Z'; i++){
            list.add(String.valueOf((char)i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_test:
                test();
                break;
        }
    }

    private void test() {
        //
    }

    private void inputStream(String path){
        File file = new File(path);
        InputStream is = null;
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            is = new FileInputStream(file);
            byte [] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1){
                System.out.println(new String(buf,0,len));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void outputStream(String path){
        File file = new File(path);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(69);//文件中产生ASC码对应的字符
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制
     * @param originalFile 源文件
     * @param goalFile 目标文件
     */
    private void copy(String originalFile, String goalFile){
        File origin = new File(originalFile);
        File goal = new File(goalFile);
        if (!origin.exists()){
            try {
                origin.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(origin);
            os = new FileOutputStream(goal);
            byte [] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1){
                os.write(buff,0,len);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reader(String path){
        File file = new File(path);
        OutputStream os = null;
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            os = new FileOutputStream(file);
            int len = 0;
            while ((len = reader.read()) != -1){
                os.write(len);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (os != null){
                    os.close();
                }
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    /**
     * 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("GestureDetector","onDown：轻触触摸屏");
        return false;
    }

    /**
     * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
     * 注意和onDown()的区别，强调的是没有松开或者拖动的状态；
     * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
     * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
     * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间，
     * 拖动了，就不执行onShowPress。
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("GestureDetector","onShowPress：轻触触摸屏，尚未松开或拖动");
    }

    /**
     * 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
     * 轻击一下屏幕，立刻抬起来，才会有这个触发
     * 从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,
     * 那就不再算是Single操作了,所以这个事件 就不再响应
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("GestureDetector","onSingleTapUp：（轻触触摸屏后）松开");
        return false;
    }

    /**
     * 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("GestureDetector","onScroll：按下触摸屏，并拖动");
        return false;
    }

    /**
     * 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("GestureDetector","onLongPress：长按触摸屏");
    }

    /**
     * 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("GestureDetector","onFling：按下触摸屏、快速移动后松开");
        return false;
    }
}
