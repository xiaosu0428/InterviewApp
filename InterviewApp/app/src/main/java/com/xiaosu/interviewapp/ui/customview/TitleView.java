package com.xiaosu.interviewapp.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xiaosu.interviewapp.R;

/*
 * Time: 2020/6/15
 * Author: Xiaosu
 * Description:
 */
public class TitleView extends LinearLayout implements View.OnClickListener {

    /*
     * 属性：
     * 是否有返回
     * 标题文字设置
     * *字体颜色
     * *字体样式
     * *字体大小
     * *背景颜色
     * *分割线高度
     * *分割线颜色
     * */

    //子控件
    private TextView mTitle;

    //属性
    private String mTitleText;
    private boolean mIsBack;

    //接口
    private OnClickTitleListener mOnClickTitleListener;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置属性
        initAttrs(context, attrs);
        //加载子控件
        initView(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        mTitleText = typedArray.getString(R.styleable.TitleView_titleText);
        mIsBack = typedArray.getBoolean(R.styleable.TitleView_isBack, false);
        typedArray.recycle();
    }

    private void initView(Context context) {
        //引入布局
        LayoutInflater.from(context).inflate(R.layout.customview_title, this, true);
        //加载控件
        mTitle = findViewById(R.id.title);
        View line = findViewById(R.id.line);

        mTitle.setOnClickListener(this);
        mTitle.setText(getTitleText());
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/xmlt.ttf");
        mTitle.setTypeface(typeface);
        if (mIsBack) {
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_back, null);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTitle.setCompoundDrawables(drawable, null, null, null);
        }else {
//            LinearLayout linearLayout = findViewById(R.id.ll_titleView);
//            linearLayout.setGravity(Gravity.CENTER);
        }

    }

    public void setOnClickTitleListener(OnClickTitleListener listener) {
        this.mOnClickTitleListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickTitleListener != null) {
            mOnClickTitleListener.onClickTitle();
        }
    }


    public interface OnClickTitleListener {
        void onClickTitle();
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String titleText) {
        mTitleText = titleText;
    }

    public Boolean getBack() {
        return mIsBack;
    }

    public void setBack(Boolean back) {
        mIsBack = back;
    }
}
