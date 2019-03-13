package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShangpinidAndDianpuidBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouDanAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.CaiGouDanHeDanDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.SiGeXuanXiangDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/8/1/001.
 */

public class CaiGouDanActivity extends BaseActivity {
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
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.bt_hedan)
    Button btHedan;
    @BindView(R.id.rl_hedan)
    RelativeLayout rlHedan;
    @BindView(R.id.iv_quanxuan)
    ImageView ivQuanxuan;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.ll)
    LinearLayout ll;

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
    private HashMap<String, CaiGouDanBean> xuanzhong = new HashMap<>();
    private boolean isBack;
    private boolean isSelect;
    private List<CaiGouDanBean> mList = new ArrayList<>();
    private String hedanName = "";
    private String purchase_id = "";
    private String ct_buy_final_id = "";
    private int mytype = 0;
    private int ye=1;
    private String type = "902";
    //    private Date dangqianTime;
    @Override
    public int getLayoutId() {
        return R.layout.activity_wodecaigoudan;
    }

    @Override
    protected void initData() {
        mContext = CaiGouDanActivity.this;
        tvRight.setText("取消");
        tvRight.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        tvRight.setVisibility(View.GONE);
        initAdapter();
        if(StringUtil.isValid(getIntent().getStringExtra("type"))){
            getHedanList("0",getIntent().getStringExtra("type"),1);
        } else {
            getHedanList("0","902",1);
        }
        initdialog();
    }

    private void initAdapter() {

        adapter = new CaiGouDanAdapter(getResources());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final CaiGouDanBean item = (CaiGouDanBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.bt_xiangqing:
//                        if(item.getOrder_audit_state().equals("902")){
                        if(StringUtil.isValid(item.getQdTime())){
                            if (!isCanClick(item)) {
                                return;
                            }
                        }

                        //审批页
                        Intent intent = new Intent(CaiGouDanActivity.this, ShenPiActivity.class);
                        if (item.getOrder_audit_state().equals("901")) {
                            intent.setClass(CaiGouDanActivity.this, ShenPiChengGongActivity.class);
                            intent.putExtra("id", item.getPurchase_id());
                            intent.putExtra("caigouming",item.getPurchase_name());
                        } else if (item.getOrder_audit_state().equals("903")) {
                            intent.setClass(CaiGouDanActivity.this, ShenPiShiBaiActivity.class);
                            intent.putExtra("id", item.getPurchase_id());
                            intent.putExtra("caigouming",item.getPurchase_name());
                        } else if(item.getOrder_audit_state().equals("904")){
                            intent.setClass(CaiGouDanActivity.this, CaiGouXuQiuActivity.class);
                            intent.putExtra("id", item.getPurchase_id());
                            intent.putExtra("caigouming",item.getPurchase_name());
                        }
                        String data1 = gson.toJson(item);
                        if(StringUtil.isValid(item.getCt_buy_final_id())){
                            intent.putExtra("ct_buy_final_id",item.getCt_buy_final_id());
                        } else {
                            intent.putExtra("purchase_id",item.getPurchase_id());
                            intent.putExtra("caigouming",item.getPurchase_name());
                        }
                        intent.putExtra("data", data1);
                        startActivity(intent);
                        finish();
//                        }
                        break;
                    case R.id.iv_xuanzhong://是否选中
                        if(mList.get(position).isSelect()){
                            delItem(mList.get(position));
                        } else {
                            addItem(mList.get(position));
                        }
                        mList.get(position).setSelect(!mList.get(position).isSelect());
                        adapter.refreshNotifyItemChanged(position);
                        ivQuanxuan.setSelected(setXuanzhong());
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
                deleteItem.setBackground(R.color.red_ff3300);
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
                if(item.getOrder_audit_state().equals("903")||item.getOrder_audit_state().equals("904")){
                    HttpManager.getInstance()
                            .with(mContext)
                            .setObservable(
                                    RetrofitManager
                                            .getService()
                                            .delxuqiudan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), item.getPurchase_id()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    adapter.remove(adapterPosition);
                                    int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                                }
                            });
                } else {
                    ToastUtil.showToastLong("此状态不可删除");
                }


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
                getHedanList("0",type,0);
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

//    private void setShenPiShiBai(String yuanyin, String purchase_id) {
//        HttpManager.getInstance()
//                .with(mContext)
//                .setObservable(
//                        RetrofitManager
//                                .getService()
//                                .shenpishibai(PreferenceUtils.getString(MyApplication.mContext, "token", ""), yuanyin, purchase_id))
//                .setDataListener(new HttpDataListener<String>() {
//                    @Override
//                    public void onNext(String data) {
//                        getHedanList("0","902");
//                    }
//                });
//    }


    private void initdialog() {
        titleDialog = new SiGeXuanXiangDialog().init("待提交", "审核通过", "审核不通过", "待审核")
                .setActivity(this)
                .setTop(AppUtil.dip2px(44));

        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));

    }


    @OnClick({R.id.ll_title, R.id.iv_back, R.id.tv_right, R.id.bt_hedan, R.id.bt_chaifen,R.id.iv_quanxuan, R.id.tv_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title:
                titleDialog.show(getSupportFragmentManager());
                break;
            case R.id.iv_back:
//                myBack();
                finish();
                break;
            case R.id.tv_right:
                tvRight.setVisibility(View.GONE);
                rlHedan.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
                adapter.setShow(false);
                isSelect = false;
                ivQuanxuan.setSelected(isSelect);
                for(int i=0;i<mList.size();i++){
                    mList.get(i).setSelect(false);
                    adapter.refreshNotifyItemChanged(i);
                }
                getHedanList("2","902",1);
                break;
            case R.id.bt_hedan:
                isBack = true;
                mytype =0;
                xuanzhong.clear();
                tvTijiao.setText("合单");
                getHedanList("2","902",1);
                ll.setVisibility(View.GONE);
                rlHedan.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_chaifen:
                isBack = true;
                mytype =1;
                xuanzhong.clear();
                tvTijiao.setText("拆分");
                getHedanList("1","902",1);
                ll.setVisibility(View.GONE);
                rlHedan.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_quanxuan:
                if(isSelect){
                        for(int i=0;i<mList.size();i++){
                            delItem(mList.get(i));
                            mList.get(i).setSelect(false);
                            adapter.refreshNotifyItemChanged(i);
                        }
                } else {
                    for(int i=0;i<mList.size();i++){
                        addItem(mList.get(i));
                        mList.get(i).setSelect(true);
                        adapter.refreshNotifyItemChanged(i);
                    }
                }
                if(mytype==0){

                }
                isSelect = !isSelect;
                ivQuanxuan.setSelected(isSelect);
                break;
            case R.id.tv_tijiao:
                getPurchase();
                break;
        }
    }


    public static boolean isCanClick(CaiGouDanBean item) {
        Date dangqianTime = new Date();
        Log.e( "isCanClick: ", item.getQdTime());
//        int size = item.getFllist()==null?0:item.getFllist().size();
//        for (int i1 = 0; i1 < size; i1++) {
//            Date createTime = DateUtil.StrToDate(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
//            long fen = DateUtil.dqsj(createTime, dangqianTime, "3");
//                    if (fen<5){
//                        ToastUtil.showToast("商家正在抢单中，请抢单结束后再点击。距离抢单结束还有"+(5-fen)+"分钟。");
//                        return false;
//                    }else{
            if (!TextUtils.isEmpty(String.valueOf(item.getQdTime()))) {
                Date createTime = DateUtil.StrToDate(item.getQdTime(), "yyyy-MM-dd HH:mm:ss");
                long fen = DateUtil.dqsj(createTime, dangqianTime, "3");
                if (fen < 5) {
                    ToastUtil.showToastLong("商家正在抢单中，请抢单结束后再点击。距离抢单结束还有" + (5 - fen) + "分钟。");
                    return false;
                } else {
                    return true;
                }
            }
            else {
                return true;
            }


//                    }
//        }
//        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ye!=1){
            Log.e(TAG, "onResume: "+"啊啊啊" );
            tvTitle.setText("待审核");
            getHedanList("0","902",1);

        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        myBack();
        finish();
    }

//    private void myBack() {
//        if (isBack) {
//            isBack = false;
//            btHedan.setVisibility(View.VISIBLE);
//            rlHedan.setVisibility(View.GONE);
//        } else {
//            finish();
//        }
//    }

    public void addItem(CaiGouDanBean id) {
        if(mytype==0){
            xuanzhong.put(id.getPurchase_id(), id);
        } else {
            xuanzhong.put(id.getCt_buy_final_id(), id);
        }

    }

    public void delItem(CaiGouDanBean id) {
        if(mytype==0){
            xuanzhong.remove(id.getPurchase_id());
        } else {
            xuanzhong.remove(id.getCt_buy_final_id());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void getHedanList(final String status,final String isState,final int myye) {
        if(myye==1){
            ye = myye;
        }
        type = isState;
        rl.setVisibility(View.GONE);
        if(isState.equals("902")){
            tvTitle.setText("待审核");
            rl.setVisibility(View.VISIBLE);
        } else if(isState.equals("901")){
            tvTitle.setText("审核通过");
        } else if(isState.equals("903")){
            tvTitle.setText("审核失败");
        } else {
            tvTitle.setText("待提交");
        }
        Log.e(TAG, "getHedanList: "+isState );
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getHedanList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), status,isState,ye+""))
                .setDataListener(new HttpDataListener<List<CaiGouDanBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean> list) {
                        String data = gson.toJson(list);
                        Log.e(TAG, data);
                        mList.clear();
                        mList.addAll(list);
                        adapter.setNewData(list);
                        if (list.size() == 5) {
                            rvCaigoudan.loadMoreFinish(false, true);
                        } else if (list.size() > 0) {
                            rvCaigoudan.loadMoreFinish(false, false);
                        } else {
                            rvCaigoudan.loadMoreFinish(true, false);
                        }
                        adapter.notifyDataSetChanged();
                        if(status.equals("0")){
                            xuanzhong.clear();
                            ll.setVisibility(View.VISIBLE);
                            rlHedan.setVisibility(View.GONE);
                            adapter.setShow(false);
                            tvRight.setVisibility(View.GONE);
                            isSelect = false;
                            ivQuanxuan.setSelected(false);
                        } else {
                            adapter.setShow(true);
                        }
                        ye++;
                    }
                });
    }

    public void getPurchase() {//存储点击item,计算总价
        int count = 0;
        Set<String> mapkey = xuanzhong.keySet();
        if(mytype==0){
            for (String key : mapkey) {
                CaiGouDanBean value = xuanzhong.get(key);
                if (value.getPurchase_id().isEmpty()) {//没选中的不拼   避免有多余的,
                } else {
                    purchase_id += key + ",";
                    count++;
                    Log.e("我的主表ID"+count,purchase_id);
                }
            }
        } else {
            for (String key : mapkey) {
                CaiGouDanBean value = xuanzhong.get(key);
                if (value.getCt_buy_final_id().isEmpty()) {//没选中的不拼   避免有多余的,
                } else {
                    ct_buy_final_id += key + ",";
                    count++;
                }
            }
        }

        if(mytype==0){
            if (count >1) {
                purchase_id = purchase_id.substring(0, purchase_id.length() - 1);
                Log.e("我的主表ID",purchase_id);
                CaiGouDanHeDanDailog dialog = new CaiGouDanHeDanDailog(mContext, new CaiGouDanHeDanDailog.CallBack() {
                    @Override
                    public void confirm(String msg) {
                        hedanName = msg;
                        caigouHedan(msg);
                    }
                });
                dialog.show();

                //调用接口合单
            } else {
                ToastUtil.showToast("请至少选择2项合单");

            }
        } else {
            if(count!=0){
                ct_buy_final_id = ct_buy_final_id.substring(0, ct_buy_final_id.length() - 1);
                //调用接口拆分
                caigouChaidan();
            } else {
                ToastUtil.showToast("请至少选择1项拆分");

            }
        }
    }

    public void caigouHedan(String name) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .caigouHedan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),purchase_id,name))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        purchase_id = "";
                        ToastUtil.showToast("合单成功");
                        getHedanList("0","902",1);
//                        setShow();
                    }
                });
    }

    public void caigouChaidan() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .caigouChaidan(PreferenceUtils.getString(MyApplication.mContext, "token", ""),ct_buy_final_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ct_buy_final_id = "";
                        ToastUtil.showToast("拆单成功");
                        getHedanList("0","902",1);
//                        setShow();
                    }
                });
    }
    private boolean setXuanzhong(){
        for(int i=0;i<mList.size();i++){
            if(!mList.get(i).isSelect()){
                isSelect = false;
                return false;
            }
        }
        isSelect = true;
        return true;
    }
    private void setShow(){
        xuanzhong.clear();
        ll.setVisibility(View.VISIBLE);
        rlHedan.setVisibility(View.GONE);
        adapter.setShow(false);
        isSelect = false;
        ivQuanxuan.setSelected(false);
    }
}
