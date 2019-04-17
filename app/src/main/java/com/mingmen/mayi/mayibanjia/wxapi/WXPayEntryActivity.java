package com.mingmen.mayi.mayibanjia.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.ui.activity.XuanZeZhiFuFangShiActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, UMConfig.WECHAT_APPID);
        api.registerApp(UMConfig.WECHAT_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wxpay_entry;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case 0:
                ToastUtil.showToastLong("支付成功");
//                Intent it = new Intent(WXPayEntryActivity.this,XuanZeZhiFuFangShiActivity.class);
                XuanZeZhiFuFangShiActivity.instance.updateZhifu("");
                break;
            case -1:
                ToastUtil.showToastLong("支付失败");
//                XuanZeZhiFuFangShiActivity.instance.finish();
                break;
            case -2:
                break;
        }
        finish();
    }
}
