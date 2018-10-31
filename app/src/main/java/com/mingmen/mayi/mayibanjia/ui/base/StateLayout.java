package com.mingmen.mayi.mayibanjia.ui.base;

/**
 * Created by Administrator on 2018/7/4/004.
 */


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;

/**
 * 方便我们切换不同的界面：
 * 提供几个切换界面的方法
 * @author lxj
 *
 */
public class StateLayout extends FrameLayout {


    private View errorView;
    private View successView;
//    private boolean b = false;
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public StateLayout(Context context) {
        this(context,null);
    }
    public StateLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    /**
     * 初始化几个View,并添加进来
     */
    private void init() {

        //2.初始化加载失败的界面
        errorView = View.inflate(MyApplication.mContext,
                R.layout.page_error, null);
		TextView btn_reload = (TextView) errorView.findViewById(R.id.cuowu);
		btn_reload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//要重新加载数据
				if(listener!=null){
					listener.onReloadBtnClick();
				}
			}
		});
//        if(!b){
//            b = true;
            addView(errorView);
//        }


        //隐藏所有的View
        hideAllView();
    }

    /**
     * 设置自己的successView。就是数据加载成功要展示的那个View
     * @param successView
     */
    public void setSuccessView(View successView){
        this.successView = successView;
        this.successView.setVisibility(View.INVISIBLE);
        //将成功View添加进来
        addView(this.successView);
    }



    public void setErrorView(View errorView){
        this.errorView = errorView;
        this.errorView.setVisibility(View.INVISIBLE);
        //将成功View添加进来
//        if(!b){
//            b = true;
            addView(this.errorView);
//        }

    }

    private void hideAllView(){
        errorView.setVisibility(View.INVISIBLE);
        if(successView!=null){
            successView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示失败的界面
     */
    public void showErrorView(){
        hideAllView();
        errorView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示成功的界面
     */
    public void showSuccessView(){
        hideAllView();
        if(successView!=null){
            successView.setVisibility(View.VISIBLE);
        }
    }
    OnReloadBtnClickListener listener;
    public void setOnReloadClickListener(OnReloadBtnClickListener listener){
        this.listener = listener;
    }

    public interface OnReloadBtnClickListener{
        void onReloadBtnClick();
    }
}