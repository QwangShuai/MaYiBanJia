package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheDianpuBean;
import com.mingmen.mayi.mayibanjia.bean.TuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.QueRenDingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter.GouWuCheAdapter;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class GouWuCheFragment extends BaseFragment {

    @BindView(R.id.tv_guanli)
    TextView tvGuanli;
    @BindView(R.id.iv_quanxuan)
    ImageView ivQuanxuan;
    @BindView(R.id.rv_gouwuche)
    RecyclerView rvGouwuche;
    @BindView(R.id.ll_quanxuan)
    LinearLayout llQuanxuan;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    @BindView(R.id.rl_zhengchang)
    RelativeLayout rlZhengchang;
    @BindView(R.id.rl_dibu)
    RelativeLayout rlDibu;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.tv_shanchu)
    TextView tvShanchu;
    @BindView(R.id.rl_guanli)
    LinearLayout rlGuanli;
    @BindView(R.id.srl_shuaxin)
    SwipeRefreshLayout srlShuaxin;
    private View viewSPYXFragment;
    private Context mContext;

    public boolean isGuanli() {
        return isGuanli;
    }

    private boolean isGuanli;
    private GouWuCheAdapter adapter;
    private ArrayList<String> weinituijian;
    private Gson gson=new Gson();
    private boolean isSelect=false;
    private List<GouwucheDianpuBean> gwcdianpushangpin = new ArrayList<>();
    private Map<String,String> selectedId;
    private String zongjia;

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_gouwuche, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {
        mContext = getActivity();
        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.beijing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }
        getGouWuChe(true);
        selectedId=new HashMap<>();
        srlShuaxin.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);
        srlShuaxin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 设置可见
                srlShuaxin.setRefreshing(true);
                // 重置adapter的数据源为空
                // 获取第第0条到第PAGE_COUNT（值为10）条的数据
                gwcdianpushangpin.clear();
                getGouWuChe(true);
                quanxuanlist(false);
                ivQuanxuan.setSelected(false);
                tvZongjia.setText("0");
                selectedId.clear();
                srlShuaxin.setRefreshing(false);
            }
        });
        weinituijian = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            weinituijian.add("i"+i);
        }
    }

    public void setShuaxin(){
        tvZongjia.setText("0");
        selectedId.clear();
        isGuanli = false;
        isSelect = false;
        rlGuanli.setVisibility(View.GONE);
        rlZhengchang.setVisibility(View.VISIBLE);
        ivQuanxuan.setSelected(isSelect);
        getGouWuChe(true);
    }
    @Override
    public void onResume() {
        setShuaxin();
        super.onResume();
    }

    public ImageView getIvQuanxuan() {
        return ivQuanxuan;
    }

    public void setIvQuanxuan(ImageView ivQuanxuan) {
        this.ivQuanxuan = ivQuanxuan;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    //查询购物车
    public void getGouWuChe(boolean isxianshi) {
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getgouwuche(PreferenceUtils.getString(MyApplication.mContext, "token",""),"1"))
                    .setDataListener(new HttpDataListener<GouwucheBean>() {


                        @Override
                        public void onNext(GouwucheBean data) {
                            int mysize = data.getDianpu()==null?0:data.getDianpu().size();
                            if (mysize==0){
                                rlDibu.setVisibility(View.GONE);
                                tvGuanli.setText("");
                            }else{
                                rlDibu.setVisibility(View.VISIBLE);
                                tvGuanli.setText("管理");
                                gwcdianpushangpin.addAll(data.getDianpu());
                                adapter.notifyDataSetChanged();
                            }
                            getweinituijian();
                        }
                    },isxianshi);
    }

    private void getweinituijian() {
     HttpManager.getInstance()
             .with(mContext)
                    .setObservable(
                RetrofitManager
                        .getService()
                        .getweinituijian(PreferenceUtils.getString(MyApplication.mContext, "token",""),"601"))
                .setDataListener(new HttpDataListener<List<TuiJianBean>>() {
            @Override
            public void onNext(List<TuiJianBean> data) {
                adapter = new GouWuCheAdapter(getActivity(), gwcdianpushangpin,data,GouWuCheFragment.this);
                rvGouwuche.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                rvGouwuche.setAdapter(adapter);

            }
        },false);
    }


    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @OnClick({R.id.tv_guanli, R.id.ll_quanxuan, R.id.tv_jiesuan,  R.id.tv_shoucang, R.id.tv_shanchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_guanli:
                if (isGuanli){
                    isGuanli=false;
                    rlGuanli.setVisibility(View.GONE);
                    rlZhengchang.setVisibility(View.VISIBLE);
                    tvGuanli.setText("管理");
                }else{
                    isGuanli=true;
                    rlGuanli.setVisibility(View.VISIBLE);
                    rlZhengchang.setVisibility(View.GONE);
                    tvGuanli.setText("完成");
                }
                break;
            case R.id.ll_quanxuan:
                if (isSelect){
//                    adapter.allCheck(false);
                    ivQuanxuan.setSelected(false);
                    tvZongjia.setText("0");
                    selectedId.clear();
                    quanxuanlist(false);
                }else{
//                    adapter.allCheck(true);
                    ivQuanxuan.setSelected(true);
                    quanxuanlist(true);
                    if(!isGuanli){
                        getZongJia();
                    }
                }
                isSelect=!isSelect;
                break;
            case R.id.tv_jiesuan:
                if (selectedId.size()==0){
                    ToastUtil.showToast("请选择商品后再结算");
                    return;
                }
                String shooping_id="";
                String company_id="";
                Intent intent=new Intent(mContext, QueRenDingDanActivity.class);
                Set<String> keys = selectedId.keySet();
                for (String key:keys) {
                    String value = selectedId.get(key);
                    company_id = company_id +value+",";
                    shooping_id = shooping_id +key+",";
                }
                intent.putExtra("shopping_id", shooping_id);
                intent.putExtra("company_id", company_id);
                intent.putExtra("lujingtype", "1");
                intent.putExtra("zongjia",zongjia);
                startActivity(intent);
                break;
            case R.id.tv_shoucang:
                if (selectedId.size()==0){
                    ToastUtil.showToast("请选择商品后再收藏");
                    return;
                }
                shoucang();
                break;
            case R.id.tv_shanchu:
                if (selectedId.size()==0){
                    ToastUtil.showToast("请选择商品后再删除");
                    return;
                }
                delGouwucheList();
                break;
        }
    }

    //
    //获取选中的id
    public void getalllist(){
        selectedId.clear();
        for (int i = 0; i < gwcdianpushangpin.size(); i++) {
            for (int j = 0; j < gwcdianpushangpin.get(i).getSplist().size(); j++) {
                if (gwcdianpushangpin.get(i).getSplist().get(j).isSelect()){
                    selectedId.put(gwcdianpushangpin.get(i).getSplist().get(j).getShopping_id(),gwcdianpushangpin.get(i).getSplist().get(j).getCompany_id());
                }
            }
        }
    }

    //全选
    private void quanxuanlist(boolean isquanxuan) {
        selectedId.clear();
        for (int i = 0; i < gwcdianpushangpin.size(); i++) {
            for (int j = 0; j < gwcdianpushangpin.get(i).getSplist().size(); j++) {
                if(isGuanli){
                    if (isquanxuan){
                        selectedId.put(gwcdianpushangpin.get(i).getSplist().get(j).getShopping_id(),gwcdianpushangpin.get(i).getSplist().get(j).getCompany_id());
                    }
                    gwcdianpushangpin.get(i).getSplist().get(j).setSelect(isquanxuan);
                } else {
                    if (isquanxuan){
                        if(!gwcdianpushangpin.get(i).getSplist().get(j).getCommodity_state().equals("1")){
                            selectedId.put(gwcdianpushangpin.get(i).getSplist().get(j).getShopping_id(),gwcdianpushangpin.get(i).getSplist().get(j).getCompany_id());
                        }
                    }
                    if(!gwcdianpushangpin.get(i).getSplist().get(j).getCommodity_state().equals("1")){
                        gwcdianpushangpin.get(i).getSplist().get(j).setSelect(isquanxuan);
                    }
                }


            }

        }

    }

    //查询选中商品的总价
    public  void getZongJia(){
        String id="";
        for (String key:selectedId.keySet()) {
            id+=key+",";
        }
        if ("".equals(id)){
            tvZongjia.setText("0");
            selectedId.clear();
            return;
        }
        id=id.substring(0 ,id.length()-1);
        HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    .getgwcmoney(PreferenceUtils.getString(MyApplication.mContext, "token",""),id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            zongjia = data;
                            tvZongjia.setText(data);
                            tvJiesuan.setEnabled(true);
                        }
                    });
    }

    private void delGouwucheList(){
        String id = "";
        for (String myid : selectedId.keySet()){
            id+=","+myid;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .delgwc(PreferenceUtils.getString(MyApplication.mContext, "token",""),"2",id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        setShuaxin();
                    }
                },false);
    }
    public void allCheck(boolean b,int pos){
        if(isGuanli){
            for (int j=0;j<gwcdianpushangpin.get(pos).getSplist().size();j++){
                gwcdianpushangpin.get(pos).getSplist().get(j).setSelect(b);
            }
        } else {
            for (int j=0;j<gwcdianpushangpin.get(pos).getSplist().size();j++){
                if(!gwcdianpushangpin.get(pos).getSplist().get(j).getCommodity_state().equals("1")){
                    gwcdianpushangpin.get(pos).getSplist().get(j).setSelect(b);
                } else {
                    ToastUtil.showToastLong("请及时删除下架商品");
                }

            }
        }

        adapter.notifyDataSetChanged();
    }

    //收藏此商品
    private void shoucang() {
        String id = "";
        for (String myid : selectedId.keySet()){
            id+=","+myid;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shoucang(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id,""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("收藏成功");
                        setShuaxin();
                    }
                });
    }
}
