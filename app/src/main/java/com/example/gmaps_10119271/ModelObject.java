package com.example.gmaps_10119271;

import com.example.gmaps_10119271.R;

public enum ModelObject {

    RED(R.string.red, R.layout.view_red),
    BLUE(R.string.blue, R.layout.view_blue);

    private int mTitleResId;
    private int mLayoutResId;
    //    NIM : 10119271
    //    Nama : Ikra Esa A'raaf Mahardika
    //    Kelas : IF 7
    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
