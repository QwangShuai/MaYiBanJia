package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DaYinQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TuikuanDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/28.
 */

public class GHOrderAdapter extends RecyclerView.Adapter<GHOrderAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<GHOrderBean> mList;
    private Activity activity;
    private BaseGHOrderFragment fragment;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    private boolean isClick = true;
    public GHOrderAdapter(Context mContext, List<GHOrderBean> list, Activity activity,BaseGHOrderFragment fragment) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
        this.fragment = fragment;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gh_order, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GHOrderBean bean = mList.get(position);
        holder.tv_time.setText("客户要求送达时间:" + bean.getArrival_time());
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv_shangpin.setLayoutManager(manager);
        if(StringUtil.isValid(bean.getRefund())&&!bean.getRefund().equals("0")){
            holder.rlTuikuan.setVisibility(View.VISIBLE);
            holder.tvTuikuanjine.setText(bean.getRefund());
        } else {
            holder.rlTuikuan.setVisibility(View.GONE);
        }
        if(StringUtil.isValid(bean.getAppend_money())&&!bean.getAppend_money().equals("0")){
            holder.rlFuJiaMoney.setVisibility(View.VISIBLE);
            holder.tvFuJiaFeiMoney.setText(bean.getAppend_money());
        } else {
            holder.rlFuJiaMoney.setVisibility(View.GONE);
        }

        if (bean.getState_name().equals("待发货")) {
            holder.tv_state.setText("待发货");
            holder.rl_wancheng.setVisibility(View.GONE);
            if (TextUtils.isEmpty(String.valueOf(bean.getDriver_name()))) {
                holder.tv_wait_state.setText("待送货员取商品");
                holder.ll_rongqi.setVisibility(View.GONE);
                holder.tv_chepaihao.setVisibility(View.GONE);
            } else {
                holder.tv_peisongyuan.setText("配送员:" + bean.getDriver_name());
                holder.tv_phone.setText(String.valueOf(bean.getDriver_phone()));
                holder.tv_chepaihao.setText(String.valueOf(bean.getPlate_number()));
            }
            holder.tv_fahuoshijian.setVisibility(View.GONE);
            holder.tv_end_time.setVisibility(View.GONE);
        } else if (bean.getState_name().equals("已发货")) {
            holder.tv_state.setText("已发货");
            holder.rl_wancheng.setVisibility(View.GONE);
            holder.tv_wait_state.setVisibility(View.GONE);
            holder.tv_peisongyuan.setText("配送员:" + bean.getDriver_name());
            holder.tv_phone.setText(String.valueOf(bean.getDriver_phone()));
            holder.tv_chepaihao.setText(String.valueOf(bean.getPlate_number()));
            holder.tv_fahuoshijian.setText("发货时间:" + String.valueOf(bean.getWl_sweep_time()));
            holder.tv_end_time.setVisibility(View.GONE);
            holder.iv_dianhua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallPhone(String.valueOf(bean.getDriver_phone()));
                }
            });
        } else {
            //修改的地方不好使待改
            if(StringUtil.isValid(String.valueOf(bean.getWl_sweep_time()))){
                holder.tv_fahuoshijian.setText("发货时间:" + String.valueOf(bean.getWl_sweep_time()));
            }else{
                holder.tv_fahuoshijian.setVisibility(View.GONE);
            }
            holder.iv_del.setVisibility(View.GONE);
            holder.tv_state.setVisibility(View.GONE);
            holder.tv_wait_state.setVisibility(View.GONE);
            Log.d("abcdefg",String.valueOf(bean.getDriver_name()));
            if(StringUtil.isValid(String.valueOf(bean.getDriver_name()))){
                holder.tv_peisongyuan.setText("配送员:" + bean.getDriver_name());
                holder.tv_chepaihao.setText(String.valueOf(bean.getPlate_number()));
                //holder.tv_fahuoshijian.setText("发货时间:" + String.valueOf(bean.getWl_sweep_time()));

            }else{
                holder.tv_peisongyuan.setVisibility(View.GONE);
                holder.tv_phone.setVisibility(View.GONE);
                holder.iv_dianhua.setVisibility(View.GONE);
                holder.tv_chepaihao.setVisibility(View.GONE);
            }
            holder.tv_phone.setText(String.valueOf(bean.getDriver_phone()));
            holder.tv_end_time.setText(bean.getMj_sweep_time());
            holder.tvTuikuan.setVisibility(View.GONE);
        }
        holder.tvDianpu.setText(bean.getCompany_name() + ":");
        holder.tvDianpuPhone.setText(bean.getTelephone());
        holder.tv_shangpin.setText(bean.getCountname());
        holder.tv_zongjia.setText("￥:" + bean.getTotal_price());
        holder.tv_order_number.setText("订单编号:" + bean.getGy_order_number());
        holder.tv_order_time.setText("下单时间:" + bean.getCreate_time());
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GHOrderBean.ZilistBean> list = new ArrayList<GHOrderBean.ZilistBean>();
                if (holder.btn_more.getText().equals("展开")) {
                    list.addAll(bean.getZilist());
                    holder.btn_more.setText("收起");
                } else {
                    list.clear();
                    holder.btn_more.setText("展开");
                }
                GHShangPinAdapter adapter = new GHShangPinAdapter(mContext, list);
                holder.rv_shangpin.setAdapter(adapter);
            }
        });
        if(isClick){
            holder.ivDianpuDianhua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallPhone(bean.getTelephone());
                }
            });
            holder.tvTuikuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new TuikuanDialog(mContext,bean.getGy_order_id(),bean.getGy_order_number(),fragment).show();
                }
            });
            holder.iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HttpManager.getInstance()
                            .with(mContext)
                            .setObservable(RetrofitManager.getService()
                                    .delGHOrder(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bean.getGy_order_id()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String o) {
                                    mList.remove(position);
                                    notifyDataSetChanged();
                                }
                            });
                }
            });

            holder.tv_dayin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if (!TextUtils.isEmpty(String.valueOf(bean.getDriver_name()))) {
                    Intent it = new Intent(mContext, AddQrCodeActivity.class);
                    it.putExtra("id", bean.getGy_order_id());
                    mContext.startActivity(it);
//                    GHDOrderActivity activity = new GHDOrderActivity();
//                    Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.qr_code);
//                    activity.dayinQrCode(bitmap);
                    // } else {
                    //    ToastUtil.showToast("此状态不能打印");
                    //}
                }
            });
        } else {
            ToastUtil.showToastLong("注意，您只有阅览权限");
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_state)
        TextView tv_state;
        //是否显示已完成
        @BindView(R.id.rl_wancheng)
        RelativeLayout rl_wancheng;
        @BindView(R.id.iv_del)
        ImageView iv_del;
        //是否显示暂无送货员
        @BindView(R.id.tv_wait_state)
        TextView tv_wait_state;
        //是否显示送货员信息
        @BindView(R.id.rl_rongqi)
        RelativeLayout rl_rongqi;
        @BindView(R.id.tv_peisongyuan)
        TextView tv_peisongyuan;
        @BindView(R.id.tv_phone)
        TextView tv_phone;
        @BindView(R.id.iv_dianhua)
        ImageView iv_dianhua;
        @BindView(R.id.tv_shangpin)
        TextView tv_shangpin;
        @BindView(R.id.btn_more)
        Button btn_more;
        @BindView(R.id.rv_shangpin)
        RecyclerView rv_shangpin;
        @BindView(R.id.tv_zongjia)
        TextView tv_zongjia;
        @BindView(R.id.tv_order_number)
        TextView tv_order_number;
        @BindView(R.id.tv_order_time)
        TextView tv_order_time;
        //是否显示完成时间
        @BindView(R.id.tv_end_time)
        TextView tv_end_time;
        @BindView(R.id.tv_dayin)
        TextView tv_dayin;
        @BindView(R.id.tv_chepaihao)
        TextView tv_chepaihao;
        @BindView(R.id.tv_fahuoshijian)
        TextView tv_fahuoshijian;
        //是否显示配送信息
        @BindView(R.id.ll_rongqi)
        LinearLayout ll_rongqi;
        @BindView(R.id.tv_dianpu)
        TextView tvDianpu;
        @BindView(R.id.tv_dianpu_phone)
        TextView tvDianpuPhone;
        @BindView(R.id.iv_dianpu_dianhua)
        ImageView ivDianpuDianhua;
        @BindView(R.id.tv_tuikuan)
        TextView tvTuikuan;
        @BindView(R.id.rl_tuikuan)
        RelativeLayout rlTuikuan;
        @BindView(R.id.tv_tuikuanjine)
        TextView tvTuikuanjine;
        @BindView(R.id.rl_fuJiaMoney)//附加费layout
        RelativeLayout rlFuJiaMoney;
        @BindView(R.id.tv_fuJiaFeiMoney)
        TextView tvFuJiaFeiMoney;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
