package com.xiaosu.interviewapp.ui.activity.category.science;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.ui.adapter.category.CategorySubjectExListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 分类》学术门类》点击某学科打开的Activity
 */
public class CategoryXMajorActivity extends AppCompatActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupExpandListener {
    private static int CATEGORY_ARRAY_ID = R.array.x_philosophy;

    private List<String> groupList = new ArrayList<>();

    private List<List<String>> childList = new ArrayList<>();

    private TextView tv_title;

    private ExpandableListView mExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_major);
        bindViews();
        initExpandListViewData();
    }

    /*
     * desc: 加载数据
     * params:
     * methodName:
     * date: 2020/6/8
     */
    protected void initExpandListViewData() {
        Resources resources = getResources();
        switch (getBundleData()) {
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
        CategorySubjectExListViewAdapter SExpandListViewAdapter = new CategorySubjectExListViewAdapter(this);
        SExpandListViewAdapter.setGroupList(groupList);
        SExpandListViewAdapter.setChildList(childList);
        mExpandableListView.setAdapter(SExpandListViewAdapter);
        //控制他只能打开一个组,此监听与group的点击监听不兼容
        mExpandableListView.setOnGroupExpandListener(this);
    }

    /*
     * desc: 绑定控件
     * params:
     * methodName:
     * date: 2020/6/8
     */

    protected void bindViews() {
        //标题栏返回键
        tv_title = findViewById(R.id.tv_title);
        Typeface typeface_xmlt = Typeface.createFromAsset(getAssets(), "font/xmlt.ttf");
        tv_title.setTypeface(typeface_xmlt);//vista黑体
        tv_title.setText(getBundleData());
        tv_title.setOnClickListener(this);

        mExpandableListView = findViewById(R.id.elv_subject);
        mExpandableListView.setOnChildClickListener(this);//child点击


    }

    private String getBundleData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        assert bundle != null;
        return bundle.getString("category");
    }


    /*
     * desc: 其他按钮点击事件
     * params:
     * methodName:
     * date: 2020/6/8
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
                finish();
                break;
            default:
                break;
        }
    }

    /*
     * desc: //子控件监听
     * params:
     * methodName:
     * date: 2020/6/8
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getApplicationContext(), childList.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
        return true;
    }

    /*
     * desc: 只展开一个组
     * params:
     * methodName:
     * date: 2020/6/8
     */
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
