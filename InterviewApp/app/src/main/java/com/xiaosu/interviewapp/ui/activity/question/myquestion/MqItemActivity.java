package com.xiaosu.interviewapp.ui.activity.question.myquestion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.ui.customview.TitleView;

public class MqItemActivity extends AppCompatActivity implements TitleView.OnClickTitleListener {
    private TitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question_item);
        mTitleView = findViewById(R.id.titleView);
        mTitleView.setOnClickTitleListener(this);
    }

    @Override
    public void onClickTitle() {
        finish();
    }
}
