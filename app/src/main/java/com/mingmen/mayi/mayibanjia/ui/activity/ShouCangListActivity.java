package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ShouCangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShouCangAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SouSuoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ShouCangListActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.rv_shoucang)
    SwipeMenuRecyclerView rvShoucang;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;
    @BindView(R.id.tv_kongview)
    TextView tvKongview;
    private Context mContext=ShouCangListActivity.this;
    private ShouCangAdapter adapter;
    private List<ShouCangBean> mlist=new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye=1;
    private SwipeMenuCreator mSwipeMenuCreator;
    private SwipeMenuItemClickListener mMenuItemClickListener;
    private String sousuoname="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_shoucanglist;
    }

    @Override
    protected void initData() {
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adaptgetshoucanger的数据源为空
                // 获取第第0条到第PAGE_COUNT（值为10）条的数据
                getshoucang(1);
                srlShuaxin.setRefreshing(false);
            }
        });
        getshoucang(1);

        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                ye++;
                Log.e("ye++++", ye + "--");
                getshoucang(ye);
            }
        };
        mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                deleteItem.setText("删除");
                deleteItem.setBackgroundColor(getResources().getColor(R.color.red_ff3300));
                deleteItem.setTextSize(18);
                deleteItem.setTextColor(getResources().getColor(R.color.white));
                deleteItem.setHeight(MATCH_PARENT);
                deleteItem.setWidth(AppUtil.dip2px(50));
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            }
        };
        mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                Log.e("shanchu","shanchu"+adapterPosition);
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .quxiaoshoucang(PreferenceUtils.getString(MyApplication.mContext, "token", ""),mlist.get(adapterPosition).getCollect_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                Log.e("data", data + "---");
                                mlist.remove(adapterPosition);
                                setAdaptershuaxin();
                            }
                        });

            }
        };
        adapter = new ShouCangAdapter();

        rvShoucang.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvShoucang.setSwipeMenuCreator(mSwipeMenuCreator);
        rvShoucang.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvShoucang.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShoucang.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShoucang.loadMoreFinish(false, true);
        rvShoucang.setAdapter(adapter);

    }

    private void getshoucang(final int ye) {
        Log.e("shoucangcanshu",PreferenceUtils.getString(MyApplication.mContext, "token","")+"===="+ye);
         HttpManager.getInstance()
                 .with(mContext)
                 .setObservable(
                         RetrofitManager
                            .getService()
                            .getshoucanglist(PreferenceUtils.getString(MyApplication.mContext, "token",""),ye+"",sousuoname))
                    .setDataListener(new HttpDataListener<List<ShouCangBean>>() {
                @Override
                public void onNext(List<ShouCangBean> data) {
//                    Log.e("data",gson .toJson(data)+"---");
                    if (srlShuaxin.isRefreshing()){
                        srlShuaxin.setRefreshing(false);
                    }
                    if (data.size()>0&&data.size()<10){
                        rvShoucang.loadMoreFinish(false, false);
                    }else{
                        if (data.size()==0){
                            rvShoucang.loadMoreFinish(true, false);
                        }else{
                            rvShoucang.loadMoreFinish(false, true);
                        }
                    }
                    if (ye==1){
                        mlist=new ArrayList<>();
                        if(data.size()==0){

                        } else{
                            mlist.addAll(data);
                        }
                    }else{
                        mlist.addAll(data);
                    }
                    adapter.setNewData(mlist);
                    if (mlist.size()==0){
                        tvKongview.setText("您还没有收藏商品");
                        tvKongview.setVisibility(View.VISIBLE);
                        rvShoucang.setVisibility(View.GONE);
                    }else{
                        tvKongview.setVisibility(View.GONE);
                        rvShoucang.setVisibility(View.VISIBLE);
                    }
                }
            },ye==1?true:false);
    }

    private void setAdaptershuaxin(){
        if (adapter.getData().size()>0){
            tvKongview.setVisibility(View.GONE);
        }else{
            tvKongview.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }
    @OnClick({R.id.iv_fanhui, R.id.iv_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.iv_sousuo:
                new SouSuoDialog()
                        .setData(sousuoname)
                        .setCallBack(new SouSuoDialog.CallBack() {
                            @Override
                            public void sousuozi(String msg) {
                                Log.e("msg",msg+"==");
                                sousuoname=msg;
                                getshoucang(1);
                            }
                        }).show(getSupportFragmentManager());
                break;
        }
    }
}
