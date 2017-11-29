package com.git.hexg.liveplayer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HEXG on 2017/11/27.
 */

public class HomeInfo implements Parcelable {
    private String str1;
    private String str2;
    private int val;

    public HomeInfo(String str1, String str2, int val) {
        this.str1 = str1;
        this.str2 = str2;
        this.val = val;
    }

    protected HomeInfo(Parcel in) {
        str1 = in.readString();
        str2 = in.readString();
        val = in.readInt();
    }

    public static final Creator<HomeInfo> CREATOR = new Creator<HomeInfo>() {
        @Override
        public HomeInfo createFromParcel(Parcel in) {
            return new HomeInfo(in);
        }

        @Override
        public HomeInfo[] newArray(int size) {
            return new HomeInfo[size];
        }
    };

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str1);
        dest.writeString(str2);
        dest.writeInt(val);
    }
}
