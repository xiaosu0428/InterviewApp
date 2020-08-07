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
    private MyDataBaseHelper mMyDataBaseHelper;

    public QCollectionDao(MyDataBaseHelper myDataBaseHelper) {
        this.mMyDataBaseHelper = myDataBaseHelper;
    }


    @Override
    public void insert(ContentValues values) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();
        sqLiteDatabase.insert("collection",null,values);
        sqLiteDatabase.close();
    }

    @Override
    public void delete(String _id) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();
        sqLiteDatabase.delete("collection","_id=?",new String[]{_id});
        sqLiteDatabase.close();
    }

    @Override
    public void update(String _id,ContentValues values) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();
        sqLiteDatabase.update("collection",values,"_id = ?",new String[]{_id});
        sqLiteDatabase.close();
    }

    /*
     * date:
     * desc: 查询所有collection
     */
    @Override
    public String query() {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("collection",null,null,null,null,null,null,null);
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
