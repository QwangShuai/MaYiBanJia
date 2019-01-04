package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuObjBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.WuLiuFenPeiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.WuLiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WuLiuActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.rv_fenpeiwuliu)
    SwipeMenuRecyclerView rvFenpeiwuliu;

    private Context mContext;
    private WuLiuDialog titleDialog;
    private WuLiuFenPeiAdapter adapter;
    private List<WuLiuBean> mList= new ArrayList<WuLiuBean>();
    private int count = 1;
    private PopupWindow tuichupop;
    private ConfirmDialog confirmDialog;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;


    @Override
    public int getLayoutId() {
        return R.layout.activity_wu_liu;
    }

    @Override
    protected void initData() {
        mContext=WuLiuActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        getWuLiu("",true);
    }
    @OnClick({R.id.ll_title,R.id.iv_sangedian})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.ll_title:
                titleDialog.show(getSupportFragmentManager());
                break;
            case R.id.iv_sangedian:
                showTuiChuPop();
                break;
        }
    }
    public void getWuLiu(final String type,final boolean b){
        Log.e("2222","看不到请求网络");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),type,count+"",""))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        Log.e("2222",bean.toString());
                        mList=bean.getDdList();
                        if (count==1){
                            rvFenpeiwuliu.loadMoreFinish(false,true);
                        } else{
                            if(mList.size()>0&&mList.size()<5){
                                rvFenpeiwuliu.loadMoreFinish(false,true);
                            } else {
                                if(mList.size()==0){
                                    rvFenpeiwuliu.loadMoreFinish(true,false);
                                } else {
                                    rvFenpeiwuliu.loadMoreFinish(false,true);
                                }
                            }
                        }
                        if(b){
                            mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    getWuLiu(type,false);
                                }
                            };

                            adapter = new WuLiuFenPeiAdapter(mContext,mList,WuLiuActivity.this,type);
                            rvFenpeiwuliu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            rvFenpeiwuliu.useDefaultLoadMore(); // 使用默认的加载更多的View。
                            rvFenpeiwuliu.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
                            rvFenpeiwuliu.loadMoreFinish(false, true);
                            rvFenpeiwuliu.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }

                        count++;
                        showTongji(bean.getCount()+"",bean.getCount0()+"",bean.getCount1()+"",bean.getCount2()+"");
                    }
                });
    }
    public void shuaxinWuLiu(final String type){
        mList.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getWuliu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),type,"1",""))
                .setDataListener(new HttpDataListener<WuLiuObjBean<WuLiuBean>>() {
                    @Override
                    public void onNext(WuLiuObjBean<WuLiuBean> bean) {
                        mList=bean.getDdList();
                        adapter = new WuLiuFenPeiAdapter(mContext,mList,WuLiuActivity.this,type);
                        rvFenpeiwuliu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvFenpeiwuliu.setAdapter(adapter);
                        showTongji(bean.getCount()+"",bean.getCount0()+"",bean.getCount1()+"",bean.getCount2()+"");
                    }
                });
    }
    private void showTuiChuPop() {
        View view = View.inflate(mContext, R.layout.pop_jiesuan, null);
        tuichupop = new PopupWindow(view);

        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        tuichupop.setWidth(AppUtil.dip2px(130));
        tuichupop.setHeight(AppUtil.dip2px(150));
        LinearLayout ll_tuichu = view.findViewById(R.id.ll_tuichu);
        LinearLayout ll_yunfei = view.findViewById(R.id.ll_jiesuan);
        LinearLayout ll_jiesuanjieguo = view.findViewById(R.id.ll_jiesuanjieguo);
        ll_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", false);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        confirmDialog.dismiss();
                        tuichupop.dismiss();
                        finish();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        tuichupop.dismiss();
                    }
                });
            }
        });
        ll_jiesuanjieguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,JieSuanJieGuoActivity.class));
            }
        });
        ll_yunfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,YunFeiJieSuanActivity.class));
            }
        });
        tuichupop.setOutsideTouchable(true);
        tuichupop.setBackgroundDrawable(new BitmapDrawable());
        tuichupop.showAsDropDown(ivSangedian);
    }

    public void showTongji(String zong,String wfc,String yfc,String ybg){//显示上方统计
        titleDialog = new WuLiuDialog()
                .setActivity(WuLiuActivity.this)
                .init("全部状态("+zong+")","未分车("+wfc+")","已分车("+yfc+")","已变更("+ybg+")")
                .setTop(AppUtil.dip2px(44));
    }
}
