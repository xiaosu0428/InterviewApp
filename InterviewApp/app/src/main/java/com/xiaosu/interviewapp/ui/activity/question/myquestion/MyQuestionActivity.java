package com.xiaosu.interviewapp.ui.activity.question.myquestion;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.MyQuestionDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.MyQuestion;
import com.xiaosu.interviewapp.ui.adapter.question.MyQuestionListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 自定义》我的问题
 */
public class MyQuestionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "MyQuestionActivity";

    private TextView tv_title;//页面标题栏

    private ListView mListView;

    private List<MyQuestion> mList = new ArrayList<>();

    private MyQuestionListViewAdapter mMyQuestionListViewAdapter;
    private MyQuestionDao mMyQuestionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        bindViews();
        CreateDB createDB = new CreateDB(this, "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        mMyQuestionDao = new MyQuestionDao(myDataBaseHelper);
        String myQuestionList_json = mMyQuestionDao.query();
        mList = new Gson().fromJson(myQuestionList_json, new TypeToken<List<MyQuestion>>() {
        }.getType());
        mMyQuestionListViewAdapter = new MyQuestionListViewAdapter(this);
        mMyQuestionListViewAdapter.setList(mList);//设置数据
        mListView.setAdapter(mMyQuestionListViewAdapter);//添加适配器
        mListView.setOnItemClickListener(this);
    }

    private void bindViews() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setOnClickListener(this);
        Typeface typeface_xmlt = Typeface.createFromAsset(getAssets(), "font/xmlt.ttf");
        tv_title.setTypeface(typeface_xmlt);//vista黑体

        mListView = findViewById(R.id.lv_mq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,mList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
    }
}
