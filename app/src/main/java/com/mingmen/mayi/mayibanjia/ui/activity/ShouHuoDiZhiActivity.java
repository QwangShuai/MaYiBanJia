package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddressListBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShouHuoDiZhiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class ShouHuoDiZhiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_shouhuodizhi)
    RecyclerView rvShouhuodizhi;
    @BindView(R.id.ll_meiyou)
    LinearLayout llMeiyou;
    private Context mContext;
    private ShouHuoDiZhiAdapter adapter;
    private String rukou;


    @Override
    public int getLayoutId() {
        return R.layout.activity_shouhuodizhi;
    }

    @Override
    protected void initData() {
        mContext = ShouHuoDiZhiActivity.this;
        tvTitle.setText("收货地址");
        tvRight.setText("添加");
        getaddressList();

        rukou = getIntent().getStringExtra("rukou");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getaddressList();
    }

    private void getaddressList() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getaddresslist(PreferenceUtils.getString(MyApplication.mContext, "token","")))
                .setDataListener(new HttpDataListener<List<AddressListBean>>() {
                    @Override
                    public void onNext(final List<AddressListBean> data) {
                        if (data.size()>0){
                            rvShouhuodizhi.setVisibility(View.VISIBLE);
                            llMeiyou.setVisibility(View.GONE);
                            Log.e("data","--"+gson.toJson(data));
                            adapter = new ShouHuoDiZhiAdapter(mContext, data);
                            rvShouhuodizhi.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            rvShouhuodizhi.setAdapter(adapter);
                            adapter.setOnItemClickListener(new ShouHuoDiZhiAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                   switch (view.getId()){
                                       case R.id.ll_bianji:
                                           Intent intent = new Intent(mContext,AddAddressActivity.class);
                                           bundle.putString("rukou","edit");
                                           bundle.putString("json",gson.toJson(data.get(position)));
                                           intent.putExtras(bundle);
                                           startActivity(intent);
                                           break;
                                       case R.id.ll_shanchu:
                                           Log.e("delid",data.get(position).getAddress_id());
                                           addressDel(data.get(position).getAddress_id());
                                           break;
                                       case R.id.ll_kuang:
                                           if ("xuanze".equals(rukou)){
                                               Intent fanhui=new Intent(mContext,QueRenDingDanActivity.class);
                                               fanhui.putExtra("dizhi",gson.toJson(data.get(position)));
                                               setResult(1,fanhui);
                                               finish();
                                           }

                                           break;

                                   }
                                }
                            });
                        }else{
                            rvShouhuodizhi.setVisibility(View.GONE);
                            llMeiyou.setVisibility(View.VISIBLE);
                        }


                    }
                });
    }

    private void addressDel(String lid) {
        Log.e("idididididid",lid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .deladdress(PreferenceUtils.getString(MyApplication.mContext, "token",""),lid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("del",data+"(＾－＾)V"+"shanchula");
                        getaddressList();

                    }
                });

    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                Intent intent=new Intent(mContext,AddAddressActivity.class);
                bundle.putString("rukou","add");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
