package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.PingJiaLableAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaBiaoPingJiaActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_touxiang)
    CircleImageView ivTouxiang;
    @BindView(R.id.iv_xuanzhong)
    ImageView ivXuanzhong;
    @BindView(R.id.rb_pingfen)
    RatingBar rbPingfen;
    @BindView(R.id.ll_pingjia)
    LinearLayout ll_pingjia;
    @BindView(R.id.rv_lable)
    RecyclerView rvLable;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    private int starsImgHeight;

    private Context mContext;
    private PingJiaLableAdapter adapter;
    private boolean isNiming;
    private String lableId;
    private String niming = "1";
    private HashMap<String, PingJiaLableBean> xuanzhong = new HashMap<>();
    private String order_id;
    private String company_id;
    private String photo;
    @Override
    public int getLayoutId() {
        return R.layout.activity_fa_biao_ping_jia;
    }

    @Override
    protected void initData() {

        tvTitle.setText("发表评价");
        mContext = FaBiaoPingJiaActivity.this;

        photo = getIntent().getStringExtra("photo");
        order_id = getIntent().getStringExtra("order_id");
        company_id = getIntent().getStringExtra("company_id");
        Glide.with(mContext).load(photo).into(ivTouxiang);

        rbPingfen.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0){
                    tvPinglun.setText("0分真香");
                }else if(rating<3){
                    tvPinglun.setText(rating+"分emmmmm");
                } else if(rating<5){
                    tvPinglun.setText(rating+"分阔以阔以");
                } else {
                    tvPinglun.setText("5分非常完美");
                }
            }
        });
        getPingjiaLable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_pingjia, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_pingjia:
                if(isNiming){
                    isNiming = false;
                    ivXuanzhong.setSelected(false);
                    niming = "0";

                } else {
                    isNiming = true;
                    ivXuanzhong.setSelected(true);
                    niming = "1";
                }
                break;
            case R.id.btn_submit:
                getLableId();
                if(rbPingfen.getRating()<1){
                    ToastUtil.showToast("星级评分最低为1星");
                } else {
                    postPingjia();
                }
                break;
        }
    }
    public void getPingjiaLable(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getPingjiaLable())
                .setDataListener(new HttpDataListener<List<PingJiaLableBean>>() {
                    @Override
                    public void onNext(List<PingJiaLableBean> data) {
                        adapter = new PingJiaLableAdapter(mContext,data,FaBiaoPingJiaActivity.this);
                        rvLable.setLayoutManager(new GridLayoutManager(mContext,2));
                        rvLable.setAdapter(adapter);
                    }
                });
    }
    public void delViewShow(PingJiaLableBean item) {//删除item
        xuanzhong.remove(item.getSon_number());
    }
    public void addViewShow(PingJiaLableBean item) {//存储点击item
        xuanzhong.put(item.getSon_number(), item);
    }

    private void getLableId(){
        lableId = "";
        int count = 0;
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            PingJiaLableBean value = xuanzhong.get(key);
            if (value.getSon_number().isEmpty()) {//没选中的不拼   避免有多余的,
            } else {
                lableId += key + ",";
                count++;
            }
        }
        if (count != 0) {
            lableId = lableId.substring(0, lableId.length() - 1);
            Log.e("lableId", lableId + "---");
        } else {
            ToastUtil.showToast("您还没有选择标签啊");
        }
    }

    public void postPingjia(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .postPingjia(PreferenceUtils.getString(MyApplication.mContext, "token", ""),niming,order_id,rbPingfen.getRating()+"",
                                lableId,company_id,"1"))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("评价成功");
                        finish();
                    }
                });
    }
}
