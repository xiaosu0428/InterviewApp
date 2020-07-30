package com.xiaosu.interviewapp.ui.adapter.question;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xiaosu.interviewapp.ui.fragment.question.choosemajor.ScienceFragment;

import java.util.ArrayList;
import java.util.List;

/*
 * Date:
 * Author: XiaoSu
 * Desc: 创建我的问题时，选择专业时学术专业适配器
 */
public class MajorChooseViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> categories = new ArrayList<>();


    public MajorChooseViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
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
        Fragment fragment = new ScienceFragment("哲学");
        switch (position) {
            case 0:
                fragment = new ScienceFragment("哲学");
                break;
            case 1:
                fragment = new ScienceFragment("经济学");
                break;
            case 2:
                fragment = new ScienceFragment("法学");
                break;
            case 3:
                fragment = new ScienceFragment("教育学");
                break;
            case 4:
                fragment = new ScienceFragment("历史学");
                break;
            case 5:
                fragment = new ScienceFragment("文学");
                break;
            case 6:
                fragment = new ScienceFragment("理学");
                break;
            case 7:
                fragment = new ScienceFragment("工学");
                break;
            case 8:
                fragment = new ScienceFragment("农学");
                break;
            case 9:
                fragment = new ScienceFragment("医学");
                break;
            case 10:
                fragment = new ScienceFragment("管理学");
                break;
            case 11:
                fragment = new ScienceFragment("军事学");
                break;
            case 12:
                fragment = new ScienceFragment("艺术学");
                break;
            default:
                break;
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

    public void setCategories(List<String> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
