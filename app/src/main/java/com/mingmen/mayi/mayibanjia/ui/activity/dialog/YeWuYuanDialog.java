package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.WuLiuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YeWuYuanMainActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/10/010.
 */


public class YeWuYuanDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_xuanxiang1)
    TextView tvXuanxiang1;
    @BindView(R.id.tv_xuanxiang2)
    TextView tvXuanxiang2;
    @BindView(R.id.tv_xuanxiang3)
    TextView tvXuanxiang3;
    @BindView(R.id.tv_xuanxiang4)
    TextView tvXuanxiang4;
    View xuanzhong;

    private int top;
    private YeWuYuanMainActivity activity;

    public YeWuYuanDialog setTop(int top) {
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
        setText(tvXuanxiang1,"全部餐厅");
        setText(tvXuanxiang2,"全部供货商");
        setText(tvXuanxiang3,"我的餐厅");
        setText(tvXuanxiang4,"我的供货商");
        initEvent();
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullWidth()
                .setClearBehind(true)
                .setGravity(Gravity.TOP);
    }


    private void initEvent(){
        setOnClickListener(this,tvXuanxiang1,tvXuanxiang2,tvXuanxiang3,tvXuanxiang4);
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
    private void changeView(TextView textView, View chooseView, String s, String role){
        int color=getContext().getResources().getColor(textView.getId()==chooseView.getId()?R.color.caigoudanxuanzhong:R.color.zicolor);
        textView.setTextColor(color);
        if (textView.getId()==chooseView.getId())
            activity.shuaxinList(s,role);
        this.dismiss();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cancel_view){
            dismiss();
        }else {
            changeView(tvXuanxiang1,v,"2","1");
            changeView(tvXuanxiang2,v, "2","2");
            changeView(tvXuanxiang3,v, "1","1");
            changeView(tvXuanxiang4,v, "1","2");
        }
    }


    public YeWuYuanDialog setActivity(YeWuYuanMainActivity activity) {
        this.activity = activity;
        return this;
    }
}
