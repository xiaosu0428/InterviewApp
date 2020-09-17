package com.xiaosu.interviewapp.ui.activity.question.createmq;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.MyQuestionDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.ui.customview.TitleView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 自定义》创建》创建我的问题
 */
public class CreateMqActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, TitleView.OnClickTitleListener {

    private static final String TAG = "CustomCreateMqActivity";

    private static int MQ_TYPE = 1;//问题类型码,1是学硕2是专硕

    private TitleView title;//页面标题栏

    private EditText et_mqTitle;//问题标题
    private RadioGroup rg_mqType;//问题分类
    private RadioButton rb_science;
    private RadioButton rb_speciality;
    private TextView tv_mqMajor;//问题专业
    private ConstraintLayout cl_mqMajor;//问题选专业按钮
    private ImageButton ibtn_goto;//问题选专业按钮
    private EditText et_mqContent;//问题内容
    private Button btn_save;

    private String rb_text = "学术";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mq);
        bindViews();
    }

    private void bindViews() {
        title = findViewById(R.id.titleView);
        et_mqTitle = findViewById(R.id.et_mqTitle);
        rg_mqType = findViewById(R.id.rg_type);
        rb_science = findViewById(R.id.rb_science);
        rb_speciality = findViewById(R.id.rb_speciality);
        cl_mqMajor = findViewById(R.id.cl_mqMajor);
        ibtn_goto = findViewById(R.id.ib_goto);
        tv_mqMajor = findViewById(R.id.tv_mqMajor);
        et_mqContent = findViewById(R.id.et_mqContent);
        //页面标题
        title.setOnClickTitleListener(this);//返回监听
        //问题标题
        Typeface typeface_wr_vista_ht = Typeface.createFromAsset(getAssets(), "font/wr_vista_ht.ttf");//微软黑体
        et_mqTitle.setTypeface(typeface_wr_vista_ht);
        //问题分类
        rg_mqType.setOnCheckedChangeListener(this);
        //问题专业
        cl_mqMajor.setOnClickListener(this);
        ibtn_goto.setOnClickListener(this);
        Typeface typeface_st = Typeface.createFromAsset(getAssets(), "font/st.ttf");//宋体
        tv_mqMajor.setTypeface(typeface_st);

        Bundle bundle = getBundle();
        Log.d(TAG, "bindViews: " + bundle.getString("TAG"));
        if (!"CustomizeFragment".equals(bundle.getString("TAG"))) {
            SharedPreferences sharedPreferences = getSharedPreferences("Temp", MODE_PRIVATE);
            String title = sharedPreferences.getString("mqTitle", "");
            String type = sharedPreferences.getString("mqType", "");
            String category = sharedPreferences.getString("mqCategory", "");
            String subject = sharedPreferences.getString("mqSubject", "");
            String major = sharedPreferences.getString("mqMajor", "");
            String content = sharedPreferences.getString("mqContent", "");
            if (!"".equals(title)) {
                et_mqTitle.setText(title);
            }
            if ("专业".equals(type)) {
                rb_speciality.setChecked(true);
            } else {
                rb_science.setChecked(true);
            }
            if (!"".equals(category) && !"".equals(subject) && !"".equals(major)) {
                tv_mqMajor.setText((category + "》" + subject + "》" + major));
            }
            if (!"".equals(content)) {
                et_mqContent.setText(content);
            }
        }
        //内容内容：设置编辑框多行输入
        et_mqContent.setTypeface(typeface_st);//宋体
        et_mqContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);//设置输入类型
        et_mqContent.setSingleLine(false);//禁止单行输入
        et_mqContent.setHorizontallyScrolling(false);//进制水平滚动
        //保存
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
    }

    private Bundle getBundle() {
        Intent intent = getIntent();
        return intent.getBundleExtra("bundle");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_mqMajor:
            case R.id.ib_goto:
                //保存已编辑的内容
                SharedPreferences sharedPreferences = getSharedPreferences("Temp", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String title = et_mqTitle.getText().toString();
                String type = rb_text;
                String category = sharedPreferences.getString("mqCategory", "");
                String subject = sharedPreferences.getString("mqSubject", "");
                String major = sharedPreferences.getString("mqMajor", "");
                String content = et_mqContent.getText().toString();
                editor.putString("mqTitle", title);
                if (!"".equals(type)) {
                    editor.putString("mqType", rb_text);
                }
                if (!"".equals(tv_mqMajor.getText().toString())) {//未选专业
                    editor.putString("mqCategory", category);
                    editor.putString("mqSubject", subject);
                    editor.putString("mqMajor", major);
                }
                editor.putString("mqContent", content);
                editor.apply();
                //跳转到选专业
                Intent intent = new Intent(CreateMqActivity.this, MajorChooseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("MQ_TYPE", MQ_TYPE);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_save:
                //保存
                saveMyQuestion();
                break;
            default:
                break;
        }
    }

    private void saveMyQuestion() {
        CreateDB createDB = new CreateDB(this, "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        MyQuestionDao myQuestionDao = new MyQuestionDao(myDataBaseHelper);
        if ("".equals(et_mqTitle.getText().toString())) {//标题空
            Toast.makeText(CreateMqActivity.this, "请填写题目", Toast.LENGTH_SHORT).show();
        } else if ("".equals(tv_mqMajor.getText().toString())) {//专业空
            Toast.makeText(CreateMqActivity.this, "请选择专业", Toast.LENGTH_SHORT).show();
        } else if ("".equals(et_mqContent.getText().toString())) {//内容空
            Toast.makeText(CreateMqActivity.this, "请编辑内容", Toast.LENGTH_SHORT).show();
        } else {//都不空，生成对象并插入
            Log.d(TAG, "saveMyQuestion: =====>");
            Log.d(TAG, "标题: =====》" + et_mqTitle.getText().toString());
            Log.d(TAG, "专业: =====》" + tv_mqMajor.getText().toString());
            Log.d(TAG, "内容: =====》" + et_mqContent.getText().toString());
            SharedPreferences sharedPreferences = getSharedPreferences("Temp", MODE_PRIVATE);

            SimpleDateFormat idSDF = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            SimpleDateFormat timeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            Date date = new Date();
            String _id = idSDF.format(date);
            String title = et_mqTitle.getText().toString();
            String type = rb_text;
            String category = sharedPreferences.getString("mqCategory", "");
            String subject = sharedPreferences.getString("mqSubject", "");
            String major = sharedPreferences.getString("mqMajor", "");
            String content = et_mqContent.getText().toString();
            String time = timeSDF.format(date);
            ContentValues values = new ContentValues();
            values.put("_id", _id);
            values.put("title", title);
            values.put("type", type);
            values.put("category", category);
            values.put("subject", subject);
            values.put("major", major);
            values.put("content", content);
            values.put("time", time);
            try {
                myQuestionDao.insert(values);
                Log.d(TAG, "saveMyQuestion: ======>保存成功");
                clearSharedPreferences();//清空临时文件内容
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void clearSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Temp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mqTitle", "");
        editor.putString("mqType", "");
        editor.putString("mqCategory", "");
        editor.putString("mqSubject", "");
        editor.putString("mqMajor", "");
        editor.putString("mqContent", "");
        editor.apply();
        Log.d(TAG, "clearSharedPreferences: =====>临时文件已清空");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_science:
                MQ_TYPE = 1;
                rb_text = rb_science.getText().toString();
                Log.d(TAG, "onCheckedChanged: "+ rb_text);
                break;
            case R.id.rb_speciality:
                MQ_TYPE = 2;
                rb_text = rb_speciality.getText().toString();
                Log.d(TAG, "onCheckedChanged: "+ rb_text);
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            clearSharedPreferences();
            finish();
        }
        return true;
    }

    @Override
    public void onClickTitle() {
        clearSharedPreferences();//清空文件
        finish();
    }
}
