package com.xiaosu.interviewapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDataBaseHelper";



    /*
     * desc: 构造函数
     * params: context 上下文；name 数据库名称；factory 游标工厂；version 版本号，只能为整型且只增不减
     * methodName:
     * date: 2020/6/3
     */
    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*
     * desc: 首次创建数据库时执行
     * params:
     * methodName: onCreate
     * date: 2020/6/3
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(SQL.CREATE_TABLE_USER);
            Log.d(TAG, "onCreate: user表已创建");
            db.execSQL(SQL.CREATE_TABLE_MY_QUESTION);
            Log.d(TAG, "onCreate: my_question表已创建");
            db.execSQL(SQL.CREATE_TABLE_COLLECTION);
            Log.d(TAG, "onCreate: collection表已创建");
        }catch (Exception e){
            Log.d(TAG, "onCreate: 数据表创建异常 ===>");
            e.printStackTrace();
        }
    }

    /*
     * desc: 更新数据库时调用，需要变更版本号才会更新
     * methodName: onUpgrade
     * date: 2020/6/3
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL.UPDATE_TABLE_USER);
//        Log.d(TAG, "onUpgrade: 数据库已更新");
    }
}
