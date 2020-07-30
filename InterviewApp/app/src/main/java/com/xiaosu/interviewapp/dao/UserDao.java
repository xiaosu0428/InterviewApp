package com.xiaosu.interviewapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao {

    private SQLiteDatabase mSQLiteDatabase;

    public UserDao(MyDataBaseHelper myDataBaseHelper) {
        mSQLiteDatabase = myDataBaseHelper.getWritableDatabase();//打开数据库
    }

    /*
     * desc: 注册用户插入
     * params:
     * methodName:
     * date: 2020/6/5
     */
    @Override
    public void insert(ContentValues values) {
        mSQLiteDatabase.insert("user", null, values);
        mSQLiteDatabase.close();
    }

    @Override
    public void delete(String _id) {

    }

    @Override
    public void update() {

    }

    /*
     * desc: 查询所有用户的信息
     * params:
     * methodName:
     * date: 2020/6/5
     */
    @Override
    public String query() {
        Cursor cursor = mSQLiteDatabase.query("user", null, null, null, null, null, null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String user_id = cursor.getString(cursor.getColumnIndex("_id"));
            String user_account = cursor.getString(cursor.getColumnIndex("user_account"));
            String user_psw = cursor.getString(cursor.getColumnIndex("user_psw"));
            String user_petName = cursor.getString(cursor.getColumnIndex("user_petName"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            String user_sex = cursor.getString(cursor.getColumnIndex("user_sex"));
            String user_age = cursor.getString(cursor.getColumnIndex("user_age"));
            String user_mail = cursor.getString(cursor.getColumnIndex("user_mail"));
            String user_motto = cursor.getString(cursor.getColumnIndex("user_motto"));
            String user_img = cursor.getString(cursor.getColumnIndex("user_img"));
            User user = new User(user_id, user_account, user_psw, user_petName, user_name, user_sex, user_age, user_mail, user_motto,user_img);
            userList.add(user);
        }
        String json_user = new Gson().toJson(userList);
        cursor.close();
        return json_user;
    }




    /*
     * desc: 根据账号查询一个用户的信息
     * params:arg 账号信息
     * methodName:select
     * date: 2020/6/5
     */
    @Override
    public String select(String arg) {
        Cursor cursor = mSQLiteDatabase.query("user", null, "user_account = ?", new String[]{arg}, null, null, null);
        String json_user = "";
        while (cursor.moveToNext()) {
            String user_id = cursor.getString(cursor.getColumnIndex("user_id"));
            String user_account = cursor.getString(cursor.getColumnIndex("user_account"));
            String user_psw = cursor.getString(cursor.getColumnIndex("user_psw"));
            String user_petName = cursor.getString(cursor.getColumnIndex("user_petName"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            String user_sex = cursor.getString(cursor.getColumnIndex("user_sex"));
            String user_age = cursor.getString(cursor.getColumnIndex("user_age"));
            String user_mail = cursor.getString(cursor.getColumnIndex("user_mail"));
            String user_motto = cursor.getString(cursor.getColumnIndex("user_motto"));
            String user_img = cursor.getString(cursor.getColumnIndex("user_img"));
            User user = new User(user_id, user_account, user_psw, user_petName, user_name, user_sex, user_age, user_mail, user_motto,user_img);
            json_user = new Gson().toJson(user);
        }
        cursor.close();
        return json_user;
    }



}
