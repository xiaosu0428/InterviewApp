package com.xiaosu.interviewapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.QCollection;

import java.util.ArrayList;
import java.util.List;

/*
 * Time: 2020/7/30
 * Author: Xiaosu
 * Description:
 */
public class QCollectionDao implements Dao {

    private SQLiteDatabase mSQLiteDatabase;

    public QCollectionDao(MyDataBaseHelper myDataBaseHelper) {
        mSQLiteDatabase = myDataBaseHelper.getWritableDatabase();//打开数据库
    }


    @Override
    public void insert(ContentValues values) {
        mSQLiteDatabase.insert("collection",null,values);
        mSQLiteDatabase.close();
    }

    @Override
    public void delete(String _id) {
        mSQLiteDatabase.delete("collection","_id=?",new String[]{_id});
        mSQLiteDatabase.close();
    }

    @Override
    public void update() {

    }

    /*
     * date:
     * desc: 查询所有collection
     */
    @Override
    public String query() {
        Cursor cursor = mSQLiteDatabase.query("collection",null,null,null,null,null,null,null);
        List<QCollection> qCollectionList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Integer total = cursor.getInt(cursor.getColumnIndex("total"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            QCollection qCollection = new QCollection(_id,name,total,img);
            qCollectionList.add(qCollection);
        }
        String json_collections = new Gson().toJson(qCollectionList);
        cursor.close();
        return json_collections;
    }

    @Override
    public String select(String arg) {
        return null;
    }
}
