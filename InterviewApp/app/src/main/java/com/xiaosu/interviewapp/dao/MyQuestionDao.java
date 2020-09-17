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

    private MyDataBaseHelper mMyDataBaseHelper;

    public MyQuestionDao(MyDataBaseHelper myDataBaseHelper) {
        this.mMyDataBaseHelper = myDataBaseHelper;
    }

    @Override
    public void insert(ContentValues values) {
        SQLiteDatabase sQLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        sQLiteDatabase.insert("my_question", null, values);
        sQLiteDatabase.close();//关闭数据库
    }

    @Override
    public void delete(String _id) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        sqLiteDatabase.delete("my_question", "_id=?", new String[]{_id});
        sqLiteDatabase.close();
    }

    @Override
    public void update(String _id, ContentValues values) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        sqLiteDatabase.update("my_question",values,"_id = ?",new String[]{_id});
        sqLiteDatabase.close();

    }

    @Override
    public String query() {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        Cursor cursor = sqLiteDatabase.query("my_question", null, null, null, null, null, null);
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
            MyQuestion myQuestion = new MyQuestion(_id, title, type, category, subject, major, content, time);
            myQuestionList.add(myQuestion);
        }
        cursor.close();
        return new Gson().toJson(myQuestionList);
    }

    @Override
    public String select(String _id) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        return null;
    }

    public String search(String where) {
        SQLiteDatabase sqLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//打开数据库
        Cursor cursor = sqLiteDatabase.rawQuery("select * from my_question where title LIKE ?", new String[]{"%" + where + "%"});
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
            MyQuestion myQuestion = new MyQuestion(_id, title, type, category, subject, major, content, time);
            myQuestionList.add(myQuestion);
        }
        cursor.close();
        return new Gson().toJson(myQuestionList);
    }

}
