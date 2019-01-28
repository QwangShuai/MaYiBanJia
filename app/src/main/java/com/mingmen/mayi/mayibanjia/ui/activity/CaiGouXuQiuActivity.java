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

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.CaiGouListXuQiuLevelOneAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TiJiaoXuQiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

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
    @BindView(R.id.rl_list)
    RecyclerView rvList;

    private Context mContext;
    private CaiGouListXuQiuLevelOneAdapter adapter;
    private List<CaiGouDanBean.FllistBean> mList = new ArrayList<>();
    private TiJiaoXuQiuDialog tijiaoxuqiuDialog;
    private int REQUEST_CODE = 1;
    private String purchase_id = "";
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

        tijiaoxuqiuDialog = new TiJiaoXuQiuDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        adapter = new CaiGouListXuQiuLevelOneAdapter(CaiGouXuQiuActivity.this,mList,mContext);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setAdapter(adapter);
        rvList.setFocusable(false);
        rvList.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();

        getlist();
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
                break;
            case R.id.ll_add:
                startActivityForResult(new Intent(mContext, AddShangPinActivity.class),REQUEST_CODE);
                break;
            case R.id.ll_changgou:
                startActivityForResult(new Intent(mContext, ChangGouActivity.class),REQUEST_CODE);
                break;
        }
    }

    public  void getlist() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getShenpiThree(PreferenceUtils.getString(MyApplication.mContext, "token",""),purchase_id))//这块得改
                .setDataListener(new HttpDataListener<List<CaiGouDanBean>>() {
                    @Override
                    public void onNext(List<CaiGouDanBean> list) {
                        mList.clear();
                        mList.addAll(list.get(0).getFllist());
                        adapter.notifyDataSetChanged();
                    }
                });
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
    }
}
