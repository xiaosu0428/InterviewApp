package com.xiaosu.interviewapp.db;

public class SQL {

    //创建user表
    public static final String CREATE_TABLE_USER = "create table user ("
            + "_id text primary key,"
            + "user_account text,"
            + "user_psw text,"
            + "user_petName text,"
            + "user_name text,"
            + "user_sex text,"
            + "user_age text,"
            + "user_mail text,"
            + "user_motto text,"
            + "user_img text)";


    /*
    private String _id;
    private String title;
    private String category;//门类
    private String subject;//学科
    private String major;//专业
    private String content;
    private String time;
    */
    public static final String CREATE_TABLE_MY_QUESTION = "create table my_question(" +
            "_id text primary key," +
            "title text," +
            "type text,"+
            "category text," +
            "subject text," +
            "major text," +
            "content text," +
            "time text)";

    public static final  String CREATE_TABLE_COLLECTION = "create table collection(" +
            "_id text primary key," +
            "name text," +
            "total integer,"+
            "img text)";
}
