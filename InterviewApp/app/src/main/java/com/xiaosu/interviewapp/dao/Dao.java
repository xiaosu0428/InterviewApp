package com.xiaosu.interviewapp.dao;

import android.content.ContentValues;

interface Dao {
    void insert(ContentValues values);//插入一条记录

    void delete(String _id);//根据_ID删除一条记录

    void update();//更新记录

    String query();//查询所有记录，返回json

    String select(String arg);//根据某个参数查询记录，返回json

}
