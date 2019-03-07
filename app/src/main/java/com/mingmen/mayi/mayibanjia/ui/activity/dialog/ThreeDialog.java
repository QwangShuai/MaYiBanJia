package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class ThreeDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_xuanxiang1)
    TextView tvXuanxiang1;
    @BindView(R.id.tv_xuanxiang2)
    TextView tvXuanxiang2;
    @BindView(R.id.tv_xuanxiang3)
    TextView tvXuanxiang3;
    @BindView(R.id.cancel_view)
    View cancel_view;
    View xuanzhong;


    private int top;
    private AddQrCodeActivity activity;
    private String type = "";


    public ThreeDialog setTop(int top) {
        this.top = top;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_sangexuanxiang;
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
        if("qr".equals(type)){
            tvXuanxiang1.setText("已打包");
            tvXuanxiang2.setText("待确认");
            tvXuanxiang3.setText("未打包");
        }
        setOnClickListener(this,tvXuanxiang1,tvXuanxiang2,tvXuanxiang3);
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
            if("qr".equals(type)){
//                activity.updateSpList(s);
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

        }
    }

    public ThreeDialog setAddQrCodeActivity(AddQrCodeActivity activity) {
        this.activity = activity;
        this.type = "qr";
        return this;
    }
}
