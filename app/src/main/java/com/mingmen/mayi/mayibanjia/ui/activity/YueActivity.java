package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ZhangHuRenZhengBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui.JiaoYiMingXiActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YueActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.rl_chongzhi)
    RelativeLayout rlChongzhi;
    @BindView(R.id.rl_tixian)
    RelativeLayout rlTixian;

    private String yue="0";
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yue;
    }

    @Override
    protected void initData() {
        mContext = YueActivity.this;
        tvTitle.setText("我的余额");
//        tvRight.setText("交易明细");
        getYue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.rl_chongzhi, R.id.rl_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
//            case R.id.tv_right:
//                Jump_intent(JiaoYiMingXiActivity.class, new Bundle());
//                break;
            case R.id.rl_chongzhi:
                Jump_intent(JiaoYiMingXiActivity.class, new Bundle());
                break;
            case R.id.rl_tixian:
                Bundle bundle = new Bundle();
                bundle.putString("yue",yue);
                Jump_intent(TiXianActivity.class, bundle);
                break;
        }
    }

    private void getYue(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getYue(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        yue = data;
                        tvYue.setText(data);
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getYue();
    }
}
