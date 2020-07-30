package com.xiaosu.interviewapp.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.base.BaseFragment;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.ui.fragment.main.QuestionFragment;
import com.xiaosu.interviewapp.ui.fragment.main.HomeFragment;
import com.xiaosu.interviewapp.ui.fragment.main.CategoryFragment;
import com.xiaosu.interviewapp.ui.fragment.main.MyFragment;

public class IndexActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "IndexActivity";

    private BottomNavigationView mBottomNavigationView;

    private FragmentManager mFragmentManager;

    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private QuestionFragment mQuestionFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        CreateDB createDB = new CreateDB(this,"Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        myDataBaseHelper.getWritableDatabase();
        initFragment();
        setClickListener();
    }

    /*
     * desc:设置监听
     * params:
     * methodName:
     * date: 2020/6/5
     */
    private void setClickListener() {
        mBottomNavigationView = findViewById(R.id.index_navigation_bar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /*
     * desc: 初始化碎片布局
     * params:
     * methodName:
     * date: 2020/6/5
     */
    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mQuestionFragment = new QuestionFragment();
        mMyFragment = new MyFragment();
        mFragmentManager=getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Log.d(TAG, "onNavigationItemSelected: home");
                switchFragment(mHomeFragment);
                break;
            case R.id.more:
                Log.d(TAG, "onNavigationItemSelected: more");
                switchFragment(mCategoryFragment);
                break;
            case R.id.customize:
                Log.d(TAG, "onNavigationItemSelected: customize");
                switchFragment(mQuestionFragment);
                break;
            case R.id.my:
                Log.d(TAG, "onNavigationItemSelected: my");
                switchFragment(mMyFragment);
                break;
        }
        return true;
    }

    private void switchFragment(BaseFragment targetFragment) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fl_index_container,targetFragment);
        transaction.commit();
    }
}
