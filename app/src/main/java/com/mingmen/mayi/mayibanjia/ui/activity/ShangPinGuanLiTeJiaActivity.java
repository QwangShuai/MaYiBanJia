package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinGuanLiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinGuanLiTeJiaAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SanGeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShangPinTeJiaDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SouSuoDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.MATCH_PARENT;

public class ShangPinGuanLiTeJiaActivity extends BaseActivity {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.rv_shangpinguanli)
    SwipeMenuRecyclerView rvShangpinguanli;
    @BindView(R.id.ll_xinjianshangpin)
    LinearLayout llXinjianshangpin;

    private Context mContext;
    private ShangPinGuanLiTeJiaAdapter shangpinguanliadapter;
    private String chaxunzi="";
    private int ye = 1;
    private ArrayList<ShangPinGuanLiBean.GoodsListBean> mlist = new ArrayList<>();
    private String type="0";
    private String goods= "0";
    private ShangPinTeJiaDialog titleDialog;
    private boolean isClick = true;
    private String token = "";
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_shang_pin_guan_li_te_jia;
    }

    @Override
    protected void initData() {
        mContext=ShangPinGuanLiTeJiaActivity.this;
        titleDialog = new ShangPinTeJiaDialog()
                .setTeJiaActivity(this)
                .setTop(AppUtil.dip2px(44));
        goods = getIntent().getStringExtra("goods");
        setToken(getIntent().getStringExtra("token"));

        if(StringUtil.isValid(token)){
            isClick = false;
        } else {
            token = PreferenceUtils.getString(MyApplication.mContext,"token","");
        }
        initRecycleView();
//        if(goods.equals("1")){
//
//        }
        getShangpinList(1);
    }


    public void getShangpinList(final int ye) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getshangpinguanli(token, chaxunzi,goods,type,ye))
                .setDataListener(new HttpDataListener<ShangPinGuanLiBean>() {
                    @Override
                    public void onNext(ShangPinGuanLiBean data) {
                        if (ye == 1) {
                            mlist.clear();
                            rvShangpinguanli.loadMoreFinish(false, true);
                        }else{
                            if (data.getGoodsList().size()>0&&data.getGoodsList().size()<5){
                                rvShangpinguanli.loadMoreFinish(false, false);
                            }else{
                                if (data.getGoodsList().size()==0){
                                    rvShangpinguanli.loadMoreFinish(true, false);
                                }else{
                                    rvShangpinguanli.loadMoreFinish(false, true);
                                }
                            }
                        }
                        mlist.addAll(data.getGoodsList());
                        shangpinguanliadapter.setNewData(mlist);
                        shangpinguanliadapter.notifyDataSetChanged();
                    }
                },ye==1);
    }

    private void initRecycleView() {
        if(StringUtil.isValid(token)){
            // 创建菜单：
            SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
                @Override
                public void onCreateMenu(SwipeMenu rightMenuleftMenu, SwipeMenu rightMenu, int viewType) {
                    SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
                    deleteItem.setText("删除");
                    deleteItem.setTextColor(mContext.getResources().getColor(R.color.white));
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
                            .with(mContext)
                            .setObservable(
                                    RetrofitManager
                                            .getService()
                                            . ghdspdel(token, mlist.get(adapterPosition).getCommodity_id(),"3"))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    mlist.remove(adapterPosition);
                                    shangpinguanliadapter.setNewData(mlist);
                                    shangpinguanliadapter.notifyDataSetChanged();
                                }
                            },false);


                }
            };
            // 设置监听器。
            rvShangpinguanli.setSwipeMenuCreator(mSwipeMenuCreator);
            // 菜单点击监听。
            rvShangpinguanli.setSwipeMenuItemClickListener(mMenuItemClickListener);
        }


        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                ye++;
                getShangpinList(ye);
            }
        };

        rvShangpinguanli.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false));
        rvShangpinguanli.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvShangpinguanli.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvShangpinguanli.loadMoreFinish(false, true);
        rvShangpinguanli.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        shangpinguanliadapter=new ShangPinGuanLiTeJiaAdapter(ShangPinGuanLiTeJiaActivity.this,goods);
        shangpinguanliadapter.setClick(isClick);
        rvShangpinguanli.setAdapter(shangpinguanliadapter);
    }

    @OnClick({R.id.ll_title, R.id.iv_back, R.id.iv_sousuo, R.id.ll_xinjianshangpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title:
                titleDialog.show(getSupportFragmentManager());
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sousuo:
                if(isClick){
                    //搜索
                    new SouSuoDialog()
                            .setData(chaxunzi)
                            .setCallBack(new SouSuoDialog.CallBack() {
                                @Override
                                public void sousuozi(String msg) {
                                    Log.e("msg",msg+"==");
                                    chaxunzi=msg;
                                    getShangpinList(1);
                                }
                            }).show(getSupportFragmentManager());
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }

                break;
            case R.id.ll_xinjianshangpin:
                if(isClick) {
                    //添加商品
                    Intent intent = new Intent(mContext, FaBuShangPinActivity.class);
                    intent.putExtra("state", "0");
                    intent.putExtra("goods", goods);
                    startActivity(intent);
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
                break;
        }
    }

    public void setType(String s) {
        type=s;
        ye = 1;
        getShangpinList(1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setType(type);
    }
}
