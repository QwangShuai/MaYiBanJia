package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.bean.LiuLanJiLuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DianPuGuanZhuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.LiuLanJiLuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SouSuoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class LiuLanJiLuActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.rv_shoucang)
    SwipeMenuRecyclerView rvShoucang;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;
    @BindView(R.id.tv_kongview)
    TextView tvKongview;

    private Context mContext=LiuLanJiLuActivity.this;
    private LiuLanJiLuAdapter adapter;
    private List<LiuLanJiLuBean> mlist=new ArrayList<>();
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye=1;
    private SwipeMenuCreator mSwipeMenuCreator;
    private SwipeMenuItemClickListener mMenuItemClickListener;
    private String sousuoname="";
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_liu_lan_ji_lu;
    }

    @Override
    protected void initData() {
        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getList();
            }
        };
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adaptgetshoucanger的数据源为空
                // 获取第第0条到第PAGE_COUNT（值为10）条的数据
                mlist.clear();
                ye = 1;
                getList();
                srlShuaxin.setRefreshing(false);
            }
        });
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
                                        .delLiuLanJiLu(PreferenceUtils.getString(MyApplication.mContext, "token",""),mlist.get(adapterPosition).getBrowse_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                ToastUtil.showToast("删除记录成功");
                                mlist.remove(adapterPosition);
                                adapter.notifyDataSetChanged();
                            }
                        });



            }
        };
        mlist=new ArrayList<>();
        adapter = new LiuLanJiLuAdapter(mContext, mlist);
        adapter.setOnItemClickListener(new LiuLanJiLuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.iv_addcar:
                        final LiuLanJiLuBean data = mlist.get(position);
                        String spguige = "";
                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), data.getPackStandard(), data.getRation_one() + "", data.getPrice() + ""
                                , data.getGoodsPicture());
                        final String finalSpguige = spguige;
                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                Log.e("data.getShopping_id()", data.getShoppingId() + "---");
                                if (Integer.parseInt(shuliang) >= Integer.parseInt(data.getRation_one())) {
                                    addcar(data.getCommodity_id(), shuliang, data.getShoppingId(), "", finalSpguige);
                                } else {
                                    ToastUtil.showToast("不够起订量");
                                }
                                Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                jiarugouwuchedialog.cancel();
                                updateGwc();
                            }
                        });
                        break;
                }
            }
        });
        rvShoucang.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvShoucang.setSwipeMenuCreator(mSwipeMenuCreator);
        rvShoucang.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvShoucang.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShoucang.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShoucang.loadMoreFinish(false, true);
        rvShoucang.setAdapter(adapter);
        getList();
    }

    @OnClick({R.id.iv_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
        }
    }

    public void getList(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getLiuLanJiLuList(PreferenceUtils.getString(MyApplication.mContext, "token",""),ye+""))
                .setDataListener(new HttpDataListener<List<LiuLanJiLuBean>>() {
                    @Override
                    public void onNext(List<LiuLanJiLuBean> data) {
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
                            if(data.size()==0){

                            } else{
                                mlist.addAll(data);
                            }
                        }else{
                            mlist.addAll(data);
                        }
                        if (mlist.size()==0){
                            tvKongview.setText("您还没有收藏商品");
                            tvKongview.setVisibility(View.VISIBLE);
                            rvShoucang.setVisibility(View.GONE);
                        }else{
                            tvKongview.setVisibility(View.GONE);
                            rvShoucang.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                        ye++;
                    }
                },ye==1?true:false);
    }
    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid, String gouwucheid, String guigeid) {
        Log.e("canshu", spid + "---" + shuliang + "---" + dianpuid + "---" + gouwucheid + "---" + guigeid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid, shuliang, dianpuid, gouwucheid, guigeid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data", data + "---");
                        for (int i = 0; i < mlist.size(); i++) {
                            if (spid.equals(mlist.get(i).getCommodity_id())) {
                                mlist.get(i).setShoppingId(data);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        ToastUtil.showToast("添加购物车成功");

                    }
                });
    }
}
