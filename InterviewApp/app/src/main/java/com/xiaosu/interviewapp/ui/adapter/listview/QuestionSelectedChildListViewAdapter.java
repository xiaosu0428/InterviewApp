package com.xiaosu.interviewapp.ui.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.model.MyQuestion;

import java.util.ArrayList;
import java.util.List;

/*
 * Time: 2020/9/16
 * Author: Xiaosu
 * Description:
 */
public class QuestionSelectedChildListViewAdapter extends BaseAdapter {
    private Context mContext;

    private List<MyQuestion> mMyQuestionList  = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    @Override
    public int getCount() {
        return mMyQuestionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMyQuestionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_listview_selected_child,null);

        return view;
    }
}
