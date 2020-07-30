package com.xiaosu.interviewapp.model;

public class User {
    private String _id;
    private String user_account;
    private String user_psw;
    private String user_petName;
    private String user_name;
    private String user_sex;
    private String user_age;
    private String user_mail;
    private String user_motto;
    private String user_img;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_psw() {
        return user_psw;
    }

    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }

    public String getUser_petName() {
        return user_petName;
    }

    public void setUser_petName(String user_petName) {
        this.user_petName = user_petName;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_motto() {
        return user_motto;
    }

    public void setUser_motto(String user_motto) {
        this.user_motto = user_motto;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public User(String _id, String user_account, String user_psw, String user_petName, String user_name, String user_sex, String user_age, String user_mail, String user_motto, String user_img) {
        this._id = _id;
        this.user_account = user_account;
        this.user_psw = user_psw;
        this.user_petName = user_petName;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_mail = user_mail;
        this.user_motto = user_motto;
        this.user_img = user_img;
    }
}
