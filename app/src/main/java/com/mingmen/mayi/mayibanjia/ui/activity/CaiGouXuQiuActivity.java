package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.UpdateCaiGouListBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListXuQiuLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TiJiaoXuQiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaiGouXuQiuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.ll_changgou)
    LinearLayout llChanggou;
    @BindView(R.id.ll_null)
    LinearLayout llNull;
    @BindView(R.id.rl_list)
    RecyclerView rvList;

    private Context mContext;
    private CaiGouListXuQiuLevelOneAdapter adapter;
    private List<CaiGouDanBean.FllistBean> mList = new ArrayList<>();
    private TiJiaoXuQiuDialog tijiaoxuqiuDialog;
    private int REQUEST_CODE = 1;
    private String purchase_id = "";
    private String listGson = "";
    private List<UpdateCaiGouListBean> list = new ArrayList<>();
    private ConfirmDialog confirmDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cai_gou_xu_qiu;
    }

    @Override
    protected void initData() {
        mContext = CaiGouXuQiuActivity.this;
        tvTitle.setText(getIntent().getStringExtra("caigouming"));
        tvRight.setText("确认提交");
        tvRight.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        tijiaoxuqiuDialog = new TiJiaoXuQiuDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter = new CaiGouListXuQiuLevelOneAdapter(CaiGouXuQiuActivity.this,mList,mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        rvList.setFocusable(false);
        rvList.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();

//        getlist();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_add, R.id.ll_changgou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                updateCaiGouList();
                break;
            case R.id.ll_add:
                setIntentType(0);
                break;
            case R.id.ll_changgou:
                setIntentType(1);
                break;
        }
    }

    public void getlist() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShenpiThree(PreferenceUtils.getString(MyApplication.mContext, "token",""),purchase_id))//这块得改
                .setDataListener(new HttpDataListener<CaiGouDanBean>() {
                    @Override
                    public void onNext(CaiGouDanBean data) {
                        mList.clear();
                        mList.addAll(data.getFllist());
                        int mysize = mList==null?0:mList.size();
                        if(mysize!=0){
                            llNull.setVisibility(View.GONE);
                            rvList.setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        } else {
                            llNull.setVisibility(View.VISIBLE);
                            rvList.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getlist();
    }

    //    public void getlist(){
//        mList.clear();
//        CaiGouDanBean.FllistBean bean = new CaiGouDanBean.FllistBean();
//        List<CaiGouDanBean.FllistBean.SonorderlistBean> mylist  = new ArrayList<>();
//        for (int i = 0;i<3;i++){
//            bean.setClassify_name("难受啊兄弟"+i);
//            for (int j =0;j<3;j++){
//                CaiGouDanBean.FllistBean.SonorderlistBean sbean = new CaiGouDanBean.FllistBean.SonorderlistBean();
//                sbean.setClassify_name("难受啊，马飞"+j);
//                mylist.add(sbean);
//                bean.setSonorderlist(mylist);
//            }
//            mList.add(bean);
//            adapter.notifyDataSetChanged();
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            if(requestCode==REQUEST_CODE){
                if(StringUtil.isValid(data.getStringExtra("id"))){
                    purchase_id = data.getStringExtra("id");
                    getlist();
                }
            }
        }
    }

    private void setIntentType(int type){
        Intent it = new Intent();
        if(type==0){
            it.setClass(mContext, AddShangPinActivity.class);
        } else {
            it.setClass(mContext, ChangGouActivity.class);
        }
        it.putExtra("name",getIntent().getStringExtra("caigouming"));
        it.putExtra("id",purchase_id);
        startActivityForResult(it,REQUEST_CODE);
    }

    public void setListData(int pos,int postion,int s){
        if(s!=mList.get(pos).getSonorderlist().get(postion).getCount()){
            mList.get(pos).getSonorderlist().get(postion).setCount(s);
//            if (rvList.getScrollState() == RecyclerView.SCROLL_STATE_IDLE ) {
//                adapter.notifyDataSetChanged();
//            }
        }
    }

    private boolean getListGson(){
        int mysize = mList==null?0:mList.size();
        if(mysize==0)
            return false;
        for(int i = 0;i<mList.size();i++){
            for (int j=0;j<mList.get(i).getSonorderlist().size();j++){
                UpdateCaiGouListBean bean = new UpdateCaiGouListBean();
                bean.setSon_order_id(mList.get(i).getSonorderlist().get(j).getSon_order_id());
                if(mList.get(i).getSonorderlist().get(j).getCount()!=0){
                    bean.setCount(mList.get(i).getSonorderlist().get(j).getCount());
                } else {
                    ToastUtil.showToastLong("请确保每个商品都已输入采购数量");
                    return false;
                }
                list.add(bean);
            }
        }
        listGson = new Gson().toJson(list);
        return true;
    }

    public void updateCaiGouList() {
        if(getListGson()){
            confirmDialog.showDialog("是否确认提交采购单");
            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HttpManager.getInstance()
                            .with(mContext)
                            .setObservable(
                                    RetrofitManager
                                            .getService()
                                            //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                            .postCaigoudan(purchase_id))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    HttpManager.getInstance()
                                            .with(mContext)
                                            .setObservable(
                                                    RetrofitManager
                                                            .getService()
                                                            .updateCaiGou(PreferenceUtils.getString(MyApplication.mContext, "token",""),listGson))//这块得改
                                            .setDataListener(new HttpDataListener<String>() {
                                                @Override
                                                public void onNext(String data) {
                                                    tijiaoxuqiuDialog .showDialog();
                                                    tijiaoxuqiuDialog.getTvCaigoudan().setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent intent = new Intent(mContext, CaiGouDanActivity.class);
                                                            startActivity(intent);
                                                            FCGDiQuXuanZeActivity.instance.finish();
                                                            tijiaoxuqiuDialog.dismiss();
                                                            finish();
                                                        }
                                                    });
                                                    tijiaoxuqiuDialog.getTvShouye().setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);
                                                            tijiaoxuqiuDialog.dismiss();
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });
                                }

                            }, false);
                    confirmDialog.cancel();
                }
            });
            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmDialog.cancel();
                }
            });

        }

    }
}
