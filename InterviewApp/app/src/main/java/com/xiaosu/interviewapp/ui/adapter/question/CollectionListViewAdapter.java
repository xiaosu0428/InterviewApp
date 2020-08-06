package com.xiaosu.interviewapp.ui.adapter.question;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.xiaosu.interviewapp.utils.CreateDialogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Time: 2020/7/17
 * Author: Xiaosu
 * Description:
 */
public class CollectionListViewAdapter extends BaseAdapter{
    private Dialog mainOperationDialog;
    private Dialog renameDialog;
    private Dialog cleanDialog;
    private Dialog deleteDialog;


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
                initMainOperationDialogView(position);
                return true;
            }
        });
    }

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 长按收藏夹弹出操作对话框
     */
    public void initMainOperationDialogView(int position) {
        //1. 准备Dialog
        mainOperationDialog = new Dialog(mContext);
        //2. 初始化布局
        CreateDialogUtil createDialogUtil = new CreateDialogUtil(mContext,mainOperationDialog,R.layout.dialog_question_operation_collection,true);
        //3. 设置监听
        setMainOperationDialogListener(createDialogUtil.getDialogView(),position);
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 设置OperationDialog监听
     */
    private void setMainOperationDialogListener(View dialogView,final int position) {
        TextView tv_collectionName = dialogView.findViewById(R.id.tv_collectionName);
        LinearLayout ll_rename = dialogView.findViewById(R.id.ll_rename);
        LinearLayout ll_empty = dialogView.findViewById(R.id.ll_empty);
        LinearLayout ll_delete = dialogView.findViewById(R.id.ll_delete);

        tv_collectionName.setText(mList.get(position).getTitle());
        ll_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRenameDialogView();//重命名
                mainOperationDialog.dismiss();
            }
        });
        ll_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCleanDialogView(position);//清空
                mainOperationDialog.dismiss();
            }
        });
        ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDeleteDialogView(position);//删除
                mainOperationDialog.dismiss();
            }
        });
    }


    /**********************************************************************************************/

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 清空收藏夹内容对话框
     */
    private void initCleanDialogView(int position) {
        cleanDialog = new Dialog(Objects.requireNonNull(mContext));
        CreateDialogUtil createDialogUtil = new CreateDialogUtil(mContext,cleanDialog,R.layout.dialog_question_clean_collection,false);
        setCleanDialogListener(createDialogUtil.getDialogView(),position);//设置监听

    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 设置清空对话框监听
     */
    private void setCleanDialogListener(View dialogView,final int position) {
        TextView tv_confirmDelete = dialogView.findViewById(R.id.delete_confirm);
        TextView tv_cancelDelete = dialogView.findViewById(R.id.delete_cancel);
        tv_confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空
                cleanDialog.dismiss();
                Toast.makeText(mContext, "清空成功", Toast.LENGTH_SHORT).show();
            }
        });
        tv_cancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanDialog.dismiss();
            }
        });
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 清空对话框
     */
    private void cleanCollection(int position) {


    }

    /**********************************************************************************************/

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 重命名对话框
     */
    private void initRenameDialogView() {
        renameDialog = new Dialog(Objects.requireNonNull(mContext));
        CreateDialogUtil createDialogUtil = new CreateDialogUtil(mContext,renameDialog,R.layout.dialog_question_rename_collection,false);
        setRenameDialogListener(createDialogUtil.getDialogView());//设置对话框内部按钮监听
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 设置重命名对话框监听
     */
    private void setRenameDialogListener(View view) {
        final EditText et_collectionName = view.findViewById(R.id.et_collectionName);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(et_collectionName.getText().toString())) {
                    //重命名收藏夹
                    renameCollection();
                    //关闭对话框
                    renameDialog.dismiss();
                } else {
                    Toast.makeText(mContext, "请为收藏夹命名", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renameDialog.dismiss();
            }
        });
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 重命名收藏夹
     */
    private void renameCollection() {

    }

    /**********************************************************************************************/

    /*
     * date: 2020/7/30
     * author: xiao su
     * method：
     * desc: 删除提醒对话框
     */
    private void initDeleteDialogView(int position) {
        deleteDialog = new Dialog(Objects.requireNonNull(mContext));
        CreateDialogUtil createDialogUtil = new CreateDialogUtil(mContext,deleteDialog,R.layout.dialog_question_delete_collection,false);
        setDeleteWarnDialogListener(createDialogUtil.getDialogView(), position);
    }

    /*
     * date: 2020/8/6
     * author: xiao su
     * method：
     * desc: 设置删除对话框监听
     */
    private void setDeleteWarnDialogListener(View dialogView, final int position) {
        TextView tv_confirmDelete = dialogView.findViewById(R.id.delete_confirm);
        TextView tv_cancelDelete = dialogView.findViewById(R.id.delete_cancel);
        tv_confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCollection(position);
                deleteDialog.dismiss();
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        tv_cancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
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
