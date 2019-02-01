package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DaYinQrCodeBean;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.dayinji.bean.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class DaYinQrCodeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.rv_qr_code)
    RecyclerView rv_qr_code;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.bt_add_qr_code)
    Button btAddQrCode;
    private Context mContext;
    private String id = "";
    private String sp_id = "";
    private String type = "2";
    private QrCodeAdapter adapter;
    private int count = 0;


    @Override
    public int getLayoutId() {
        return R.layout.activity_da_yin_qr_code;
    }

    @Override
    protected void initData() {
        mContext = DaYinQrCodeActivity.this;
        id = getIntent().getStringExtra("id");
        sp_id = getIntent().getStringExtra("sp_id");
        type = getIntent().getStringExtra("type");
        if(type.equals("0")){
            llAdd.setVisibility(View.GONE);
            btAddQrCode.setVisibility(View.GONE);
        }
        getQrCodeList();
    }

    @OnClick({R.id.iv_back, R.id.tv_add, R.id.bt_add_qr_code})
    protected void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                addQrCode();
                break;
            case R.id.bt_add_qr_code:
                if (count == 0) {
                    ToastUtil.showToast("暂无二维码");
                } else {
                    packageEnd();
                }
                break;
        }
    }

    public void addQrCode() {//新增
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .createQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id,sp_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        getQrCodeList();
                    }
                });
    }

    public void packageEnd() {//打包完成
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .packageEnd(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id,sp_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
//                        ToastUtil.showToast("打包完成,作废功能和新增功能已关闭");
                        llAdd.setVisibility(View.GONE);
                        btAddQrCode.setVisibility(View.GONE);

                    }
                });
    }

    public void getQrCodeList() {//获取列表
        Log.e("2222", "巴巴爱你");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,sp_id))
                .setDataListener(new HttpDataListener<List<DaYinQrCodeBean>>() {

                    @Override
                    public void onNext(List<DaYinQrCodeBean> data) {
                        count = data==null?0:data.size();
                        if(count==0)
                            return;
//                        if(StringUtil.isValid(data.getIs_true())&&data.getIs_true().equals("0")){
//                            llAdd.setVisibility(View.GONE);
//                            btAddQrCode.setVisibility(View.GONE);
//                        }
//                        Log.e("12333",data.getList().size()+"");
                        adapter = new QrCodeAdapter(DaYinQrCodeActivity.this, data, DaYinQrCodeActivity.this);
                        rv_qr_code.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rv_qr_code.setAdapter(adapter);
                    }
                });
    }

    public void dayinQrCode(View v) {
        Bitmap bitmap = decodeResource(getResources(),R.mipmap.qr_code);
//        Bitmap bitmap = convertViewToBitmap(v, 400, 240);
    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭打印机

    }
}
