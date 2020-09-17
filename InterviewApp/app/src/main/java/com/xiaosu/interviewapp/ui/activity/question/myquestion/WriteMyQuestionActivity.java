package com.xiaosu.interviewapp.ui.activity.question.myquestion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.model.MyQuestion;

import java.util.Objects;

public class WriteMyQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText_questionTitle;
    private RadioGroup mRadioGroup_questionType;
    private RadioButton mRadioButton_science;
    private RadioButton mRadioButton_speciality;
    private TextView mTextView_questionMajor;
    private ImageButton mImageButton_goto;
    private EditText mEditText_questionContent;
    private Button mButton_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_my_question_acitivity);
        bindView();
    }

    private void bindView() {
        mEditText_questionTitle = findViewById(R.id.editText_questionTitle);
        mRadioGroup_questionType = findViewById(R.id.radioGroup_questionType);
        mRadioButton_science = findViewById(R.id.radioButton_science);
        mRadioButton_speciality = findViewById(R.id.radioButton_speciality);
        mTextView_questionMajor = findViewById(R.id.textView_questionMajor);
        mImageButton_goto = findViewById(R.id.imageButton_goto);
        mEditText_questionContent = findViewById(R.id.editText_questionContent);
        mButton_save = findViewById(R.id.button_save);
        setView();
    }

    private void setView() {
        Intent intent = getIntent();
        String json = Objects.requireNonNull(intent.getExtras()).getString("json_myQuestion");
        MyQuestion myQuestion = new Gson().fromJson(json, MyQuestion.class);
        //标题
        mEditText_questionTitle.setText(myQuestion.getTitle());
        //类型
        if ("学术".equals(myQuestion.getType())) {
            mRadioButton_science.setChecked(true);
        } else {
            mRadioButton_speciality.setChecked(false);
        }
        //专业
        mTextView_questionMajor.setText(String.format("%s>>%s>>%s", myQuestion.getCategory(), myQuestion.getSubject(), myQuestion.getMajor()));
        //跳转
        mImageButton_goto.setOnClickListener(this);
        //内容
        mEditText_questionContent.setSingleLine(false);//禁止单行输入
        mEditText_questionContent.setHorizontallyScrolling(false);//进制水平滚动
        mEditText_questionContent.setText(myQuestion.getContent());
        //保存
        mButton_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}