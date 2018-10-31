package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GengDuoShangJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiangDanShangPinAdapter;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/17.
 */

public class QiangDanShangPinDialog extends BaseFragmentDialog {

    @BindView(R.id.rv_qiangdandialog)
    RecyclerView rvQiangdandialog;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.et_fujiafei)
    EditText etFujiafei;
    private QiangDanShangPinAdapter adapter;
    private List<ShangPinBean> data;
    private CallBack mCallBack;
    private ShangPinBean xuanzhongitem;

    public QiangDanShangPinDialog() {

    }
    public QiangDanShangPinDialog setData(List<ShangPinBean> data)
    {
        this.data=data;
        return this;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_qiangdan;
    }

    @Override
    protected void init() {

        adapter=new QiangDanShangPinAdapter();
        Log.e("QiangDanShangPinDialog",new Gson().toJson(data));
        adapter.setNewData(data);
        rvQiangdandialog.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvQiangdandialog.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_kuang:
                        ShangPinBean item =(ShangPinBean) adapter1.getItem(position);
                        adapter.setXuanzhong(item.getCommodity_id());
                        xuanzhongitem=item;
                        break;
                }

            }
        });
        btQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack!=null)
                    mCallBack.xuanzhongitem(xuanzhongitem,etFujiafei.getText().toString());
                dismiss();
            }
        });
        btQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack!=null)
                    mCallBack.xuanzhongitem(null,null);
                dismiss();
            }
        });
    }
    public QiangDanShangPinDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected void initConfiguration(Configuration configuration){
        configuration
                .setWidth(AppUtil.dip2px(340))
                .setHeight(AppUtil.dip2px(350))
                .setGravity(Gravity.CENTER);
    }

    public interface CallBack{
        void xuanzhongitem(ShangPinBean msg,String fujifei);
    }
}
