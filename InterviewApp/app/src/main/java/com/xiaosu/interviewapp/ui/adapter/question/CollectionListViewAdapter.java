package com.xiaosu.interviewapp.ui.adapter.question;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.itheima.roundedimageview.RoundedImageView;
import com.xiaosu.interviewapp.R;
import com.xiaosu.interviewapp.dao.QCollectionDao;
import com.xiaosu.interviewapp.db.CreateDB;
import com.xiaosu.interviewapp.db.MyDataBaseHelper;
import com.xiaosu.interviewapp.model.QCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Time: 2020/7/17
 * Author: Xiaosu
 * Description:
 */
public class CollectionListViewAdapter extends BaseAdapter {
    private Dialog mDialog;
    private Dialog deleteWarnDialog;

    private List<QCollection> mList = new ArrayList<>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CollectionListViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<QCollection> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_listview_collection, null);
        initView(view, position);
        return view;
    }


    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: initView
     */
    private void initView(View view, final int position) {
        //find view
        ConstraintLayout cl_collection = view.findViewById(R.id.cl_collection);
        RoundedImageView riv_collectionImage = view.findViewById(R.id.riv_collectionImage);
        TextView tv_collectionName = view.findViewById(R.id.tv_collectionName);
        TextView tv_collectionTotal = view.findViewById(R.id.tv_collectionTotal);
        //set view content
        tv_collectionName.setText(mList.get(position).getTitle());
        tv_collectionTotal.setText(String.valueOf(mList.get(position).getTotal()) + "题");
        //set listener
        cl_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        cl_collection.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(position);
                return true;
            }
        });
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 长按收藏夹弹出对话框
     */
    public void showDialog(int position) {
        //1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
        mDialog = new Dialog(Objects.requireNonNull(mContext));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        //2.填充布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogView = inflater.inflate(R.layout.dialog_question_operation_collection, null);
        initDialogView(dialogView, position);
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
        mDialog.setCancelable(true);
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 初始化对话框布局
     */
    private void initDialogView(View view, final int position) {
        TextView tv_collectionName = view.findViewById(R.id.tv_collectionName);
        LinearLayout ll_rename = view.findViewById(R.id.ll_rename);
        LinearLayout ll_empty = view.findViewById(R.id.ll_empty);
        LinearLayout ll_delete = view.findViewById(R.id.ll_delete);

        tv_collectionName.setText(mList.get(position).getTitle());
        ll_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRenameDialog(position);//重命名
                mDialog.dismiss();
            }
        });
        ll_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空
                Toast.makeText(mContext, "清空", Toast.LENGTH_SHORT).show();
            }
        });
        ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteWarnDialog(position);//删除
                mDialog.dismiss();
            }
        });
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 重命名对话框
     */
    private void showRenameDialog(int position) {
        //1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
        mDialog = new Dialog(Objects.requireNonNull(mContext));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        //2.填充布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogView = inflater.inflate(R.layout.dialog_question_rename_collection, null);
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

    private void setDialogListener(View view) {
        final EditText et_collectionName = view.findViewById(R.id.et_collectionName);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(et_collectionName.getText().toString())) {
                    //重命名收藏夹

                    //关闭对话框
                    mDialog.dismiss();
                } else {
                    Toast.makeText(mContext, "请为收藏夹命名", Toast.LENGTH_SHORT).show();
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
     * desc: 删除提醒对话框
     */
    private void showDeleteWarnDialog(int position) {
        //1.创建一个Dialog对象，如果是AlertDialog对象的话，弹出的自定义布局四周会有一些阴影，效果不好
        deleteWarnDialog = new Dialog(Objects.requireNonNull(mContext));
        deleteWarnDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        //2.填充布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogView = inflater.inflate(R.layout.dialog_warn_delete_collection, null);
        initDeleteWarnDialogView(dialogView, position);
        deleteWarnDialog.setContentView(dialogView);//将自定义布局设置进去
        //3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = deleteWarnDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        deleteWarnDialog.show();
        window.setAttributes(lp);
        //设置点击其它地方不让消失弹窗
        deleteWarnDialog.setCancelable(false);
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 初始化删除提醒对话框布局
     */
    private void initDeleteWarnDialogView(View dialogView, final int position) {
        TextView tv_confirmDelete = dialogView.findViewById(R.id.delete_confirm);
        TextView tv_cancelDelete = dialogView.findViewById(R.id.delete_cancel);
        tv_confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCollection(position);
                deleteWarnDialog.dismiss();
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        tv_cancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWarnDialog.dismiss();
            }
        });
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 删除收藏夹
     */
    private void deleteCollection(int position) {
        CreateDB createDB = new CreateDB(mContext, "Interview.db");
        MyDataBaseHelper myDataBaseHelper = createDB.getMyDataBaseHelper();
        QCollectionDao qCollectionDao = new QCollectionDao(myDataBaseHelper);
        QCollection qCollection = (QCollection) getItem(position);
        qCollectionDao.delete(qCollection.get_id());
        mList.remove(position);
        notifyDataSetChanged();
    }


}
