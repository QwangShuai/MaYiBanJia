package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiYeLeiBieBean;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QiYeLieBiaoAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiYeLieBiaoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.QiYeSouSUoDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YeWuYuanAddDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YeWuYuanDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.SinglePicker;
import it.sephiroth.android.library.easing.Linear;

import static com.mingmen.mayi.mayibanjia.R.id.rv_yijifenlei;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class YeWuYuanMainActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shaixuan)
    TextView tvShaixuan;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_qiyeliebiao)
    SwipeMenuRecyclerView rvQiyeliebiao;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;
    private Context mContext;
    private QiYeLieBiaoAdapter adapter;
    private QiYeLieBiaoDialog bianjidialog;
    private YeWuYuanAddDialog addDialog;
    private ArrayList<QiYeLieBiaoBean> mlist = new ArrayList<>();
    private QiYeSouSUoDialog sousuodialog;
    private SinglePicker<QiYeLeiBieBean> leibiepicker;
    private String leibiename;
    private String leibieid="";
    private PopupWindow tuichupop;
    private ConfirmDialog confirmDialog;
    private int ye = 1;
    private YeWuYuanDialog dialog;
    private String type = "1";
    private String role = "2";
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;
    private long exitTime = 0;
    private boolean isOne = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_yewuyuanmain;
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的商家");
        ivSangedian.setVisibility(View.VISIBLE);
//        ivBack.setImageResource(R.mipmap.sousuo_bai);
        mContext=YeWuYuanMainActivity.this;
        mlist.clear();
        getQiyeLiebiao(type,role);
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                // 该加载更多啦。
                getQiyeLiebiao(type,role);
            }
        };
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adapter的数据源为空
                ye = 1;
                mlist.clear();
                getQiyeLiebiao(type,role);
                srlShuaxin.setRefreshing(false);
            }
        });
        adapter = new QiYeLieBiaoAdapter(mContext, mlist);
        rvQiyeliebiao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvQiyeliebiao.setLoadMoreListener(mLoadMoreListener);
        rvQiyeliebiao.setAdapter(adapter);
        adapter.setOnItemClickListener(new QiYeLieBiaoAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, final int position) {
                switch (view.getId()){
                    case R.id.ll_bianji:
                        //dialog
                        bianjidialog = new QiYeLieBiaoDialog(mContext,
                                mContext.getResources().getIdentifier("TouMingDialog", "style", mContext.getPackageName()));
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOne){
            isOne = false;
        } else {
            ye = 1;
            mlist.clear();
            getQiyeLiebiao(type,role);
        }
    }

    //查询企业列表
    public void getQiyeLiebiao(String type,String role) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getqiyeliebiao(PreferenceUtils.getString(MyApplication.mContext, "token",""),type,ye+"",role))
                .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                    @Override
                    public void onNext(final List<QiYeLieBiaoBean> data) {
                        ye++;
                        int size = data==null?0:data.size();
                        if(size!=0){
                            mlist.addAll(data);
                            if(data.size()==10){
                                rvQiyeliebiao.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQiyeliebiao.loadMoreFinish(false, false);
                            } else {
                                rvQiyeliebiao.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    //查询企业列表..带参数
    private void getQiyeLiebiaodaicanshu(String trim, String leibieid) {
        Log.e("getQiyeLiebiaodaicanshu",trim+"--"+leibieid);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getqiyedaicanshu(PreferenceUtils.getString(MyApplication.mContext, "token",""),trim,leibieid,type))
                .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                    @Override
                    public void onNext(final List<QiYeLieBiaoBean> data) {
                        int size = data==null?0:data.size();
                        if(size!=0){
                            mlist.addAll(data);
                            if(data.size()==10){
                                rvQiyeliebiao.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQiyeliebiao.loadMoreFinish(false, false);
                            } else {
                                rvQiyeliebiao.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
    //查询企业列表..带参数
    public void shuaxinList(String type,String role) {
        ye = 1;
        this.type = type;
        this.role = role;
        if(type.equals("1")&&role.equals("1")){
            tvTitle.setText("我的餐厅");
        } else if(type.equals("1")&&role.equals("2")) {
            tvTitle.setText("我的商家");
        }  else if(type.equals("2")&&role.equals("1")) {
            tvTitle.setText("全部餐厅");
        }  else if(type.equals("2")&&role.equals("2")) {
            tvTitle.setText("全部商家");
        }
        mlist.clear();
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getqiyeliebiao(PreferenceUtils.getString(MyApplication.mContext, "token",""),type,ye+"",role))
                .setDataListener(new HttpDataListener<List<QiYeLieBiaoBean>>() {
                    @Override
                    public void onNext(final List<QiYeLieBiaoBean> data) {
                        ye++;
                        int size = data==null?0:data.size();
                        if(size!=0){
                            mlist.addAll(data);
                            if(data.size()==10){
                                rvQiyeliebiao.loadMoreFinish(false, true);
                            }else if(data.size()>0){
                                rvQiyeliebiao.loadMoreFinish(false, false);
                            } else {
                                rvQiyeliebiao.loadMoreFinish(true, false);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    //删除
    private void shanchu(final int position) {
        Log.e("shanchu",position+"-");
        String id = mlist.get(position).getCompany_id();
        Log.e("id",id+"_--");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .delqiye(PreferenceUtils.getString(MyApplication.mContext, "token",""),id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        Log.e("data",data+"---");
                        mlist.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                });
    }
    //编辑
    private void bianji(int position,String type) {
        Intent intent = new Intent();
        if(type.equals("1")){
            intent.setClass(mContext, XinXiLuRuActivity.class);
        } else {
            intent.setClass(mContext, XinXiLuRuGHDActivity.class);
        }

        bundle.putString("rukou","edit");
        bundle.putString("xinxi",gson.toJson(mlist.get(position)));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick({R.id.tv_shaixuan, R.id.tv_right,R.id.iv_sangedian,R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shaixuan:
                //搜索弹出框
//dialog
                sousuodialog = new QiYeSouSUoDialog(mContext,
                        mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                sousuodialog.showDialog();
                sousuodialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = sousuodialog.getEtQiyemingcheng().getText().toString().trim();
                        Log.e("name",name+"--");
                        getQiyeLiebiaodaicanshu(name,leibieid);
                        sousuodialog.cancel();
                    }
                });
                sousuodialog.getTvQuxiao().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sousuodialog.cancel();
                    }
                });
                sousuodialog.getTvQiyeleibie().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//企业类别
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .getqylb(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                                .setDataListener(new HttpDataListener<List<QiYeLeiBieBean>>(){
                                    @Override
                                    public void onNext(List<QiYeLeiBieBean> data) {
                                        leibiepicker = new SinglePicker<>(YeWuYuanMainActivity.this, data);
                                        leibiepicker.setCanceledOnTouchOutside(false);
                                        leibiepicker.setSelectedIndex(1);
                                        leibiepicker.setCycleDisable(false);
                                        leibiepicker.setOnItemPickListener(new SinglePicker.OnItemPickListener<QiYeLeiBieBean>() {
                                            @Override
                                            public void onItemPicked(int index, QiYeLeiBieBean item) {
                                                leibiename = item.getSon_name();
                                                leibieid = item.getSon_number();
                                                sousuodialog.getTvQiyeleibie().setText(leibiename);
                                                Log.e("leibiename+leibieid", leibiename +"+"+ leibieid);
                                                leibiepicker.dismiss();
                                            }
                                        });
                                        leibiepicker.show();

                                    }
                                });
                    }

                });
                break;
            case R.id.tv_right:
                addDialog = new YeWuYuanAddDialog(mContext,
                        mContext.getResources().getIdentifier("TouMingDialog", "style", mContext.getPackageName()));
                addDialog.showDialog();
                addDialog.getLlBianji().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.dismiss();
                        //添加企业
                        Intent intent = new Intent(mContext, XinXiLuRuActivity.class);
                        bundle.putString("rukou","add");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                addDialog.getLlShanchu().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addDialog.dismiss();
                                Intent intent = new Intent(mContext, XinXiLuRuGHDActivity.class);
                                bundle.putString("rukou","add");
                                bundle.putString("random_id","1");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                addDialog.getLlHebing().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.dismiss();
                        Intent intent = new Intent(mContext, XinXiLuRuGHDActivity.class);
                        bundle.putString("rukou","add");
                        bundle.putString("random_id","0");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                addDialog.getIvGuanbi().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.cancel();
                    }
                });

                break;
            case R.id.iv_sangedian:
                showTuiChuPop();
                break;
            case R.id.tv_title:
                dialog = new YeWuYuanDialog();
                dialog.setTop(AppUtil.dip2px(44)).setActivity(YeWuYuanMainActivity.this);
                dialog.show(getSupportFragmentManager());
                break;
        }
    }

    private void showTuiChuPop() {
        View view = View.inflate(mContext, R.layout.pop_tuichu, null);
        tuichupop = new PopupWindow(view);

        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        tuichupop.setWidth(AppUtil.dip2px(130));
        tuichupop.setHeight(AppUtil.dip2px(50));
        LinearLayout ll_tuichu = view.findViewById(R.id.ll_tuichu);
        ll_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitLogin();
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
        tuichupop.setOutsideTouchable(true);
        tuichupop.setBackgroundDrawable(new BitmapDrawable());
        tuichupop.showAsDropDown(ivSangedian);
    }

    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        PreferenceUtils.putBoolean(MyApplication.mContext,"isLogin",false);
                        PreferenceUtils.clear(MyApplication.mContext);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        confirmDialog.dismiss();
                        tuichupop.dismiss();
                        AppManager.getAppManager().finishAllActivity();
                    }
                });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exit();
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(mContext);
        }
    }
}
