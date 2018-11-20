package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class YunFeiTypeDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_xuanxiang1)
    TextView tvXuanxiang1;
    @BindView(R.id.tv_xuanxiang2)
    TextView tvXuanxiang2;
    @BindView(R.id.tv_xuanxiang3)
    TextView tvXuanxiang3;
    View xuanzhong;

    private int xuanxiang1, xuanxiang2, xuanxiang3;

    private int top;
    private YunFeiJieSuanActivity activity;

    public YunFeiTypeDialog init(int xuanxiang1, int xuanxiang2, int xuanxiang3) {
        this.xuanxiang1 = xuanxiang1;
        this.xuanxiang2 = xuanxiang2;
        this.xuanxiang3 = xuanxiang3;
        return this;
    }

    public YunFeiTypeDialog setTop(int top) {
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
        setText(tvXuanxiang1,"未结算("+xuanxiang1+")");
        setText(tvXuanxiang2,"结算中("+xuanxiang2+")");
        setText(tvXuanxiang3,"已结算("+xuanxiang3+")");
       initEvent();
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullWidth()
                .setClearBehind(true)
                .setGravity(Gravity.TOP);
    }


    private void initEvent(){
        setOnClickListener(this,tvXuanxiang1,tvXuanxiang2,tvXuanxiang3);
        setOnClickListener(this,R.id.cancel_view);
    }

    private void setText(TextView textView,String text){
        if(!TextUtils.isEmpty(text)){
            textView.setText(text);
        }
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
            activity.getList(s);
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


    public YunFeiTypeDialog setActivity(YunFeiJieSuanActivity activity) {
        this.activity = activity;
        return this;
    }
}
