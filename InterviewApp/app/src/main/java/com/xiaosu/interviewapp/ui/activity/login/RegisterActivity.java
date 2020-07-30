package com.xiaosu.interviewapp.ui.activity.login;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.UserDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    private UserDao mUserDao;

    private EditText et_account;
    private EditText et_psw;
    private EditText et_pswConfirm;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        //创建数据库，存在则直接打开
        CreateDB createDB = new CreateDB(this,"Interview.db");//指定数据库
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();//获取数据库助手
        mUserDao = new UserDao(myDataBaseHelper);//数据库操作对象
        bindViews();
    }

    private void bindViews() {
        et_account = findViewById(R.id.et_account);
        et_psw = findViewById(R.id.et_psw);
        et_pswConfirm = findViewById(R.id.et_pswConfirm);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {//注册
            registerUser();
        }
    }

    private void registerUser() {
        if ("".equals(et_account.getText().toString())) {//账号空
            Toast.makeText(RegisterActivity.this, "请输入注册账号", Toast.LENGTH_SHORT).show();
        } else if (et_account.getText().toString().length() != 11) {//账号不符合
            Toast.makeText(RegisterActivity.this, "请输入手机号码格式", Toast.LENGTH_SHORT).show();
        } else if ("".equals(et_psw.getText().toString())) {//密码空
            Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else if (et_psw.getText().toString().length() < 6) {//密码长度<6
            Toast.makeText(RegisterActivity.this, "密码最少6位", Toast.LENGTH_SHORT).show();
        } else if ("".equals(et_pswConfirm.getText().toString())) {//密码确认空
            Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
        } else if (!(et_psw.getText().toString()).equals(et_pswConfirm.getText().toString())) {//两次密码不一致
            Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            //判断是否已注册
            if(isRegister()){
                Toast.makeText(RegisterActivity.this, "账号已注册", Toast.LENGTH_SHORT).show();
            }else {//未注册账号注册
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
                String user_id = simpleDateFormat.format(new Date());
                String user_account = et_account.getText().toString();
                String user_psw = et_psw.getText().toString();
                ContentValues values = new ContentValues();
                values.put("_id", user_id);
                values.put("user_account", user_account);
                values.put("user_psw", user_psw);
                values.put("user_petName", "");
                values.put("user_name", "");
                values.put("user_sex", "");
                values.put("user_age", "");
                values.put("user_mail", "");
                values.put("user_motto", "");
                try {
                    mUserDao.insert(values);
                    Log.d(TAG, "onClick: 插入成功");
                    Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    Log.d(TAG, "onClick: 插入异常 ===>");
                    e.printStackTrace();
                }
            }

        }
    }

    /*
     * desc: 判断是否已注册
     * params:
     * methodName:
     * date: 2020/6/5
     */
    private boolean isRegister() {
        String user_json = mUserDao.select(et_account.getText().toString());
        Log.d(TAG, "isRegister: "+ user_json);
        if(!"".equals(user_json)){
            User user = new Gson().fromJson(user_json,User.class);
            return et_account.getText().toString().equals(user.getUser_account());
        }
        return false;
    }
}
