package com.xiaosu.interviewapp.ui.activity.question.myquestion;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.model.MyQuestion;

public class MqDetailsActivity extends AppCompatActivity {

    private TextView tv_details;//详情页说明文字
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;//问题标题
    private TextView tv_questionTitle;
    private TextView tv_questionMajor;
    private TextView tv_questionTime;
    private TextView tv_questionContent;//问题内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question_details);

        initView();


    }

    private void initView() {
        tv_details = findViewById(R.id.tv_details);
        mToolbar = findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        tv_questionTitle = findViewById(R.id.tv_questionTitle);
        tv_questionMajor = findViewById(R.id.tv_questionMajor);
        tv_questionTime = findViewById(R.id.tv_questionTime);
        tv_questionContent = findViewById(R.id.tv_questionContent);

        Typeface typeface_xmlt = Typeface.createFromAsset(getAssets(), "font/xmlt.ttf");
        tv_details.setTypeface(typeface_xmlt);//详情页标题：字体小米兰亭

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //数据
        Bundle bundle = getBundle();
        MyQuestion myQuestion = new Gson().fromJson(bundle.getString("questionDetails"),MyQuestion.class);

        //问题标题
        Typeface typeface_wr_zht = Typeface.createFromAsset(getAssets(), "font/wr_zht.ttf");//微软正黑体
        mCollapsingToolbarLayout.setExpandedTitleTypeface(typeface_wr_zht);
        mCollapsingToolbarLayout.setCollapsedTitleTypeface(typeface_wr_zht);
        mCollapsingToolbarLayout.setTitle(myQuestion.getTitle());
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        //问题标题
        tv_questionTitle.setTypeface(typeface_wr_zht);
        tv_questionTitle.setText(myQuestion.getTitle());
        //问题专业
        tv_questionMajor.setText(String.format("%s>>%s>>%s", myQuestion.getCategory(), myQuestion.getSubject(), myQuestion.getMajor()));
        //问题时间
        tv_questionTime.setText(myQuestion.getTime());
        //问题内容
        Typeface typeface_st = Typeface.createFromAsset(getAssets(), "font/st.ttf");
        tv_questionContent.setTypeface(typeface_st);
        tv_questionContent.setText(myQuestion.getContent());
    }

    private Bundle getBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        assert bundle != null;
        return bundle;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
