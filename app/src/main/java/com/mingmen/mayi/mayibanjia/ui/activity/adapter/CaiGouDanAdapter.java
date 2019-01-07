package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.ShenPiQuanXuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.CaiGouDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.QueRenDingDanActivity;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/13/013.
 */
//采购单adapter
public class CaiGouDanAdapter extends BaseQuickAdapter<CaiGouDanBean, BaseViewHolder> {
    int select; //选中颜色
    int defaultC;//默认的颜色
    @BindView(R.id.tv_zongjia)
    TextView tv_zongjia;

    private LinearLayout ll;
    private BaseViewHolder helper;

    public CaiGouDanAdapter(Resources resources) {
        super(R.layout.item_caigoudan);
        select = resources.getColor(R.color.mayihong);//设置选中的颜色
        defaultC = resources.getColor(R.color.hintcolor);//设置默认的颜色
    }


    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CaiGouDanBean item) {
        this.helper = helper;
        tv_zongjia = helper.getView(R.id.tv_zongjia);
        ll = helper.getView(R.id.ll_rongqi);
        helper.addOnClickListener(R.id.bt_xiangqing);
        helper.addOnClickListener(R.id.iv_xuanzhong);

        helper.setText(R.id.tv_shijian, item.getCreate_time());
        switch (Integer.parseInt(item.getOrder_audit_state())) {
            case 901://审核通过
                helper.setText(R.id.tv_zhuangtai, "审核通过");
                break;
            case 902://待审核
                helper.setText(R.id.tv_zhuangtai, "待审核");
                helper.setVisible(R.id.iv_xuanzhong,true);
                break;
            case 903://不通过
                helper.setText(R.id.tv_zhuangtai, "不通过");
                break;
            case 904://重新发送
                helper.setText(R.id.tv_zhuangtai, "待审核");
                break;
            default:
                helper.setText(R.id.tv_zhuangtai, "待审核");
                break;
        }


        helper.setText(R.id.tv_zongjia, item.getZongjia() == null ? "" : "¥" + item.getZongjia());
        helper.setText(R.id.tv_caigoudanming, item.getPurchase_name() != null ? item.getPurchase_name() : "");
    }
}
