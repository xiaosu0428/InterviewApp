package com.xiaosu.interviewapp.ui.fragment.question.choosemajor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.base.BaseFragment;
import com.xiaosu.interviewapp.ui.activity.category.science.CategoryXSubject;
import com.xiaosu.interviewapp.ui.activity.question.createmq.CreateMqActivity;
import com.xiaosu.interviewapp.ui.adapter.category.CategorySubjectExListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * Time: 2020/6/11
 * Author: Xiaosu
 * Description:创建我的问题》选择专业下的Fragment
 */
public class ScienceFragment extends BaseFragment implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupExpandListener {
    private static int CATEGORY_ARRAY_ID = R.array.x_philosophy;

    private static final String TAG = "CustomXMajorFragment";

    private String category ;//门类

    private List<String> groupList = new ArrayList<>();

    private List<List<String>> childList = new ArrayList<>();

    private ExpandableListView mExpandableListView;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_question_science_philosophy;
    }

    @Override
    protected void initView(View view) {
        bindViews(view);
        initExpandListViewData();
    }

    public ScienceFragment(String category) {
        this.category = category;
    }

    private void bindViews(View view) {
        mExpandableListView = view.findViewById(R.id.elv_subject);
        mExpandableListView.setOnChildClickListener(this);//child点击
    }


    private void initExpandListViewData() {
        Resources resources = getResources();
        switch (category) {
            case "哲学":
                CATEGORY_ARRAY_ID = R.array.x_philosophy;
                break;
            case "经济学":
                CATEGORY_ARRAY_ID = R.array.x_economics;
                break;
            case "法学":
                CATEGORY_ARRAY_ID = R.array.x_law;
                break;
            case "教育学":
                CATEGORY_ARRAY_ID = R.array.x_education;
                break;
            case "历史学":
                CATEGORY_ARRAY_ID = R.array.x_history;
                break;
            case "文学":
                CATEGORY_ARRAY_ID = R.array.x_literature;
                break;
            case "理学":
                CATEGORY_ARRAY_ID = R.array.x_lixue;
                break;
            case "工学":
                CATEGORY_ARRAY_ID = R.array.x_engineering;
                break;
            case "农学":
                CATEGORY_ARRAY_ID = R.array.x_agronomy;
                break;
            case "医学":
                CATEGORY_ARRAY_ID = R.array.x_medical;
                break;
            case "军事学":
                CATEGORY_ARRAY_ID = R.array.x_military;
                break;
            case "管理学":
                CATEGORY_ARRAY_ID = R.array.x_management;
                break;
            case "艺术学":
                CATEGORY_ARRAY_ID = R.array.x_art;
                break;
        }
        CategoryXSubject categoryXSubject = new CategoryXSubject(resources, CATEGORY_ARRAY_ID);
        groupList = categoryXSubject.getGroupList();
        childList = categoryXSubject.getChildList();
        CategorySubjectExListViewAdapter SExpandListViewAdapter = new CategorySubjectExListViewAdapter(getContext());
        SExpandListViewAdapter.setGroupList(groupList);
        SExpandListViewAdapter.setChildList(childList);
        mExpandableListView.setAdapter(SExpandListViewAdapter);
        //控制他只能打开一个组,此监听与group的点击监听不兼容
        mExpandableListView.setOnGroupExpandListener(this);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String subject = groupList.get(groupPosition);
        String major = childList.get(groupPosition).get(childPosition);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Temp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mqCategory", category);
        editor.putString("mqSubject", subject);
        editor.putString("mqMajor", major);
        editor.apply();
        Toast.makeText(getContext(), category + "》" + subject + "》" + major, Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("TAG",TAG);
        Intent intent= new Intent(getActivity(), CreateMqActivity.class);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
        getActivity().finish();
        return true;
    }

    @Override
    public void onGroupExpand(int groupPosition) {
        int count = mExpandableListView.getExpandableListAdapter().getGroupCount();
        for (int j = 0; j < count; j++) {
            if (j != groupPosition) {
                mExpandableListView.collapseGroup(j);
            }
        }
    }


}
