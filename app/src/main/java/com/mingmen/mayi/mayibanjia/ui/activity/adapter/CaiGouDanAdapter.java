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
    @BindView(R.id.tv_xiaoliang)
    TextView tv_xiaoliang;
    @BindView(R.id.tv_jiage)
    TextView tv_jiage;
    @BindView(R.id.tv_pingfen)
    TextView tv_pingfen;
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
        tv_xiaoliang = helper.getView(R.id.tv_xiaoliang);
        tv_jiage = helper.getView(R.id.tv_jiage);
        tv_pingfen = helper.getView(R.id.tv_pingfen);
        tv_zongjia = helper.getView(R.id.tv_zongjia);
        ll = helper.getView(R.id.ll_rongqi);
        changeView(item.getType(), tv_jiage, tv_xiaoliang, tv_pingfen);

        helper.setOnClickListener(R.id.tv_jiage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getOrder_audit_state().equals("902")) {
                    if (!CaiGouDanActivity.isCanClick(item)) {//判断是否可以点击
                        return;
                    }
                    //价格最低选项
                    Log.e("fandeyaosi", helper.getLayoutPosition() + "");
                    setShenPiTongGuo(CaiGouDanBean.TYPE_ONE + "", helper.getLayoutPosition(), tv_zongjia, tv_jiage, tv_xiaoliang, tv_pingfen);
                }
            }
        });
        helper.setOnClickListener(R.id.tv_xiaoliang, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getOrder_audit_state().equals("902")) {
                    if (!CaiGouDanActivity.isCanClick(item)) {
                        return;
                    }
                    //销量最高选项
                    setShenPiTongGuo(CaiGouDanBean.TYPE_TWO + "", helper.getLayoutPosition(), tv_zongjia, tv_jiage, tv_xiaoliang, tv_pingfen);
                }
            }
        });
        helper.setOnClickListener(R.id.tv_pingfen, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getOrder_audit_state().equals("902")) {
                    if (!CaiGouDanActivity.isCanClick(item)) {
                        return;
                    }
                    //评分最高选项
                    setShenPiTongGuo(CaiGouDanBean.TYPE_THREE + "", helper.getLayoutPosition(), tv_zongjia, tv_jiage, tv_xiaoliang, tv_pingfen);
                }
            }
        });
        helper.addOnClickListener(R.id.bt_xiangqing);
        helper.addOnClickListener(R.id.bt_shibai);
        helper.addOnClickListener(R.id.bt_tongguo);

        helper.setText(R.id.tv_shijian, item.getCreate_time());
        switch (Integer.parseInt(item.getOrder_audit_state())) {
            case 901://审核通过
                helper.setText(R.id.tv_zhuangtai, "审核通过");
                break;
            case 902://待审核
                helper.setText(R.id.tv_zhuangtai, "待审核");
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

    private void setShenPiTongGuo(final String type, final int position, final TextView tv_zongjia, final TextView tv_jiage, final TextView tv_xiaoliang, final TextView tv_pingfen) {
        final CaiGouDanBean item = this.getItem(position);
//        Log.e("item",new Gson().toJson(item));
        //获取采购单选择的商品的列表
        Log.e("canshu", PreferenceUtils.getString(MyApplication.mContext, "token", "") + "-" + item.getMarket_id() + "-" + item.getPurchase_id() + "-" + type);
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shenpitongguo(PreferenceUtils.getString(MyApplication.mContext, "token", ""), item.getMarket_id(), item.getPurchase_id(), type))
                .setDataListener(new HttpDataListener<ShenPiQuanXuanBean>() {
                    @Override
                    public void onNext(ShenPiQuanXuanBean data) {
                        Log.e("1122", String.valueOf(data.getList()));
                        if (!"null".equals(String.valueOf(data.getList()))) {
                            //成功后  判断市场下商品是否有不存在的情况
                            List<ShenPiQuanXuanBean.ListBean> datalist = data.getList();
                            Log.e("data", new Gson().toJson(data) + "---");
                            List<CaiGouDanBean.ListBean> caigoudan = item.getList();
                            String son_order_id = "";
                            String company_id = "";
                            String commodity_id = "";
                            for (int j = 0; j < data.getList().size(); j++) {
                                for (int i = 0; i < caigoudan.size(); i++) {
                                    if (caigoudan.get(i).getSon_order_id().equals(datalist.get(j).getSon_order_id())) {
                                        if (StringUtil.isEmpty(datalist.get(j).getCommodity_id())) {
                                            ToastUtil.showToast("市场下商品不全，请点击详情修改采购单");//商品不全下面不走
                                            return;
                                        }
                                        //拼接采购单子表id  店铺id  商品id
                                        son_order_id = son_order_id + caigoudan.get(i).getSon_order_id() + ",";
                                        company_id = company_id + datalist.get(j).getCompany_id() + ",";
                                        commodity_id = commodity_id + datalist.get(j).getCommodity_id() + ",";
                                        Log.e("caigoudan.get(i).getSon_order_id()", caigoudan.get(i).getSon_order_id() + "=" + i);
                                        Log.e("datalist.get(j).getSon_order_id())", datalist.get(j).getSon_order_id() + "=" + i);
                                    }
                                }
                            }

                            Log.e("son_order_id", son_order_id + "=");
                            Log.e("commodity_id", commodity_id + "=");

                            //商品全选后获取价格
                            final String finalSon_order_id = son_order_id;
                            final String finalCommodity_id = commodity_id;
                            final String finalCompany_id = company_id;
                            if (TextUtils.isEmpty(finalCommodity_id)) {
                                ToastUtil.showToast("目前没有商家报价");
                            } else {
                                HttpManager.getInstance()
                                        .with(mContext)
                                        .setObservable(
                                                RetrofitManager
                                                        .getService()
                                                        .getcaigoudanjiage(PreferenceUtils.getString(MyApplication.mContext, "token", ""), son_order_id, commodity_id))
                                        .setDataListener(new HttpDataListener<String>() {
                                            @Override
                                            public void onNext(String data) {
                                                Log.e("data", data + "---");
                                                item.setZongjia(data);
                                                item.setSon_order_id(finalSon_order_id);
                                                item.setCommodity_id(finalCommodity_id);
                                                item.setCompany_id(finalCompany_id);
                                                Log.e("finalSon_order_id", finalSon_order_id);
                                                Log.e("finalCommodity_id", finalCommodity_id);
                                                Log.e("finalCompany_id", finalCompany_id);

                                                setData(position, item); //价格获取成功后  更新采购单内容
                                                notifyDataSetChanged();
                                                tv_zongjia.setText(data);//显示价格
                                                choose(Integer.parseInt(type), item, tv_jiage, tv_xiaoliang, tv_pingfen);//选项变色
                                            }
                                        });
                            }
                        } else {
                            ToastUtil.showToast("暂无匹配项");
                        }


                    }
                });
    }


    //选项变色
    private void choose(int type, CaiGouDanBean item, TextView... textView) {
        if (item.getType() == type) return;
        item.setType(type);
        changeView(type, textView);

    }

    private void changeView(int type, TextView... textView) {
        for (int i = 0; i < textView.length; i++) {
            textView[i].setTextColor(type - 1 == i ? select : defaultC);
            textView[i].getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
    }

    public void delView() {//隐藏界面
        tv_xiaoliang.setVisibility(View.GONE);
        tv_pingfen.setVisibility(View.GONE);
        tv_jiage.setVisibility(View.GONE);
        ll.setVisibility(View.GONE);
    }

//    public boolean isCanClick(CaiGouDanBean item) {
//        for (int i1 = 0; i1 < item.getList().size(); i1++) {
//            if (item.getList().get(i1).isSpecial()){
//                Date createTime = DateUtil.StrToDate(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
//                Date dangqianTime = new Date();
//                long fen = DateUtil.dqsj(createTime, dangqianTime, "3");
//                if (fen<5){
//                    ToastUtil.showToast("商家正在抢单中，请抢单结束后再点击。距离抢单结束还有"+(5-fen)+"分钟。");
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

}
