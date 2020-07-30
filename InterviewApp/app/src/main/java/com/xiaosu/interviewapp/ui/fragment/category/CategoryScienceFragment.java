package com.xiaosu.interviewapp.ui.fragment.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.base.BaseFragment;
import com.xiaosu.interviewapp.ui.activity.category.science.CategoryXMajorActivity;

public class CategoryScienceFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_philosophy;
    private TextView tv_economics;
    private TextView tv_law;
    private TextView tv_education;
    private TextView tv_history;
    private TextView tv_literature;
    private TextView tv_lixue;
    private TextView tv_engineering;
    private TextView tv_agronomy;
    private TextView tv_medical;
    private TextView tv_management;
    private TextView tv_military;
    private TextView tv_art;


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_category_science;
    }

    @Override
    protected void initView(View view) {
        bindViews(view);
    }

    private void bindViews(View view) {
        //哲学
        tv_philosophy = view.findViewById(R.id.tv_philosophy);
        tv_philosophy.setOnClickListener(this);
        //经济学
        tv_economics = view.findViewById(R.id.tv_economics);
        tv_economics.setOnClickListener(this);

        tv_law = view.findViewById(R.id.tv_law);
        tv_law.setOnClickListener(this);

        tv_education = view.findViewById(R.id.tv_education);
        tv_education.setOnClickListener(this);

        tv_history = view.findViewById(R.id.tv_history);
        tv_history.setOnClickListener(this);

        tv_literature = view.findViewById(R.id.tv_literature);
        tv_literature.setOnClickListener(this);

        tv_lixue = view.findViewById(R.id.tv_lixue);
        tv_lixue.setOnClickListener(this);

        tv_engineering = view.findViewById(R.id.tv_engineering);
        tv_engineering.setOnClickListener(this);

        tv_agronomy = view.findViewById(R.id.tv_agronomy);
        tv_agronomy.setOnClickListener(this);

        tv_medical = view.findViewById(R.id.tv_medical);
        tv_medical.setOnClickListener(this);

        tv_management = view.findViewById(R.id.tv_management);
        tv_management.setOnClickListener(this);

        tv_military = view.findViewById(R.id.tv_military);
        tv_military.setOnClickListener(this);

        tv_art = view.findViewById(R.id.tv_art);
        tv_art.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_philosophy:
                Bundle bundle = new Bundle();
                bundle.putString("category", "哲学");
                Intent intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_economics:
                bundle = new Bundle();
                bundle.putString("category", "经济学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_law:
                bundle = new Bundle();
                bundle.putString("category", "法学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_education:
                bundle = new Bundle();
                bundle.putString("category", "教育学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_history:
                bundle = new Bundle();
                bundle.putString("category", "历史学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_literature:
                bundle = new Bundle();
                bundle.putString("category", "文学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_lixue:
                bundle = new Bundle();
                bundle.putString("category", "理学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_engineering:
                bundle = new Bundle();
                bundle.putString("category", "工学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_agronomy:
                bundle = new Bundle();
                bundle.putString("category", "农学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_medical:
                bundle = new Bundle();
                bundle.putString("category", "医学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_management:
                bundle = new Bundle();
                bundle.putString("category", "管理学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_military:
                bundle = new Bundle();
                bundle.putString("category", "军事学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.tv_art:
                bundle = new Bundle();
                bundle.putString("category", "艺术学");
                intent = new Intent(getContext(), CategoryXMajorActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
