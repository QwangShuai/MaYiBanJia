package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.res.Resources;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.GengDuoShangJiaBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class GengDuoShangJiaAdapter extends BaseQuickAdapter<XiTongTuiJianBean.CcListBean, BaseViewHolder> {

    public GengDuoShangJiaAdapter() {
        super(R.layout.item_gengduoshangjia);
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final XiTongTuiJianBean.CcListBean item) {
        helper.setText(R.id.tv_spming, item.getCommodity_name());
        helper.setText(R.id.tv_dianming, item.getCompany_name());
        helper.setText(R.id.tv_guige, item.getPack_standard());
        helper.setText(R.id.tv_danjia,item.getPrice());
        helper.setText(R.id.tv_spxiaoliang,"已售"+(item.getCommodity_sales()==null?0:item.getCommodity_sales()));
        
    }

}
