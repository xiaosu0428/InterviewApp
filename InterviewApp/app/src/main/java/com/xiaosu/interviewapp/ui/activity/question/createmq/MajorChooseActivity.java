package com.xiaosu.interviewapp.ui.activity.question.createmq;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.ui.adapter.question.MajorChooseViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc:自定义》创建》创建我的问题》选择专业
 */
public class MajorChooseActivity extends AppCompatActivity {
    private static final String TAG = "CustomMajorChooseActivity";

    private MajorChooseViewPagerAdapter mMajorChooseViewPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> categories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_choose);
        bindView();
    }

    private Bundle getBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        assert bundle != null;
        return bundle;
    }

    private void bindView() {
        Bundle bundle = getBundle();
        if(1 == bundle.getInt("MQ_TYPE")){//加载学硕门类
            categories.clear();
            Resources resources = getResources();
            String[] science_major = resources.getStringArray(R.array.category_science);
            categories.addAll(Arrays.asList(science_major));
        }else if(2 == bundle.getInt("MQ_TYPE")){//加载专硕领域
            categories.clear();
            Resources resources = getResources();
            String[] science_major = resources.getStringArray(R.array.category_speciality);
            categories.addAll(Arrays.asList(science_major));
        }
        mViewPager = findViewById(R.id.vp_major);
        mTabLayout = findViewById(R.id.tl_major);
        mTabLayout.setupWithViewPager(mViewPager);
        mMajorChooseViewPagerAdapter = new MajorChooseViewPagerAdapter(getSupportFragmentManager(), getApplication());
        mMajorChooseViewPagerAdapter.setCategories(categories);//设置顶部分类
        mViewPager.setAdapter(mMajorChooseViewPagerAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //安卓重写返回键事件
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Bundle bundle = new Bundle();
            bundle.putString("TAG",TAG);
            Intent intent = new Intent(this, CreateMqActivity.class);
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            finish();
        }
        return true;
    }

}
