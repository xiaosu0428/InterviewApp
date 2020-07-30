package com.xiaosu.interviewapp.ui.fragment.main;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.base.BaseFragment;
import com.xiaosu.interviewapp.ui.adapter.category.CategoryViwPagerAdapter;

public class CategoryFragment extends BaseFragment {
    private CategoryViwPagerAdapter mCategoryViwPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView(View view) {
        bindView(view);
    }

    private void bindView(View view) {
        mViewPager = view.findViewById(R.id.vp_more);
        mTabLayout = view.findViewById(R.id.tl_more);
        mTabLayout.setupWithViewPager(mViewPager);
        mCategoryViwPagerAdapter = new CategoryViwPagerAdapter(getChildFragmentManager());
        mCategoryViwPagerAdapter.setCategories();//设置顶部分类
        mViewPager.setAdapter(mCategoryViwPagerAdapter);
    }


}
