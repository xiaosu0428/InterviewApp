package com.xiaosu.interviewapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.MyQuestion;

import java.util.ArrayList;
import java.util.List;

public class MyQuestionDao implements Dao {
    private SQLiteDatabase mSQLiteDatabase;

    public MyQuestionDao(MyDataBaseHelper myDataBaseHelper) {
        mSQLiteDatabase = myDataBaseHelper.getWritableDatabase();//打开数据库
    }

    @Override
    public void insert(ContentValues values) {
        mSQLiteDatabase.insert("my_question", null, values);
        mSQLiteDatabase.close();
    }

    @Override
    public void delete(String _id) {
        mSQLiteDatabase.delete("my_question","_id=?",new String[]{_id});
        mSQLiteDatabase.close();
    }

    @Override
    public void update() {

    }

    @Override
    public String query() {
        Cursor cursor = mSQLiteDatabase.query("my_question", null, null, null, null, null, null);
        List<MyQuestion> myQuestionList = new ArrayList<>(0);
        while (cursor.moveToNext()) {
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String subject = cursor.getString(cursor.getColumnIndex("subject"));
            String major = cursor.getString(cursor.getColumnIndex("major"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            MyQuestion myQuestion= new MyQuestion(_id,title,type,category,subject,major,content,time);
            myQuestionList.add(myQuestion);
        }
        cursor.close();
        return new Gson().toJson(myQuestionList);
    }

    @Override
    public String  select(String _id) {

        return null;
    }
}
