package com.xiaosu.interviewapp.model;

/*
 * Time: 2020/7/17
 * Author: Xiaosu
 * Description:
 */
public class QCollection {
    private String _id;
    private String title;
    private Integer total;
    private String img;

    public QCollection(String _id, String title, Integer total, String img) {
        this._id = _id;
        this.title = title;
        this.total = total;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
