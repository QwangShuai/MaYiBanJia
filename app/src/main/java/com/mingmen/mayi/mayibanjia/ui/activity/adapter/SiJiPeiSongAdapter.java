package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangJiaPhoneBean;
import com.mingmen.mayi.mayibanjia.bean.WuLiuBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuDingdanBean;
import com.mingmen.mayi.mayibanjia.bean.WuliuSijiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShichangWuliuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SiJiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.DiTuDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.MessageDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShuruDailog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.BaseSijiFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/26.
 */

public class SiJiPeiSongAdapter extends RecyclerView.Adapter<SiJiPeiSongAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<WuliuSijiBean.DdListBean> mList;
    private SiJiActivity activity;
    private ShichangWuliuActivity scwlActivity;
    private String type = "0";
    private SmsManager manager;
    private ConfirmDialog confirmDialog;
    private BaseSijiFragment fragment;
    private boolean isTeshu;
//    private CallBack callBack;

    private interface CallBack{
        void onClick(View v,int postion);
    }

    public SiJiPeiSongAdapter(Context context, List<WuliuSijiBean.DdListBean> list, BaseSijiFragment fragment, String type, SiJiActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.fragment = fragment;
        this.type = type;
        this.activity = activity;
    }

    public SiJiPeiSongAdapter(Context context, List<WuliuSijiBean.DdListBean> list, String type,ShichangWuliuActivity scwlActivity) {
        this.mContext = context;
        this.mList = list;
        this.type = type;
        this.scwlActivity = scwlActivity;
        isTeshu = true;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pei_song_xin_xi, parent, false));
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WuliuSijiBean.DdListBean data = mList.get(position);
        Log.e("ceshi----",String.valueOf(data.getWl_order_state()));
        holder.tv_state.setText(String.valueOf(data.getWl_order_state()));

        holder.btnJieshou.setVisibility(View.GONE);
        holder.btnJujue.setVisibility(View.GONE);
        if(isTeshu){
            holder.tv_ditu.setVisibility(View.GONE);
        } else {
            holder.tv_ditu.setVisibility(View.VISIBLE);
        }
        if (data.getWl_order_state().equals("待处理")) {
            holder.btnTongzhi.setVisibility(View.GONE);
            holder.llSaoma.setVisibility(View.GONE);
            holder.tvQuhuoma.setVisibility(View.GONE);
            holder.btnSure.setText("自行处理");
            holder.btnSure.setVisibility(View.VISIBLE);
            holder.btnSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmDialog.showDialog("是否进行自行处理");
                    confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changeOrder(data.getWl_cars_order_id());
                        }
                    });
                    confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmDialog.dismiss();
                        }
                    });
                }
            });
        }else if (data.getWl_order_state().equals("待送货")) {
            holder.btnTongzhi.setVisibility(View.GONE);
            holder.llSaoma.setVisibility(View.GONE);
            holder.tvQuhuoma.setVisibility(View.GONE);
        }else if (data.getWl_order_state().equals("预警订单")) {
            holder.btnSure.setText("解除预警");
            holder.btnSure.setVisibility(View.VISIBLE);
            holder.btnSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmDialog.showDialog("解触预警后会将订单发给供货商，您确定吗？");
                    confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            postError(data.getWl_cars_order_id());
                        }
                    });
                    confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmDialog.dismiss();
                        }
                    });
                }
            });
        }else if(data.getWl_order_state().equals("待确认")){
            if(isTeshu){
                holder.btnSure.setVisibility(View.VISIBLE);
                holder.btnSure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.showDialog("是否确认打包完成");
                        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                postSongda(data.getWl_cars_order_id());
                            }
                        });
                        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmDialog.dismiss();
                            }
                        });
                    }
                });
            } else {
                holder.btnSure.setVisibility(View.GONE);
            }
        }else if(data.getWl_order_state().equals("已分车")){
            holder.tv_state.setText(data.getWl_cars_type_name());
            if(data.getWl_cars_type_name().equals("待确认")){
                holder.btnTongzhi.setVisibility(View.GONE);
                holder.btnJieshou.setVisibility(View.VISIBLE);
                holder.btnJujue.setVisibility(View.VISIBLE);
                holder.llSaoma.setVisibility(View.GONE);
                holder.tvQuhuoma.setVisibility(View.GONE);

                holder.btnJieshou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        callBack.onClick(view,position);
                        jiedanState(data.getWl_cars_order_number(),"","1");
                    }
                });
                holder.btnJujue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        callBack.onClick(view,position);
                        ShuruDailog dialog = new ShuruDailog(mContext, new ShuruDailog.CallBack() {
                            @Override
                            public void confirm(String msg) {
                                jiedanState(data.getWl_cars_id(),msg,"2");
                            }
                        });
                        dialog.show();
                    }
                });

            } else {
                holder.tv_state.setText(data.getWl_cars_type_name());
                holder.btnTongzhi.setVisibility(View.VISIBLE);
                holder.btnJieshou.setVisibility(View.GONE);
                holder.btnJujue.setVisibility(View.GONE);
                holder.llSaoma.setVisibility(View.VISIBLE);
                holder.tvQuhuoma.setVisibility(View.VISIBLE);
            }
        }  else {
            holder.tv_state.setTextColor(R.color.zicolor);
        }
        holder.tv_order_number.setText("订单编号：" + data.getWl_cars_order_number());
        holder.tv_songdashijian.setText(data.getArrival_time());
        holder.tvChuangjiashijian.setText(data.getCreate_time());
        holder.tv_shichang.setText(data.getMarketName());
        holder.tv_dizhi.setText(data.getSpecific_address());
        holder.ll_rongqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(new Intent(mContext, PeiSongXiangQingActivity.class));
                it.putExtra("xqID", data.getWl_cars_order_number());
                it.putExtra("isTeshu",true);
                mContext.startActivity(it);
            }
        });
        holder.tv_ditu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(RetrofitManager.getService()
                                .getJingweidu(PreferenceUtils.getString(MyApplication.mContext, "token", ""),data.getSpecific_address() + ""))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String weizhi) {
                                DiTuDialog dialog = new DiTuDialog();
                                dialog.setData(mContext, weizhi.split(",")[1], weizhi.split(",")[0], data.getSpecific_address());
                                if(isTeshu){
                                    dialog.show(scwlActivity.getSupportFragmentManager());
                                } else {
                                    dialog.show(activity.getSupportFragmentManager());
                                }

                            }
                        });
            }
        });
        holder.llSaoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (data.getScanCount().equals(data.getPackCount())) {
//                    ToastUtil.showToast("装车完成");
//                } else {
                    if(isTeshu){
                        scwlActivity.saomiaoQrCode();
                    } else {
                        fragment.saomiaoQrCode();
                    }
//                }
            }
        });

        holder.btnTongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.showDialog("是否确认群发短信");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhone();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
            }
        });



        holder.tvQuhuoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (data.getScanCount().equals(data.getPackCount())) {
//                    ToastUtil.showToast("装车完成");
//                } else {
                    Intent it = new Intent(mContext, WeiYiQrCodeActivity.class);
                    it.putExtra("type", "gyID");
                    it.putExtra("gyID", String.valueOf(mList.get(position).getGy_order_id()));
                    mContext.startActivity(it);
//                }

            }
        });
        if(data.getIsTrue().equals("1")){
            holder.llSaoma.setVisibility(View.GONE);
            holder.tvQuhuoma.setVisibility(View.GONE);
//            holder.llBaozhuang.setVisibility(View.GONE);
//            holder.llSaomageshu.setVisibility(View.GONE);
        } else {
            holder.llSaoma.setVisibility(View.VISIBLE);
            holder.tvQuhuoma.setVisibility(View.VISIBLE);
//            holder.llBaozhuang.setVisibility(View.VISIBLE);
//            holder.llSaomageshu.setVisibility(View.VISIBLE);
        }
        holder.tvSaomageshu.setText(data.getScanCount());
        holder.tvBaozhuanggeshu.setText(data.getPackCount());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @OnClick(R.id.ll_saoma)
    public void onViewClicked() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_state)
        TextView tv_state;
        @BindView(R.id.tv_order_number)
        TextView tv_order_number;
        @BindView(R.id.tv_songdashijian0)
        TextView tv_songdashijian0;
        @BindView(R.id.tv_songdashijian)
        TextView tv_songdashijian;
        @BindView(R.id.tv_chuangjiashijian)
        TextView tvChuangjiashijian;
        @BindView(R.id.tv_shichang0)
        TextView tv_shichang0;
        @BindView(R.id.tv_shichang)
        TextView tv_shichang;
        @BindView(R.id.tv_dizhi0)
        TextView tv_dizhi0;
        @BindView(R.id.tv_dizhi)
        TextView tv_dizhi;
        @BindView(R.id.ll_rongqi)
        LinearLayout ll_rongqi;
        @BindView(R.id.tv_ditu)
        TextView tv_ditu;
        @BindView(R.id.ll_saoma)
        LinearLayout llSaoma;
        @BindView(R.id.tv_baozhuanggeshu)
        TextView tvBaozhuanggeshu;
        @BindView(R.id.ll_baozhuang)
        LinearLayout llBaozhuang;
        @BindView(R.id.tv_quhuoma)
        TextView tvQuhuoma;
        @BindView(R.id.tv_saomageshu)
        TextView tvSaomageshu;
        @BindView(R.id.ll_saomageshu)
        LinearLayout llSaomageshu;
        @BindView(R.id.btn_tongzhi)
        Button btnTongzhi;
        @BindView(R.id.btn_sure)
        Button btnSure;
        @BindView(R.id.btn_jieshou)
        Button btnJieshou;
        @BindView(R.id.btn_jujue)
        Button btnJujue;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private void getPhone(){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getShangJiaPhoneList(PreferenceUtils.getString(mContext,"token","")))
                .setDataListener(new HttpDataListener<List<ShangJiaPhoneBean>>() {
                    @Override
                    public void onNext(List<ShangJiaPhoneBean> list) {
                        int size = list==null?0:list.size();
                        manager = SmsManager.getDefault();
                        for (int i=0;i<size;i++) {
                            // 创建一个PendingIntent对象
                            PendingIntent pi = PendingIntent.getActivity(
                                    mContext, 0, new Intent(), 0);
                            // 发送短信
                            manager.sendTextMessage(list.get(i).getTelephone(), null, list.get(i).getPlateNumber(), pi, null);
                        }
//                        viewHolder.btnTongzhi.setText("已通知");
                        ToastUtil.showToastLong("短信已发送");
                        confirmDialog.dismiss();
                    }
                });
    }

    private void postSongda(String id){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .postOrder(PreferenceUtils.getString(mContext,"token",""),id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("确认订单成功");
                        confirmDialog.dismiss();
                        scwlActivity.onResume();
                    }
                });
    }

    private void postError(String id){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .postError(PreferenceUtils.getString(mContext,"token",""),id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("确认解决异常成功");
                        confirmDialog.dismiss();
                        scwlActivity.onResume();
                    }
                });
    }

    private void changeOrder(String id){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .changeOrder(PreferenceUtils.getString(mContext,"token",""),id,"0"))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("转换成功");
                        confirmDialog.dismiss();
                        scwlActivity.onResume();
                    }
                });
    }

    private void jiedanState(String id, String remarket, final String type){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .jiedanState(PreferenceUtils.getString(mContext,"token",""),id,remarket,type))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("成功");
                        fragment.onResume();
                    }
                });
    }
}
