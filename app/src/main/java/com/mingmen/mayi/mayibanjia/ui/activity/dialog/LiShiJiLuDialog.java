package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.LiShiJiLuBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GengDuoShangJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.LiShiGouMaiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.LiShiShouCangAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/17.
 */

public class LiShiJiLuDialog extends BaseFragmentDialog {
    @BindView(R.id.view_lishi)
    View viewLishi;
    @BindView(R.id.ll_lishi)
    LinearLayout llLishi;
    @BindView(R.id.view_shoucang)
    View viewShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.iv_tu)
    ImageView ivTu;
    @BindView(R.id.tv_spming)
    TextView tvSpming;
    @BindView(R.id.ll_tu)
    LinearLayout llTu;
    @BindView(R.id.rv_lishi)
    RecyclerView rvLishi;
    @BindView(R.id.tv_meiyou)
    TextView tvMeiyou;
    private GengDuoShangJiaAdapter adapter;
    private String son_order_id;
    private CallBack mCallBack;
    private LiShiGouMaiAdapter lishigoumaiadapter;
    private LiShiShouCangAdapter shoucangadapter;
    private List<XiTongTuiJianBean.CcListBean> lishidata;
    private List<XiTongTuiJianBean.CcListBean> shoucangdata;

    public LiShiJiLuDialog() {

    }

    public LiShiJiLuDialog setSon_order_id(List<XiTongTuiJianBean.CcListBean> lishidata, List<XiTongTuiJianBean.CcListBean> shoucangdata) {
        this.lishidata = lishidata;
        this.shoucangdata = shoucangdata;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_lishijilu;
    }

    @Override
    protected void init() {
        getJiLu();
        llLishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLishi.setVisibility(View.VISIBLE);
                viewShoucang.setVisibility(View.INVISIBLE);
                lishixianshi();
            }
        });
        llShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLishi.setVisibility(View.INVISIBLE);
                viewShoucang.setVisibility(View.VISIBLE);
                shoucangxianshi();
            }
        });
    }

    public LiShiJiLuDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    private void getJiLu() {
        lishixianshi();
        rvLishi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvLishi.setNestedScrollingEnabled(false);
        lishigoumaiadapter = new LiShiGouMaiAdapter(getContext(), lishidata);
        shoucangadapter = new LiShiShouCangAdapter(getContext(), shoucangdata);
        shoucangadapter.setOnItemClickListener(new LiShiShouCangAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (mCallBack != null) {
                    mCallBack.xuanzhong("shoucang", shoucangdata.get(position));
                    dismiss();
                }
            }
        });
        lishigoumaiadapter.setOnItemClickListener(new LiShiGouMaiAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (mCallBack != null)
                    mCallBack.xuanzhong("goumai", lishidata.get(position));
                dismiss();
            }
        });
        shoucangadapter.notifyDataSetChanged();
        lishigoumaiadapter.notifyDataSetChanged();


    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullWidth()
                .setGravity(Gravity.BOTTOM);
    }

    private void lishixianshi() {
        if (lishidata == null || lishidata.size() == 0) {
            tvMeiyou.setText("您还没有历史购买记录");
            rvLishi.setVisibility(View.GONE);
            tvMeiyou.setVisibility(View.VISIBLE);
        } else {
            rvLishi.setAdapter(lishigoumaiadapter);
            rvLishi.setVisibility(View.VISIBLE);
            tvMeiyou.setVisibility(View.GONE);
            lishigoumaiadapter.notifyDataSetChanged();
        }

    }

    private void shoucangxianshi() {
        if (shoucangdata == null || shoucangdata.size() == 0) {
            tvMeiyou.setText("您还没有收藏记录");
            rvLishi.setVisibility(View.GONE);
            tvMeiyou.setVisibility(View.VISIBLE);
        } else {
            rvLishi.setAdapter(shoucangadapter);
            rvLishi.setVisibility(View.VISIBLE);
            tvMeiyou.setVisibility(View.GONE);
            shoucangadapter.notifyDataSetChanged();
        }
    }

    public interface CallBack {
        void xuanzhong(String lujing, XiTongTuiJianBean.CcListBean msg);
    }

}
