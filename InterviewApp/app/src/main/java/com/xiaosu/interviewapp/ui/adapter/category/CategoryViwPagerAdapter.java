package com.xiaosu.interviewapp.ui.adapter.category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xiaosu.interviewapp.ui.fragment.category.CategoryScienceFragment;
import com.xiaosu.interviewapp.ui.fragment.category.CategorySpecialityFragment;

import java.util.ArrayList;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 分类：ViewPager适配器
 */
public class CategoryViwPagerAdapter extends FragmentPagerAdapter {


    private List<String> categories= new ArrayList<>();


    public CategoryViwPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new CategoryScienceFragment();
        if(position == 1 ){
            fragment = new CategorySpecialityFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    /*
     * desc: 设置顶部分类
     * params:
     * methodName:
     * date: 2020/6/6
     */
    public void setCategories() {
        categories.clear();
        categories.add("学术门类");
        categories.add("专业领域");
        notifyDataSetChanged();
    }
}
