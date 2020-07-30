package com.xiaosu.interviewapp.model;

public class MyQuestion {
    private String _id;
    private String title;
    private String type;//学 专
    private String category;//门类
    private String subject;//学科
    private String major;//专业
    private String content;
    private String time;

    public MyQuestion(String _id, String title, String type, String category, String subject, String major, String content, String time) {
        this._id = _id;
        this.title = title;
        this.type = type;
        this.category = category;
        this.subject = subject;
        this.major = major;
        this.content = content;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
