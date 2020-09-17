package com.xiaosu.interviewapp.ui.activity.question.practice;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.QCollectionDao;
import com.xiaosu.interviewapp.ui.adapter.listview.QuestionSelectedGroupListViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionSelectedActivity extends AppCompatActivity{

    private QCollectionDao mCollectionDao;

    private List<String> mList = new ArrayList<>();

    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selected);
        bindViews();
    }

    private void initListViewData(ListView listView) {
        String[] data = new String[]{
                "1","2","3"
        };
        Collections.addAll(mList,data);
        QuestionSelectedGroupListViewAdapter adapter = new QuestionSelectedGroupListViewAdapter(this);
        adapter.setList(mList);
        listView.setAdapter(adapter);
    }

    private void bindViews() {
        mListView = findViewById(R.id.listView_questionSelected);
        initListViewData(mListView);
    }

}