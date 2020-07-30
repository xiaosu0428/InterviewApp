package com.xiaosu.interviewapp.db;

import android.content.Context;

public class CreateDB {
    private MyDataBaseHelper mMyDataBaseHelper;

    public CreateDB(Context context, String dbName) {
        mMyDataBaseHelper = new MyDataBaseHelper(context, dbName, null, 1);
    }

    public MyDataBaseHelper getMyDataBaseHelper() {
        return mMyDataBaseHelper;
    }
}
