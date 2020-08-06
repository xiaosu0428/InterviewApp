package com.xiaosu.interviewapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/*
 * Time: 2020/8/6
 * Author: Xiaosu
 * Description:
 */
public class CreateDialogUtil {
    private Context mContext;
    private Dialog mDialog;
    private int dialogResource;
    private boolean isCancelable;

    private View dialogView;

    public CreateDialogUtil(Context context, Dialog dialog, int dialogResource, boolean isCancelable) {
        //1.创建一个Dialog对象
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏

        //2.填充布局
        dialogView = LayoutInflater.from(context).inflate(dialogResource, null);
        dialog.setContentView(dialogView);

        //3.设置指定的宽高
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();//注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        window.setAttributes(lp);

        //4.其他设置
        dialog.setCancelable(isCancelable);//点击其他区域是否可取消对话框
    }


    public View getDialogView() {
        return dialogView;
    }

}
