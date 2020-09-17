package com.xiaosu.interviewapp.ui.adapter.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaosu.interviewapp.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Time: 2020/9/16
 * Author: Xiaosu
 * Description:
 */
public class QuestionSelectedGroupListViewAdapter extends BaseAdapter {
    private static final String TAG = "QuestionSelectedGroupLi";
    
    private Context mContext;

    private List<String> mList  = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public QuestionSelectedGroupListViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_listview_select_group,null);
        if(!mList.isEmpty()){
            Log.d(TAG, "getView: " + mList.get(position));
        }
        TextView textView_questionTitle = view.findViewById(R.id.textView_questionTitle);
        textView_questionTitle.setText(mList.get(position));
        return view;
    }
}
