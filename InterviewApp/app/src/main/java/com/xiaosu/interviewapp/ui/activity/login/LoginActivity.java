package com.xiaosu.interviewapp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.ui.activity.main.IndexActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    private TextView tv_verificationCode;
    private EditText et_account;
    private EditText et_psw;
    private Button btn_login;
    private TextView tv_forgotPsw;

    private ImageView iv_wechat;
    private ImageView iv_qq;
    private ImageView iv_weibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        bindViews();

    }

    private void bindViews() {
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);

        tv_verificationCode = findViewById(R.id.tv_verificationCode);
        tv_verificationCode.setOnClickListener(this);

        et_account = findViewById(R.id.et_account);
        et_psw = findViewById(R.id.et_psw);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        tv_forgotPsw = findViewById(R.id.tv_forgotPsw);
        tv_forgotPsw.setOnClickListener(this);

        iv_wechat = findViewById(R.id.iv_wechat);
        iv_wechat.setOnClickListener(this);
        iv_qq = findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_weibo = findViewById(R.id.iv_weibo);
        iv_weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_verificationCode:

                break;
            case R.id.btn_login:
                login();//登录
                break;
            case R.id.tv_forgotPsw:

                break;
            case R.id.iv_wechat:
            case R.id.iv_qq:
            case R.id.iv_weibo:
                Toast.makeText(LoginActivity.this, "功能暂未开通", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /*
     * desc: 用户登录
     * params:
     * methodName:
     * date: 2020/6/5
     */
    private void login() {
        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
        startActivity(intent);
        finish();
    }
}
