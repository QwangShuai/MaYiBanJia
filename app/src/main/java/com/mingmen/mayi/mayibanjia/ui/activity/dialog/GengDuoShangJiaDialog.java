package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.GengDuoShangJiaAdapter;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/17.
 */

public class GengDuoShangJiaDialog extends BaseFragmentDialog {

    @BindView(R.id.rv_gengduoshangjia)
    RecyclerView rvGengduoshangjia;
    private GengDuoShangJiaAdapter adapter;
    private String son_order_id;
    private String maket_id;
    private String type;
    private CallBack mCallBack;


    public GengDuoShangJiaDialog() {

    }
    public  GengDuoShangJiaDialog setId(String son_order_id,String maket_id,String type) {
        this.son_order_id=son_order_id;
        this.maket_id=maket_id;
        this.type = type;
      return this;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_gengduoshangjia;
    }

    @Override
    protected void init() {
        getgengduoshangjia();

    }
    public GengDuoShangJiaDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    private void getgengduoshangjia() {

        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getgengduoshangjia(PreferenceUtils.getString(MyApplication.mContext, "token",""),son_order_id,maket_id,type))
                .setDataListener(new HttpDataListener<List<XiTongTuiJianBean.CcListBean>>() {
                    @Override
                    public void onNext(List<XiTongTuiJianBean.CcListBean> data) {
                        adapter=new GengDuoShangJiaAdapter();
                        if (data.size()>0){
                            adapter.setNewData(data);
                            rvGengduoshangjia.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            rvGengduoshangjia.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    XiTongTuiJianBean.CcListBean item =(XiTongTuiJianBean.CcListBean) adapter.getItem(position);
                                    if(mCallBack!=null)
                                        mCallBack.xuanzhong(item);
                                    dismiss();
                                }
                            });
                        }else{
                            ToastUtil.showToast("没有了");
                        }



                    }
                });
    }
    @Override
    protected void initConfiguration(Configuration configuration){
        configuration.setGravity(Gravity.CENTER);
    }

    public interface CallBack{
        void xuanzhong(XiTongTuiJianBean.CcListBean msg);
    }
}
