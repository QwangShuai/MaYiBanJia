package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

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
    @BindView(R.id.iv_xuanzhong)
    ImageView ivXuanzhong;
    @BindView(R.id.ll_kuang)
    LinearLayout llKuang;

    private LinearLayout ll;
    private BaseViewHolder helper;
    private boolean isShow;
    private String type;
    public static final int viewtype_normaldata = 0,viewtype_erpdata = 1;

    public CaiGouDanAdapter(Resources resources) {
        super(R.layout.item_caigoudan);
        select = resources.getColor(R.color.red_ff3300);//设置选中的颜色
        defaultC = resources.getColor(R.color.hintcolor);//设置默认的颜色
    }


    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CaiGouDanBean item) {
        this.helper = helper;
        tv_zongjia = helper.getView(R.id.tv_zongjia);
        ivXuanzhong = helper.getView(R.id.iv_xuanzhong);
        ll = helper.getView(R.id.ll_rongqi);
        helper.addOnClickListener(R.id.bt_xiangqing);
        helper.addOnClickListener(R.id.iv_xuanzhong);
        type = item.getOrder_audit_state();
        helper.setText(R.id.tv_shijian, item.getCreate_time());
        switch (Integer.parseInt(item.getOrder_audit_state())) {
            case 901://审核通过
                helper.setText(R.id.tv_zhuangtai, "审核通过");
                break;
            case 902://待审核
                helper.setText(R.id.tv_zhuangtai, "待审核");
                if(isShow){
                    helper.setVisible(R.id.iv_xuanzhong, true);
                } else {
                    helper.setGone(R.id.iv_xuanzhong,false);
                }
                if(StringUtil.isValid(item.getCt_buy_final_id())){
                    helper.setVisible(R.id.tv_hedan,true);
                } else {
                    helper.setGone(R.id.tv_hedan,false);
                }
                break;
            case 903://不通过
                helper.setText(R.id.tv_zhuangtai, "不通过");
                break;
            case 904://重新发送
                helper.setText(R.id.tv_zhuangtai, "待提交");
                break;
            default:
                helper.setText(R.id.tv_zhuangtai, "待审核");
                break;
        }

        ivXuanzhong.setSelected(item.isSelect());
        helper.setText(R.id.tv_zongjia, item.getZongjia() == null ? "" : "¥" + item.getZongjia());
        helper.setText(R.id.tv_caigoudanming, item.getPurchase_name() != null ? item.getPurchase_name() : "");
    }

    public void setShow(boolean b){
//        ivXuanzhong.setVisibility(b?View.VISIBLE:View.GONE);
        isShow = b;
        notifyDataSetChanged();
    }

    public int getViewType() {
        // current menu type
        return type.equals("903")?viewtype_erpdata:viewtype_normaldata;
    }
}
