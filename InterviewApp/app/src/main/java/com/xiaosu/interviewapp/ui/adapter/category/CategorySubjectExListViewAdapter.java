package com.xiaosu.interviewapp.ui.adapter.category;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xiaosu.interviewapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategorySubjectExListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    private List<String> groupList = new ArrayList<>();

    private List<List<String>> childList = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    public CategorySubjectExListViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }

    public void setChildList(List<List<String>> childList) {
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_elv_group,null);
        TextView tv_elvGroup = view.findViewById(R.id.tv_elvGroup);
        Typeface typeface_fs = Typeface.createFromAsset(mContext.getAssets(),"font/fs.ttf");
        tv_elvGroup.setTypeface(typeface_fs);
        tv_elvGroup.setText(groupList.get(groupPosition));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_elv_child,null);
        TextView tv_elvChild = view.findViewById(R.id.tv_elvChild);
        Typeface typeface_fs = Typeface.createFromAsset(mContext.getAssets(),"font/fs.ttf");
        tv_elvChild.setTypeface(typeface_fs);
        tv_elvChild.setText(childList.get(groupPosition).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
