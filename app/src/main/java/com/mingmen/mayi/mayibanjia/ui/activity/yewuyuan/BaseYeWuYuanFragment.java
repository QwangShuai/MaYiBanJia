package com.mingmen.mayi.mayibanjia.ui.activity.yewuyuan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBiaoPingJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.XinXiLuRuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.XinXiLuRuGHDActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YeWuYuanMainActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DingDanXiangQingAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiYeLieBiaoAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiYeLieBiaoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public abstract class BaseYeWuYuanFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvQiyeliebiao;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private View view;
    private ArrayList<QiYeLieBiaoBean> mlist = new ArrayList<QiYeLieBiaoBean>();
    private QiYeLieBiaoDialog bianjidialog;
    private QiYeLieBiaoAdapter adapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    protected boolean isCreate = false;
    public static String mytype = "1";
    public static String myrole = "2";
    private YeWuYuanMainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(MyApplication.mContext, R.layout.fragment_dingdan, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
        getQiyeLiebiao(mytype,myrole,0);
    }

    private void initview() {
        activity = (YeWuYuanMainActivity) getActivity();
//        adapter = new QiYeLieBiaoAdapter(activity, mlist,activity,BaseYeWuYuanFragment.this);
        adapter = new QiYeLieBiaoAdapter(activity, mlist);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getQiyeLiebiao(mytype,myrole,0);
            }
        };

        rvQiyeliebiao.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        rvDingdan.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvQiyeliebiao.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
//        rvDingdan.loadMoreFinish(false, true);

        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                refreshLayout.setRefreshing(true);
                // 重置adapter的数据源为空
                ye = 1;
                mlist.clear();
                getQiyeLiebiao(mytype,myrole,0);
                refreshLayout.setRefreshing(false);
            }
        });
        adapter.setOnItemClickListener(new QiYeLieBiaoAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, final int position) {
                switch (view.getId()){
                    case R.id.ll_bianji:
                        //dialog
                        bianjidialog = new QiYeLieBiaoDialog(activity,
                                activity.getResources().getIdentifier("TouMingDialog", "style", getContext().getPackageName()));
                        bianjidialog.showDialog();
                        bianjidialog.getLlBianji().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bianji(position,mlist.get(position).getRole());
                                Log.e("bianji","bianji"+position);
                                bianjidialog.cancel();
                            }
                        });
//                        bianjidialog.getLlShanchu().setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                shanchu(position);
//                                bianjidialog.cancel();
//                            }
//                        });
                        bianjidialog.getIvGuanbi().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bianjidialog.cancel();
                            }
                        });
                        break;

                }
            }
        });
        rvQiyeliebiao.setAdapter(adapter);
        activity.setChangeView(new YeWuYuanMainActivity.ChangeView() {
            @Override
            public void changeType(String type, String role) {
                mytype = type;
                myrole = role;
                onResume();
            }

            @Override
            public void changCanshu(String name, String leibie) {
                ye = 1;
                mlist.clear();
                getQiyeLiebiaodaicanshu(name,leibie);
            }
        });
    }

    public abstract String getZhuangTai();

    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        ye = 1;
//        updateList(true);
        mlist.clear();
        getQiyeLiebiao(mytype,myrole,0);
        adapter.notifyDataSetChanged();
    }
    //编辑
    private void bianji(int position,String type) {
        Intent intent = new Intent();
        if(type.equals("1")){
            intent.setClass(getContext(), XinXiLuRuActivity.class);
        } else {
            intent.setClass(getContext(), XinXiLuRuGHDActivity.class);
        }

        intent.putExtra("rukou","edit");
        intent.putExtra("xinxi",new Gson().toJson(mlist.get(position)));
        startActivity(intent);
    }
    //查询企业列表..带参数
    public void getQiyeLiebiaodaicanshu(String trim, String leibieid) {
        Log.e("getQiyeLiebiaodaicanshu",trim+"--"+leibieid);
        HttpManager.getInstance()
                .with(activity)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getqiyedaicanshu(PreferenceUtils.getString(MyApplication.mContext, "token",""),trim,leibieid,mytype,getZhuangTai()))
                .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                    @Override
                    public void onNext(final List<QiYeLieBiaoBean> data) {
                        int size = data==null?0:data.size();
                        if(size!=0){
                            if(data.size()==10){
                                rvQiyeliebiao.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQiyeliebiao.loadMoreFinish(false, false);
                            } else {
                                rvQiyeliebiao.loadMoreFinish(true, false);
                            }
                            mlist.addAll(data);
//                            adapter.setNewList(mlist);

                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
//    删除
//    private void shanchu(final int position) {
//        Log.e("shanchu",position+"-");
//        String id = mlist.get(position).getCompany_id();
//        Log.e("id",id+"_--");
//        HttpManager.getInstance()
//                .with(getContext())
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .delqiye(PreferenceUtils.getString(MyApplication.mContext, "token",""),id))
//                .setDataListener(new HttpDataListener<String>() {
//                    @Override
//                    public void onNext(String data) {
//                        Log.e("data",data+"---");
//                        mlist.remove(position);
//                        adapter.notifyDataSetChanged();
//
//                    }
//                });
//    }
public void getQiyeLiebiao(String type,String role,int myye) {
    if(myye!=0){
        ye = myye;
    }
    Log.e( "getQiyeLiebiao: ",type+"---"+role +"----");
    HttpManager.getInstance()
            .with(activity)
            .setObservable(
                    RetrofitManager
                            .getService()
                            .getqiyeliebiao(PreferenceUtils.getString(MyApplication.mContext, "token", ""), type, getZhuangTai(), ye + "", role))
            .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                @Override
                public void onNext(final List<QiYeLieBiaoBean> data) {
                    ye++;
                    int size = data == null ? 0 : data.size();
                    if (size != 0) {
                        if (data.size() == 10) {
                            rvQiyeliebiao.loadMoreFinish(false, true);
                        } else if (data.size() > 0) {
                            rvQiyeliebiao.loadMoreFinish(false, false);
                        } else {
                            rvQiyeliebiao.loadMoreFinish(true, false);
                        }
                        mlist.addAll(data);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
}
    private boolean hasStarted = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            hasStarted = true;
            isCreate = true;
        } else {
            if (hasStarted) {
                hasStarted = false;
            }
        }
    }
}
