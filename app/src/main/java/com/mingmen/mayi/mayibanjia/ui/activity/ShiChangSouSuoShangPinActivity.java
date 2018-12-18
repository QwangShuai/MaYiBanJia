package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/12.
 */

public class ShiChangSouSuoShangPinActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sousuozi)
    TextView tvSousuozi;
    @BindView(R.id.ll_sousuo)
    LinearLayout llSousuo;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.ll_jiage)
    LinearLayout llJiage;
    @BindView(R.id.tv_pingfenzuigao)
    TextView tvPingfenzuigao;
    @BindView(R.id.rv_shichangshangpin)
    RecyclerView rvShichangshangpin;

    private Context mContext;
    private boolean isdi;
    private ArrayList<ShangPinSouSuoBean.ZhengchangBean> shangpinlist;
    private ShangPinListAdapter shangpinadapter;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private String type_tree_id;
    private String son_number;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shichangsousuo;
    }

    @Override
    protected void initData() {
        mContext=ShiChangSouSuoShangPinActivity.this;
        type_tree_id = getIntent().getStringExtra("type_tree_id");
        son_number =getIntent().getStringExtra("son_number");
        Log.e("type_tree_id",type_tree_id+"==");
        Log.e("son_number",son_number+"===");
        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        sousuoshangpin("0");
    }

    //商品搜索
    private void sousuoshangpin(final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shichangsousuoshangpin(type_tree_id, son_number,type))
                .setDataListener(new HttpDataListener<ShangPinSouSuoBean>() {
                    @Override
                    public void onNext(final ShangPinSouSuoBean shangpin) {
                        Log.e("sousuoshangpin", "sousuoshangpin");
                        if ("3".equals(type)){
                            isdi =true;
                        }else if ("4".equals(type)){
                            isdi =false;
                        }
                        shangpinlist = new ArrayList<>();
                        shangpinlist.addAll(shangpin.getZhengchang());
                        shangpinadapter = new ShangPinListAdapter(mContext, shangpinlist);
                        rvShichangshangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rvShichangshangpin.setAdapter(shangpinadapter);
                        shangpinadapter.setOnItemClickListener(new ShangPinListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_addcar:
                                        //添加购物车
                                        final ShangPinSouSuoBean.ZhengchangBean data = shangpinlist.get(position);
                                        String spguige = "";
//                                        switch (Integer.parseInt(data.getChoose_specifications())) {
//                                            case 1:
//                                                spguige = data.getPack_standard_one();
//                                                break;
//                                            case 2:
//                                                spguige = data.getPack_standard_two();
//                                                break;
//                                            case 3:
//                                                spguige = data.getPack_standard_tree();
//                                                break;
//                                        }
                                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), spguige, data.getRation_one() + "", data.getPrice() + ""
                                                , data.getPicture_url());
                                        final String finalSpguige = spguige;
                                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                                Log.e("data.getShopping_id()", data.getShopping_id() + "---");
                                                    if (Integer.parseInt(shuliang) >= Integer.parseInt(data.getRation_one())) {
                                                        addcar(data.getCommodity_id(), shuliang, data.getCompany_id(), "", finalSpguige);
                                                    } else {
                                                        ToastUtil.showToast("不够起订量");
                                                    }
                                                Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                                jiarugouwuchedialog.cancel();
                                            }
                                        });
                                        break;
                                }
                            }
                        });


                    }
                });
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
                        for (int i = 0; i < shangpinlist.size(); i++) {
                            if (spid.equals(shangpinlist.get(i).getCommodity_id())) {
                                shangpinlist.get(i).setShopping_id(data);
                            }
                        }
                        shangpinadapter.notifyDataSetChanged();
                        ToastUtil.showToast("添加成功");

                    }
                });
    }


    @OnClick({R.id.iv_back, R.id.ll_sousuo, R.id.tv_xiaoliang, R.id.ll_jiage, R.id.tv_pingfenzuigao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_sousuo:
                Intent intent = new Intent(this, SouSuoActivity.class);
                bundle.putString("sousuofangxiang","shichang");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_xiaoliang:
                sousuoshangpin("1");
                setXuanXiangColor(tvXiaoliang);
                break;
            case R.id.ll_jiage:
                //按价格升序或者降序
                if (isdi){
                    sousuoshangpin("4");
                }else{
                    sousuoshangpin("3");
                }
                setXuanXiangColor(tvJiage);
                break;
            case R.id.tv_pingfenzuigao:
                //按评分最高排序
                sousuoshangpin("2");
                setXuanXiangColor(tvPingfenzuigao);
                break;
        }
    }

    private void setXuanXiangColor(TextView bianseview){
        tvXiaoliang.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvJiage.setTextColor(getResources().getColor(R.color.lishisousuo));
        tvPingfenzuigao.setTextColor(getResources().getColor(R.color.lishisousuo));
        bianseview.setTextColor(getResources().getColor(R.color.zangqing));
    }
}
