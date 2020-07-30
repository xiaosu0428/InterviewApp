package com.xiaosu.interviewapp.ui.activity.question.practice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.ui.customview.TitleView;

public class QuestionPracticeActivity extends AppCompatActivity implements TitleView.OnClickTitleListener, View.OnClickListener {

    private TitleView mTitleView;
    private TextView tv_bankSetting;

    private TextView tv_order;//出题顺序
    private ImageButton ib_prev;
    private ImageButton ib_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_practice);
        initViews();
    }

    private void initViews() {
        mTitleView = findViewById(R.id.titleView);
        mTitleView.setOnClickTitleListener(this);

        tv_bankSetting = findViewById(R.id.tv_bankSetting);
        tv_bankSetting.setOnClickListener(this);

        tv_order = findViewById(R.id.tv_order);
        Typeface typeface_st = Typeface.createFromAsset(getAssets(), "font/st.ttf");//宋体
        tv_order.setTypeface(typeface_st);
        ib_prev = findViewById(R.id.ib_prev);
        ib_next = findViewById(R.id.ib_next);
        ib_prev.setOnClickListener(this);
        ib_next.setOnClickListener(this);
    }

    @Override
    public void onClickTitle() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_prev:
            case R.id.ib_next:
                if(tv_order.getText().toString().equals("顺序")){
                    tv_order.setText("随机");
                }else {
                    tv_order.setText("顺序");
                }
                break;
            default:
                break;
        }
        if (v.getId() == R.id.tv_bankSetting) {

        }
    }
}
