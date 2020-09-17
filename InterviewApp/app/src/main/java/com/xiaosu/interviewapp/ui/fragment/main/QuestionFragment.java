package com.xiaosu.interviewapp.ui.fragment.main;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.base.BaseFragment;
import com.xiaosu.interviewapp.dao.QCollectionDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.QCollection;
import com.xiaosu.interviewapp.ui.activity.question.createmq.CreateMqActivity;
import com.xiaosu.interviewapp.ui.activity.question.myquestion.MyQuestionActivity;
import com.xiaosu.interviewapp.ui.activity.question.practice.QuestionSelectedActivity;
import com.xiaosu.interviewapp.ui.adapter.listview.CollectionListViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class QuestionFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "CustomizeFragment";
    private QCollectionDao mCollectionDao;

    private Dialog mDialog;

    //private TitleView mTitleView;
    private TextView tv_myQuestions;//我的问题
    private TextView tv_mySpread;//我的推广

    private TextView tv_questionPractice;//题库练习

    private ListView lv_collections;//收藏夹列表
    private List<QCollection> mList = new ArrayList<>();
    private CollectionListViewAdapter mCollectionListViewAdapter;

    private FloatingActionMenu mMenu;
    private FloatingActionButton fab_spread;
    private FloatingActionButton fab_my;
    private FloatingActionButton fab_collection;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initView(View view) {
        // mTitleView = view.findViewById(R.id.titleView);

        tv_myQuestions = view.findViewById(R.id.tv_myQuestions);
        tv_myQuestions.setOnClickListener(this);

        tv_mySpread = view.findViewById(R.id.tv_mySpread);
        tv_mySpread.setOnClickListener(this);

        tv_questionPractice = view.findViewById(R.id.tv_questionPractice);
        tv_questionPractice.setOnClickListener(this);

        lv_collections = view.findViewById(R.id.lv_collections);

        mList.clear();
        //添加收藏夹数据
        initCollectionDate();
        mCollectionListViewAdapter = new CollectionListViewAdapter(getContext());//准备适配器
        mCollectionListViewAdapter.setList(mList);//将数据添加到适配器
        lv_collections.setAdapter(mCollectionListViewAdapter);//将适配器添加到控件

        mMenu = view.findViewById(R.id.fam);

        fab_my = view.findViewById(R.id.fab_my);
        fab_my.setOnClickListener(this);

        fab_spread = view.findViewById(R.id.fab_spread);
        fab_spread.setOnClickListener(this);

        fab_collection = view.findViewById(R.id.fab_collection);
        fab_collection.setOnClickListener(this);
    }

   /*
    * date: 2020/7/30
    * author: xiao su
    * method：
    * desc:加载收藏夹数据
    */
    public void initCollectionDate() {
        CreateDB createDB = new CreateDB(getContext(), "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        mCollectionDao = new QCollectionDao(myDataBaseHelper);
        String json_collections = mCollectionDao.query();
        mList.clear();//清空list
        mList = new Gson().fromJson(json_collections, new TypeToken<List<QCollection>>() {
        }.getType());
        mCollectionListViewAdapter = new CollectionListViewAdapter(getContext());
        mCollectionListViewAdapter.setList(mList);//设置数据
        lv_collections.setAdapter(mCollectionListViewAdapter);//添加适配器
        lv_collections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "功能暂未开通", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_myQuestions:
                Intent intent = new Intent(getContext(), MyQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mySpread:
                Toast.makeText(getContext(), "功能暂未开通", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_questionPractice://题库练习
                intent = new Intent(getContext(), QuestionSelectedActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_spread:
                Toast.makeText(getContext(), "新建推广功能暂未开通", Toast.LENGTH_SHORT).show();
                mMenu.close(true);
                break;
            case R.id.fab_my://新建我的问题
                mMenu.close(true);
                Bundle bundle = new Bundle();
                bundle.putString("TAG", TAG);
                intent = new Intent(getContext(), CreateMqActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                mMenu.close(true);
                break;
            case R.id.fab_collection://新建收藏集合
                showDialog();
                mMenu.close(true);
                break;
            default:
                break;
        }
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 显示对话框
     */
    public void showDialog() {
        //1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
        mDialog = new Dialog(Objects.requireNonNull(getContext()));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        //2.填充布局
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_question_create_collection, null);
        mDialog.setContentView(dialogView);//将自定义布局设置进去
        //3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = mDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        mDialog.show();
        window.setAttributes(lp);
        //设置点击其它地方不让消失弹窗
        mDialog.setCancelable(false);
        //设置对话框内部按钮监听
        setDialogListener(dialogView);


    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 设置对话框内部按钮监听
     */
    private void setDialogListener(View view) {
        final EditText et_collectionName = view.findViewById(R.id.et_collectionName);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(et_collectionName.getText().toString())){
                    Toast.makeText(getContext(), "收藏夹"+et_collectionName.getText().toString()+"创建成功", Toast.LENGTH_SHORT).show();
                    insertQCollection(et_collectionName.getText().toString());//插入新数据到数据库
                    initCollectionDate();//重新加载收藏夹数据
                    mDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "请为收藏夹命名", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 新建收藏夹，插入数据到数据库
     */
    private void insertQCollection(String collectionName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String _id = simpleDateFormat.format(new Date());
        ContentValues values = new ContentValues();
        values.put("_id", _id);
        values.put("name", collectionName);
        values.put("total",0);
        values.put("img", "");
        try {
            //创建数据库，存在则直接打开
            CreateDB createDB = new CreateDB(getContext(), "Interview.db");//指定数据库
            MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();//获取数据库助手
            mCollectionDao = new QCollectionDao(myDataBaseHelper);//数据库操作对象
            mCollectionDao.insert(values);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
