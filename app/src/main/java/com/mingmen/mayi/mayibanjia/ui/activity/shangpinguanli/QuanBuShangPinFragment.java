package com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinGuanLiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class QuanBuShangPinFragment extends BaseFragment {

    @BindView(R.id.rv_dingdan)
    SwipeRecyclerView rvShangpinguanli;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    View view;

    private boolean isShow;
    private String chaxunzi = "";
    private ArrayList<ShangPinGuanLiBean.GoodsListBean> mlist = new ArrayList<>();
    private String goods = "0";
    private ShangPinGuanLiAdapter shangpinguanliadapter;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private int ye = 1;
    private boolean b = false;
    protected boolean isCreate = false;
    private String token = "";
    private boolean isClick = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    protected View getSuccessView() {
        view = View.inflate(MyApplication.mContext, R.layout.fragment_shangpinguanli, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void loadData() {
        stateLayout.showSuccessView();
        initview();
        getData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //数据
    private void getData() {
        Log.e("getData: ", getZhuangTai());
        HttpManager.getInstance()
                .with(getContext())
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshangpinguanli(token, chaxunzi, goods, getZhuangTai(), ye))
                .setDataListener(new HttpDataListener<ShangPinGuanLiBean>() {
                    @Override
                    public void onNext(ShangPinGuanLiBean data) {
                        chaxunzi = "";
                        if (ye == 1) {
                            mlist.clear();
                            shangpinguanliadapter.notifyDataSetChanged();
                            rvShangpinguanli.loadMoreFinish(false, true);
                        } else {
                            if (data.getGoodsList().size() > 0 && data.getGoodsList().size() < 5) {
                                rvShangpinguanli.loadMoreFinish(false, false);
                            } else {
                                if (data.getGoodsList().size() == 0) {
                                    rvShangpinguanli.loadMoreFinish(true, false);
                                } else {
                                    rvShangpinguanli.loadMoreFinish(false, true);
                                }
                            }
                        }
                        mlist.addAll(data.getGoodsList());
                        shangpinguanliadapter.notifyDataSetChanged();
                        ye++;
                    }

                });
    }

    private void initview() {
        ShangPinGuanLiActivity activity = (ShangPinGuanLiActivity) getActivity();
        if (StringUtil.isValid(token)) {
            isClick = false;
        } else {
            token = PreferenceUtils.getString(MyApplication.mContext, "token", "");
            // 创建菜单：
            SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
                @Override
                public void onCreateMenu(SwipeMenu rightMenuleftMenu, SwipeMenu rightMenu, int viewType) {
                    SwipeMenuItem deleteItem = new SwipeMenuItem(getContext()); // 各种文字和图标属性设置。
                    deleteItem.setText("删除");
                    deleteItem.setTextColor(getContext().getResources().getColor(R.color.white));
                    deleteItem.setTextSize(15);
                    deleteItem.setHeight(MATCH_PARENT);
                    deleteItem.setWidth(200);
                    deleteItem.setBackground(R.color.red_ff3300);
                    rightMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
                }
            };
            SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
                @Override
                public void onItemClick(SwipeMenuBridge menuBridge) {
                    // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                    menuBridge.closeMenu();
                    int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                    final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                    int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。


                    //左滑删除
                    HttpManager.getInstance()
                            .with(getContext())
                            .setObservable(
                                    RetrofitManager
                                            .getService()
                                            .ghdspdel(token, mlist.get(adapterPosition).getCommodity_id(), "3"))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    mlist.remove(adapterPosition);
                                    shangpinguanliadapter.notifyDataSetChanged();
                                }
                            }, false);


                }
            };
            // 设置监听器。
            rvShangpinguanli.setSwipeMenuCreator(mSwipeMenuCreator);
            // 菜单点击监听。
            rvShangpinguanli.setSwipeMenuItemClickListener(mMenuItemClickListener);
        }
//        if(StringUtil.isValid(token)){

//        }


        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                getData();
            }
        };

        rvShangpinguanli.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvShangpinguanli.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpinguanli.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpinguanli.loadMoreFinish(false, true);
        shangpinguanliadapter = new ShangPinGuanLiAdapter(getContext(), goods, mlist);
        shangpinguanliadapter.setClick(isClick);
        rvShangpinguanli.setAdapter(shangpinguanliadapter);
        activity.setCallBack(new ShangPinGuanLiActivity.CallBack() {
            @Override
            public void setMsg(String msg) {
                chaxunzi = msg;
                ye = 1;
                mlist.clear();
                shangpinguanliadapter.notifyDataSetChanged();
//                if(isShow){
                getData();
//                }
            }
        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("setUserVisibleHint:yes ", getZhuangTai());
        } else {
            Log.e("setUserVisibleHint:no ", getZhuangTai());
        }
        isShow = isVisibleToUser;
    }


    public void onResume() {
        super.onResume();
        if (ye != 1) {
            ye = 1;
            mlist.clear();
            shangpinguanliadapter.notifyDataSetChanged();
//        updateList(true);
            getData();

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            Log.e("onHiddenChanged: ", "不可见" + getZhuangTai());

        } else {
            Log.e("onHiddenChanged: ", "当前可见" + getZhuangTai());
        }
    }
    public String getZhuangTai() {
        return "";
    }

}
