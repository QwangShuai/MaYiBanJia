package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouDanAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShenPiShiBaiDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SiGeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/8/1/001.
 */

public class CaiGouDanActivity extends BaseActivity{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_caigoudan)
    SwipeMenuRecyclerView rvCaigoudan;
    private Context mContext;
    private CaiGouDanAdapter adapter;
    private SiGeXuanXiangDialog titleDialog;
    private String shibaiyuanyin;
    private ConfirmDialog confirmDialog;
    private String lujingtype;
    private String shopping_id;
    private String son_order_id;
    private String company_id;
    private String commodity_id;
    private boolean canClick;
    private String TAG = "CaiGouDanActivity";
//    private Date dangqianTime;
    @Override
    public int getLayoutId() {
        return R.layout.activity_wodecaigoudan;
    }

    @Override
    protected void initData() {
        mContext = CaiGouDanActivity.this;
        initAdapter();
        initdialog();
        getlist("");

    }

    private void initAdapter(){

        adapter = new CaiGouDanAdapter(getResources());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final CaiGouDanBean item = (CaiGouDanBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.bt_xiangqing:
//                        if(item.getOrder_audit_state().equals("902")){
                            if (!isCanClick(item)) {
                                return;
                            }
                            //审批页
                            Intent intent = new Intent(CaiGouDanActivity.this, ShenPiActivity.class);
                            String data1 = gson.toJson(item);
//                            String data1 = ((CaiGouDanBean) adapter.getItem(position)).getPurchase_id();
//                            String data1 = item.getPurchase_id();
                            Log.e(TAG+"1",data1);
                            intent.putExtra("data", data1);
                            startActivity(intent);
                            finish();
//                        }
                        break;
                    case R.id.bt_tongguo:
                        if(item.getOrder_audit_state().equals("902")) {
                            if (!isCanClick(item)) {
                                return;
                            }
                            if (item.getType() == 0) {
                                ToastUtil.showToast("请选择商品。。。。。");
                                return;
                            }
                            //提交订单
                            confirmDialog.showDialog("确认审批通过即发布商品成功");
                            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (item.getZongjia() == null || item.getZongjia().isEmpty()) {
                                        ToastUtil.showToast("未选择相应商品，不能提交");
                                    } else {
                                        Intent intent = new Intent(mContext, QueRenDingDanActivity.class);
                                        intent.putExtra("son_order_id", item.getSon_order_id());
                                        intent.putExtra("commodity_id", item.getCommodity_id());
                                        intent.putExtra("lujingtype", "2");
                                        intent.putExtra("company_id", item.getCompany_id());
                                        intent.putExtra("zongjia", item.getZongjia());
                                        startActivity(intent);
                                        confirmDialog.dismiss();
                                    }
                                    Log.e("getPurchase_id", item.getPurchase_id() + "==");
                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });
                        }
                        break;
                    case R.id.bt_shibai:
                        if(item.getOrder_audit_state().equals("902")) {
                            if (!isCanClick(item)) {
                                return;
                            }
                            //审批失败
                            new ShenPiShiBaiDailog()
                                    .setInitStr(shibaiyuanyin)
                                    .setCallBack(new ShenPiShiBaiDailog.CallBack() {
                                        @Override
                                        public void confirm(String msg) {
                                            shibaiyuanyin = msg;
                                            setShenPiShiBai(shibaiyuanyin, item.getPurchase_id());
                                        }
                                    }).show(getSupportFragmentManager());
                        }
                        break;

                }
            }
        });
        rvCaigoudan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

// 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu rightMenuleftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
                deleteItem.setText("删除");
                deleteItem.setTextColor(getResources().getColor(R.color.white));
                deleteItem.setTextSize(15);
                deleteItem.setHeight(MATCH_PARENT);
                deleteItem.setWidth(getWindowManager().getDefaultDisplay().getWidth() * 1 / 6);
                deleteItem.setBackground(R.color.mayihong);
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

//                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//                leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(final SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                final int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                final CaiGouDanBean item = (CaiGouDanBean) adapter.getItem(adapterPosition);
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .delxuqiudan(PreferenceUtils.getString(MyApplication.mContext, "token",""),item.getPurchase_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                adapter.remove(adapterPosition);
                                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                            }
                        });

            }
        };
        SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                Log.e("jiazaigengduo", "jiazaigengduo");
                // 请求数据，并更新数据源操作。
                adapter.notifyDataSetChanged();

                // 数据完更多数据，一定要调用这个方法。
                // 第一个参数：表示此次数据是否为空。
                // 第二个参数：表示是否还有更多数据。
                rvCaigoudan.loadMoreFinish(false, true);

                // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                // errorMessage是会显示到loadMoreView上的，用户可以看到。
                // mRecyclerView.loadMoreError(0, "请求网络失败");
            }
        };
        // 设置监听器。
        rvCaigoudan.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        rvCaigoudan.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvCaigoudan.useDefaultLoadMore(); // 使用默认的加载更多的View。
        rvCaigoudan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        rvCaigoudan.setAdapter(adapter);
    }

    private void setShenPiShiBai(String yuanyin,String purchase_id) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpishibai(PreferenceUtils.getString(MyApplication.mContext, "token",""),yuanyin,purchase_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        getlist("");
//                        Log.e("data",data+"---");
                    }
                });
    }




    private void initdialog(){
        titleDialog = new SiGeXuanXiangDialog().init("全部采购单", "审核通过", "审核不通过", "待审核")
                .setActivity(this)
                .setTop(AppUtil.dip2px(44));

        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));

    }
    //采购单列表
    public  void getlist(String status) {
        Log.e("status",status);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getcaigoudanlist(PreferenceUtils.getString(MyApplication.mContext, "token",""),status ))
                .setDataListener(new HttpDataListener<List<CaiGouDanBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean> list) {
                        String data = gson.toJson(list);
                        Log.e(TAG,data);
                        adapter.setNewData(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    @OnClick({R.id.ll_title, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title:
                titleDialog.show(getSupportFragmentManager());
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    public static boolean isCanClick(CaiGouDanBean item) {
        Date dangqianTime = new Date();
            for (int i1 = 0; i1 < item.getList().size(); i1++) {
                if (item.getList().get(i1).isSpecial()){
//                    Log.e("1122",String.valueOf(item.getList().get(i1).getCreate_time()));
                    Date createTime = DateUtil.StrToDate(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss");

                    long fen = DateUtil.dqsj(createTime, dangqianTime, "3");
                    if (fen<5){
                        ToastUtil.showToast("商家正在抢单中，请抢单结束后再点击。距离抢单结束还有"+(5-fen)+"分钟。");
                        return false;
                    }else{
                        if (!TextUtils.isEmpty(String.valueOf(item.getQdTime()))){
                            createTime = DateUtil.StrToDate(item.getQdTime(), "yyyy-MM-dd HH:mm:ss");
                            fen = DateUtil.dqsj(createTime, dangqianTime, "3");
                            if (fen<5){
                                ToastUtil.showToast("商家正在抢单中，请抢单结束后再点击。距离抢单结束还有"+(5-fen)+"分钟。");
                                return false;
                            }else{
                                return true;
                            }
                        }else{
                            return true;
                        }


                    }
                }
            }

        return true;
    }
}
