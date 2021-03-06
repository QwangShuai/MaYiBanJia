package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.MessageBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBuShangPinActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SpXinxiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmSingleDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShangpinGaijiaDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.BaseShangPinFragment;
import com.mingmen.mayi.mayibanjia.ui.activity.shangpinguanli.QuanBuShangPinFragment;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiAdapter extends RecyclerView.Adapter<ShangPinGuanLiAdapter.ViewHolder> {
    public static final int viewtype_normaldata = 0, viewtype_erpdata = 1;
    private ConfirmDialog confirmDialog;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    private String goods = "0";
    private ViewHolder viewHolder;
    private List<ShangPinGuanLiBean.GoodsListBean> mList;
    private Context mContext;
    private ShangpinGaijiaDialog dialog;
    private BaseShangPinFragment fragment;
    private QuanBuShangPinFragment qb_fragment;
//    private boolean isTs;

    public void setClick(boolean click) {
        isClick = click;
        notifyDataSetChanged();
    }

    private boolean isClick = true;

    @Override
    public int getItemViewType(int position) {
//        if (mList.get(position).getSortOrder().equals("3")
//                || mList.get(position).getSortOrder().equals("1")
//                || mList.get(position).getSortOrder().equals("1"))
        if(true)
            return viewtype_erpdata;
        else
            return viewtype_normaldata;
    }

    public ShangPinGuanLiAdapter(Context mContext, String goods, List<ShangPinGuanLiBean.GoodsListBean> mList, BaseShangPinFragment fragment) {
        this.mContext = mContext;
        this.goods = goods;
        this.mList = mList;
        this.fragment = fragment;
//        isTs = false;
    }

    public ShangPinGuanLiAdapter(Context mContext, String goods, List<ShangPinGuanLiBean.GoodsListBean> mList, QuanBuShangPinFragment qb_fragment) {
        this.mContext = mContext;
        this.goods = goods;
        this.mList = mList;
        this.qb_fragment = qb_fragment;
//        isTs = true;
    }

    public void updateState(String id, final String type, final int pos) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .ghdspdel(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        mList.remove(pos);
                        notifyDataSetChanged();
                        confirmDialog.dismiss();
                    }
                }, false);
    }

    public void showDialog(String title, final String id, final String type, final int pos) {
        confirmDialog.showDialog(title);
        confirmDialog.getTvSubmit().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateState(id, type, pos);
            }
        });
        confirmDialog.getTvCancel().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }

    public void showGaijiaDialog(final int pos) {
        dialog.showDialog();
        dialog.getEtSmjg().setText(mList.get(pos).getPrice());
        dialog.getEtKcsl().setText(mList.get(pos).getInventory());
        dialog.getBtQueding().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isValid(dialog.getEtSmjg().getText().toString()) &&
                        StringUtil.isValid(dialog.getEtSmjg().getText().toString()) &&
                        Double.valueOf(dialog.getEtSmjg().getText().toString()) > 0 &&
                        Double.valueOf(dialog.getEtSmjg().getText().toString()) > 0) {
                    dialog.dismiss();
                    HttpManager.getInstance()
                            .with(mContext)
                            .setObservable(
                                    RetrofitManager
                                            .getService()
                                            .updateSpjg(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                                    mList.get(pos).getCommodity_id(),
                                                    dialog.getEtKcsl().getText().toString(),
                                                    dialog.getEtSmjg().getText().toString()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    mList.get(pos).setPrice(dialog.getEtSmjg().getText().toString() +
                                            mList.get(pos).getPin_name());
                                    mList.get(pos).setInventory(dialog.getEtKcsl().getText().toString());
                                    notifyDataSetChanged();
                                }
                            }, false);
                } else {
                    ToastUtil.showToastLong("请确认输入是否正确");
                }
            }
        });
        dialog.getTvQuxiao().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangpinguanli, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShangPinGuanLiBean.GoodsListBean bean = mList.get(position);
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        dialog = new ShangpinGaijiaDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        GlideUtils.cachePhoto(mContext, holder.ivSptu, bean.getPicture_url());
        holder.tvSpming.setText(bean.getClassify_name());
        holder.tvXiaoliang.setText("已售" + bean.getSumGoodsSales());
        holder.tvKucun.setText("库存" + bean.getInventory());
        holder.tvDanjia.setText("¥ " + bean.getPrice());
        holder.btBianji.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                    it.putExtra("state", "1");
                    it.putExtra("goods", goods);
                    if (bean.getSortOrder().equals("4")) {
                        it.putExtra("shsb", "1");
                    }
                    it.putExtra("bean", bean.getCommodity_id());
                    mContext.startActivity(it);
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }

            }
        });
        holder.btAddGuige.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                    it.putExtra("state", "0");
                    it.putExtra("goods", goods);
                    it.putExtra("guige", "1");
                    it.putExtra("bean", bean.getCommodity_id());
                    mContext.startActivity(it);
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
            }
        });
        holder.btShangjia.setVisibility(View.GONE);
        holder.btShangjia.setText("发售");
        holder.btXiajia.setVisibility(View.GONE);
        holder.btAddGuige.setVisibility(View.GONE);
        holder.btIsTejia.setVisibility(View.GONE);
        holder.btBianji.setVisibility(View.GONE);
        holder.tvXiajia.setVisibility(View.GONE);
        holder.btGaijia.setVisibility(View.GONE);
        switch (bean.getSortOrder()) {
            case "1"://在售商品
                holder.btXiajia.setVisibility(View.VISIBLE);
                holder.btAddGuige.setVisibility(View.VISIBLE);
                holder.btGaijia.setVisibility(View.VISIBLE);
                holder.btXiajia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick) {
                            showDialog("是否下架", bean.getCommodity_id(), "4", position);
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                holder.btGaijia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showGaijiaDialog(position);
                    }
                });
                break;
            case "2"://下架
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btAddGuige.setVisibility(View.VISIBLE);
                holder.btBianji.setVisibility(View.VISIBLE);
                holder.tvXiajia.setVisibility(View.VISIBLE);
                holder.btShangjia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick) {
                            showDialog("是否发售商品", bean.getCommodity_id(), "2", position);
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "3"://审核中
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btShangjia.setText("审核中");
                break;
            case "4"://审核失败
                holder.btIsTejia.setVisibility(View.VISIBLE);
                holder.btIsTejia.setText("删除");
                holder.btIsTejia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick) {
                            confirmDialog.showDialog("是否删除此商品");
                            confirmDialog.getTvSubmit().setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                    //删除
                                    HttpManager.getInstance()
                                            .with(mContext)
                                            .setObservable(
                                                    RetrofitManager
                                                            .getService()
                                                            .ghdspdel(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                                                    bean.getCommodity_id(), "3"))
                                            .setDataListener(new HttpDataListener<String>() {
                                                @Override
                                                public void onNext(String data) {
                                                    ToastUtil.showToastLong("删除审核失败商品成功");
                                                    mList.remove(position);
                                                    notifyDataSetChanged();
                                                }
                                            }, false);
                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.dismiss();
                                }
                            });
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                holder.btBianji.setVisibility(View.VISIBLE);
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btShangjia.setText("失败原因");
                holder.btShangjia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick) {
                            final ConfirmSingleDialog dialog;
                            dialog = new ConfirmSingleDialog(mContext,
                                    mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                            dialog.showDialog(bean.getFail_reason());
                            dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                }
                            });
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
        }


        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, SpXinxiActivity.class);
                it.putExtra("id", bean.getCommodity_id());
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bt_gaijia)
        Button btGaijia;
        @BindView(R.id.bt_add_guige)
        Button btAddGuige;
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_xiajia)
        TextView tvXiajia;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_xiaoliang)
        TextView tvXiaoliang;
        @BindView(R.id.tv_kucun)
        TextView tvKucun;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.bt_bianji)
        Button btBianji;
        @BindView(R.id.bt_xiajia)
        Button btXiajia;
        @BindView(R.id.bt_shangjia)
        Button btShangjia;
        @BindView(R.id.bt_is_tejia)
        Button btIsTejia;
        @BindView(R.id.ll_wenhao)
        LinearLayout llWenhao;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //数据
    private void getData(String id, String goods, String price, String pice_one) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .shangpinZhuanhuan(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, goods, price, pice_one))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("取消特价成功");
                        confirmDialog.dismiss();
                        EventBus.getDefault().post("update");
                    }

                });
    }
}
