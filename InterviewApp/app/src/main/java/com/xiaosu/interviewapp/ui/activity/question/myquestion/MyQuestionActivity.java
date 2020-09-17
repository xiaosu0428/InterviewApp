package com.xiaosu.interviewapp.ui.activity.question.myquestion;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.xiaosu.interviewapp.ui.adapter.listview.MyQuestionListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 自定义》我的问题
 */
public class MyQuestionActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, TextWatcher {
    private static final String TAG = "MyQuestionActivity";

    private TextView tv_title;//页面标题栏

    private EditText et_searchView;//搜索框

    private TextView tv_notFound;

    private ListView mListView;//问题列表

    private List<MyQuestion> mList = new ArrayList<>();

    private MyQuestionListViewAdapter mMyQuestionListViewAdapter;
    private MyQuestionDao mMyQuestionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        bindViews();
        initData();
    }

    /*
     * date: 2020/8/10
     * author: xiao su
     * method：
     * desc: 加载布局控件
     */
    private void bindViews() {
        tv_title = findViewById(R.id.tv_title);
        et_searchView = findViewById(R.id.et_searchView);
        tv_notFound = findViewById(R.id.tv_notFound);
        mListView = findViewById(R.id.lv_mq);

        tv_title.setOnClickListener(this);
        Typeface typeface_xmlt = Typeface.createFromAsset(getAssets(), "font/xmlt.ttf");
        tv_title.setTypeface(typeface_xmlt);//小米兰亭

        et_searchView.setOnEditorActionListener(this);
        et_searchView.addTextChangedListener(this);

    }

    /*
     * date: 2020/8/10
     * author: xiao su
     * method：
     * desc: 初始化页面数据
     */
    private void initData() {
        CreateDB createDB = new CreateDB(this, "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        mMyQuestionDao = new MyQuestionDao(myDataBaseHelper);
        String myQuestionList_json = mMyQuestionDao.query();
        mList.clear();
        mList = new Gson().fromJson(myQuestionList_json, new TypeToken<List<MyQuestion>>() {
        }.getType());
        if(mList.size() == 0){
            tv_notFound.setText("暂无数据");
        }
        mMyQuestionListViewAdapter = new MyQuestionListViewAdapter(this);
        mMyQuestionListViewAdapter.setList(mList);//设置数据
        mListView.setAdapter(mMyQuestionListViewAdapter);//添加适配器

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
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ("".equals(et_searchView.getText().toString())) {
            return false;
        }
        if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            searchData();//搜索
            Toast.makeText(this, et_searchView.getText().toString(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /*
     * date: 2020/8/10
     * author: xiao su
     * method：
     * desc: 搜索
     */
    private void searchData() {
        CreateDB createDB = new CreateDB(this, "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        mMyQuestionDao = new MyQuestionDao(myDataBaseHelper);
        String myQuestionList_json = mMyQuestionDao.search(et_searchView.getText().toString());
        mList.clear();
        mList = new Gson().fromJson(myQuestionList_json, new TypeToken<List<MyQuestion>>() {
        }.getType());
        Log.d(TAG, "searchData: "+ mList.size());
        if(mList.size() == 0){
            tv_notFound.setText("没找到");
        }
        mMyQuestionListViewAdapter = new MyQuestionListViewAdapter(this);
        mMyQuestionListViewAdapter.setList(mList);//设置数据
        mListView.setAdapter(mMyQuestionListViewAdapter);//添加适配器

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //输入前操作
        Log.d(TAG, "beforeTextChanged: " + s + "-" + start + "-" + count + "-" + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //输入框内容改变时
        Log.d(TAG, "onTextChanged:" + s + "-" + start + "-" + before + "-" + count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //输入框内容改变后
        if("".equals(s.toString())){
            initData();
        }else {
            searchData();
        }
        Log.d(TAG, "afterTextChanged: 输入结束");
    }
}
