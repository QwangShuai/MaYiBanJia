package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiTeJiaActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class ShangPinTeJiaDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_xuanxiang1)
    TextView tvXuanxiang1;
    @BindView(R.id.tv_xuanxiang2)
    TextView tvXuanxiang2;
    @BindView(R.id.tv_xuanxiang3)
    TextView tvXuanxiang3;
    @BindView(R.id.tv_xuanxiang4)
    TextView tvXuanxiang4;
    @BindView(R.id.cancel_view)
    View cancel_view;
    View xuanzhong;


    private int top;
    private ShangPinGuanLiTeJiaActivity activity;
    private String type = "";


    public ShangPinTeJiaDialog setTop(int top) {
        this.top = top;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pop_sigexuanxiang;
    }

    @Override
    protected void init() {
        getView().setPadding(0,top,0,0);
        initEvent();
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullWidth()
                .setClearBehind(true)
                .setGravity(Gravity.TOP);
    }

    private void initEvent(){
        if("sp_tj".equals(type)){
            tvXuanxiang1.setText("全部");
            tvXuanxiang2.setText("已上架");
            tvXuanxiang3.setText("已下架");
            tvXuanxiang4.setText("审核中");
        }
        setOnClickListener(this,tvXuanxiang1,tvXuanxiang2,tvXuanxiang3,tvXuanxiang4);
        setOnClickListener(this,R.id.cancel_view);
    }

    /**
     * 切换选择view状态
     * @param textView
     * @param chooseView
     * @param s
     */
    private void changeView(TextView textView, View chooseView, String s){
        int color=getContext().getResources().getColor(textView.getId()==chooseView.getId()?R.color.caigoudanxuanzhong:R.color.zicolor);
        textView.setTextColor(color);
        if (textView.getId()==chooseView.getId()){
            if("sp_tj".equals(type)){
                activity.setType(s);
            }
        }

        this.dismiss();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cancel_view){
            dismiss();
        }else {
            changeView(tvXuanxiang1,v,"0");
            changeView(tvXuanxiang2,v, "1");
            changeView(tvXuanxiang3,v, "2");
            changeView(tvXuanxiang3,v, "3");
        }
    }

    public ShangPinTeJiaDialog setTeJiaActivity(ShangPinGuanLiTeJiaActivity activity) {
        this.activity = activity;
        this.type = "sp_tj";
        return this;
    }
}
