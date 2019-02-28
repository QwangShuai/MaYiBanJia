package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/10/010.
 */

public class XuanZeJueSeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_cantingduan)
    ImageView ivCantingduan;
    @BindView(R.id.tv_cantingduan)
    TextView tvCantingduan;
    @BindView(R.id.ll_cantingduan)
    LinearLayout llCantingduan;
    @BindView(R.id.iv_gonghuoduan)
    ImageView ivGonghuoduan;
    @BindView(R.id.tv_gonghuoduan)
    TextView tvGonghuoduan;
    @BindView(R.id.ll_gonghuoduan)
    LinearLayout llGonghuoduan;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    @BindView(R.id.iv_shequshichang)
    ImageView ivShequshichang;
    @BindView(R.id.tv_shequshichang)
    TextView tvShequshichang;
    @BindView(R.id.ll_shequshichang)
    LinearLayout llShequshichang;
    private Context mContext;
    private String isXz = "1";

    @Override
    public int getLayoutId() {
        return R.layout.activity_xuanzejuese;
    }

    @Override
    protected void initData() {
        mContext = XuanZeJueSeActivity.this;
        tvTitle.setText("请选择角色");
        btXiayibu.setEnabled(false);
        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
        bundle = getIntent().getExtras();


    }

    @OnClick({R.id.iv_back, R.id.ll_cantingduan, R.id.ll_gonghuoduan, R.id.bt_xiayibu,R.id.ll_shequshichang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_cantingduan:
                ivCantingduan.setSelected(true);
                ivGonghuoduan.setSelected(false);
                ivShequshichang.setSelected(false);

                llCantingduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                llGonghuoduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));
                llShequshichang.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));

                tvCantingduan.setTextColor(getResources().getColor(R.color.white));
                tvGonghuoduan.setTextColor(getResources().getColor(R.color.green_6dd46d));
                tvShequshichang.setTextColor(getResources().getColor(R.color.green_6dd46d));

                isXz = "1";
                btXiayibu.setEnabled(true);
                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                break;
            case R.id.ll_gonghuoduan:
                ivCantingduan.setSelected(false);
                ivGonghuoduan.setSelected(true);
                ivShequshichang.setSelected(false);

                llCantingduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));
                llGonghuoduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                llShequshichang.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));

                tvCantingduan.setTextColor(getResources().getColor(R.color.green_6dd46d));
                tvGonghuoduan.setTextColor(getResources().getColor(R.color.white));
                tvShequshichang.setTextColor(getResources().getColor(R.color.green_6dd46d));

                isXz = "2";
                btXiayibu.setEnabled(true);
                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                break;
            case R.id.ll_shequshichang:
                ivCantingduan.setSelected(false);
                ivGonghuoduan.setSelected(false);
                ivShequshichang.setSelected(true);

                llCantingduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));
                llGonghuoduan.setBackground(getResources().getDrawable(R.drawable.fillet_solid_white_5));
                llShequshichang.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));

                tvCantingduan.setTextColor(getResources().getColor(R.color.green_6dd46d));
                tvGonghuoduan.setTextColor(getResources().getColor(R.color.green_6dd46d));
                tvShequshichang.setTextColor(getResources().getColor(R.color.white));

                isXz = "3";
                btXiayibu.setEnabled(true);
                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                break;
            case R.id.bt_xiayibu:
                if (isXz.equals("1")) {
                    Intent intent = new Intent(mContext, CTDWanShanXinXiActivity.class);
                    bundle.putString("jueseid", isXz);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
//                else if(isXz.equals("2")){
//                    Intent intent = new Intent(mContext, GHDWanShanXinXiActivity.class);
//                    bundle.putString("jueseid", "2");.,mmp[
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
                else {
                    Intent intent = new Intent(mContext, GHDWanShanXinXiActivity.class);
                    bundle.putString("jueseid", isXz);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
