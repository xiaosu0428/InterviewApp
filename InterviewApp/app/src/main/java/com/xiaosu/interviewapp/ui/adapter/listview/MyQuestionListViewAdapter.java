package com.xiaosu.interviewapp.ui.adapter.listview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.MyQuestionDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.MyQuestion;
import com.xiaosu.interviewapp.ui.activity.question.myquestion.MqDetailsActivity;
import com.xiaosu.interviewapp.ui.activity.question.myquestion.MyQuestionActivity;
import com.xiaosu.interviewapp.ui.activity.question.myquestion.WriteMyQuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class MyQuestionListViewAdapter extends BaseAdapter {
    private static final String TAG = "MyQuestionListViewAdapt";

    private List<MyQuestion> mList = new ArrayList<>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyQuestionListViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void setList(List<MyQuestion> list) {
        mList.clear();
        mList.addAll(list);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_listview_question, null);

        ConstraintLayout constraintLayout = view.findViewById(R.id.cl_questionDetails);
        TextView tv_mqTitle = view.findViewById(R.id.tv_mqTitle);
        TextView tv_mqType = view.findViewById(R.id.tv_type);
        TextView tv_mqCategory = view.findViewById(R.id.tv_mqCategory);
        TextView tv_mqSubject = view.findViewById(R.id.tv_mqSubject);
        TextView tv_mqMajor = view.findViewById(R.id.tv_mqMajor);
        TextView tv_mqTime = view.findViewById(R.id.tv_mqTime);
        TextView tv_mqContent = view.findViewById(R.id.tv_mqContent);
        ImageButton btn_menu = view.findViewById(R.id.btn_menu);

        //详情页跳转
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Log.d(TAG, "onClick: "+ new Gson().toJson(mList.get(position)));
                bundle.putString("questionDetails", new Gson().toJson(mList.get(position)));
                Intent intent = new Intent(mContext, MqDetailsActivity.class);
                intent.putExtra("bundle",bundle);
                mContext.startActivity(intent);
            }
        });
        //设置标题:微软正黑体
        Typeface typeface_wr_zht = Typeface.createFromAsset(mContext.getAssets(), "font/wr_zht.ttf");
        tv_mqTitle.setTypeface(typeface_wr_zht);//字体
        tv_mqTitle.setText(mList.get(position).getTitle());//标题内容
        //设置专业
        Typeface typeface_mzd_csh = Typeface.createFromAsset(mContext.getAssets(), "font/mzd_csh.ttf");//毛泽东草书
        tv_mqType.setTypeface(typeface_mzd_csh);//字体
        if ("专业".equals(mList.get(position).getType())) {//图标
            tv_mqType.setText("专");
            tv_mqType.setBackgroundResource(R.drawable.shape_circle_tv_green);
        }
        tv_mqCategory.setText(mList.get(position).getCategory());//分类
        tv_mqSubject.setText(mList.get(position).getSubject());//学科
        tv_mqMajor.setText(mList.get(position).getMajor());//专业
        //设置时间
        tv_mqTime.setText(mList.get(position).getTime());
        //设置内容
        Typeface typeface_st = Typeface.createFromAsset(mContext.getAssets(), "font/st.ttf");
        tv_mqContent.setTypeface(typeface_st);//字体
        tv_mqContent.setText(mList.get(position).getContent());//内容
        //设置操作菜单
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建弹出式菜单对象
                PopupMenu popup = new PopupMenu(mContext, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.question_myquestion_item, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.edit){
                            //Toast.makeText(mContext,"编辑功能暂未开通",Toast.LENGTH_SHORT).show();
                            writeMyQuestion(position);
                        }else {
                            //弹出提示对话框
                            showWarnDialog(position);
                        }
                        return true;
                    }
                });
                //显示(这一行代码不要忘记了)
                popup.show();
            }
        });
        return view;
    }

    private void writeMyQuestion(int position) {
        MyQuestion myQuestion = (MyQuestion) getItem(position);
        String json_myQuestion = new Gson().toJson(myQuestion);
        Intent intent = new Intent(mContext, WriteMyQuestionActivity.class);
        intent.putExtra("json_myQuestion",json_myQuestion);
        mContext.startActivity(intent);
        if(mContext instanceof MyQuestionActivity){
            MyQuestionActivity activity = (MyQuestionActivity) mContext;
            activity.finish();
        }
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 题目删除提醒
     */
    private void showWarnDialog(int position) {
//        if(){
//
//        }else {
//
//        }
        deleteMyQuestion(position);
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 删除题目
     */
    private void deleteMyQuestion(int position) {
        CreateDB createDB = new CreateDB(mContext,"Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        MyQuestionDao myQuestionDao = new MyQuestionDao(myDataBaseHelper);
        MyQuestion myQuestion = (MyQuestion) getItem(position);
        myQuestionDao.delete(myQuestion.get_id());
        mList.remove(position);
        notifyDataSetChanged();
    }

}
