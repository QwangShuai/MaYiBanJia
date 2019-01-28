package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TubiaoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.qiniu.android.utils.Json;
import com.qiniu.android.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.easing.Linear;

import static com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog.JIA;
import static com.mingmen.mayi.mayibanjia.ui.activity.dialog.GWCXiuGaiShuLiangDialog.JIAN;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class GWCShangPinAdapter extends RecyclerView.Adapter<GWCShangPinAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<GWCShangPinBean.ShoppingBean> mList;
    private boolean isSelected;
    GouWuCheFragment gouWuCheFragment;
    private OnItemClickListener mOnItemClickListener;
    public boolean isSelected() {
        return isSelected;
    }



    public interface OnItemClickListener{
        void onClick(View view, int position, List<GWCShangPinBean.ShoppingBean> mList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GWCShangPinAdapter(Context mContext, List<GWCShangPinBean.ShoppingBean> list, boolean isSelected, GouWuCheFragment gouWuCheFragment) {
        this.mContext = mContext;
        this.mList = list;
        this.gouWuCheFragment = gouWuCheFragment;
        this.isSelected = isSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_shangpin, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {//购物车页面初始数据加载
        final GWCShangPinBean.ShoppingBean shoppingBean = mList.get(position);
        //holder.tvName.setText(shoppingBean.getClassify_name());
        holder.tvName.setText(shoppingBean.getClassify_name());//商品名称改为三级分类名称
        //holder.tvGuige.setText(shoppingBean.getPack_standard());
        holder.tvPrice.setText(shoppingBean.getPrice()+"");
        holder.tvNumber.setText(shoppingBean.getNumber()+"");
        Glide.with(mContext).load(shoppingBean.getUrl())
                .into(holder.ivTu);
        holder.ivDanxuan.setSelected(shoppingBean.isSelected());

        if (!StringUtil.isEmpty(shoppingBean.getRation_one())){
            if (!StringUtil.isEmpty(shoppingBean.getSpecNameTwo())){
                holder.tvQidingliang.setText(shoppingBean.getRation_one()+shoppingBean.getSpecNameTwo());
            }else{
                holder.tvQidingliang.setText(shoppingBean.getRation_one()+shoppingBean.getSpecNameThree());
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",shoppingBean.getCommodity_id());
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
/*        holder.llQidingliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showQiDingLiangPop(shoppingBean.getRation_one(),shoppingBean.getRation_two(),shoppingBean.getRation_three(),shoppingBean.getPice_one(),shoppingBean.getPice_two(),shoppingBean.getPice_three(),holder.tvName);
            }


        });*/

        holder.ivZoushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //走势图
                Intent zoushi=new Intent(mContext,TubiaoActivity.class);
                zoushi.putExtra("mark_id",shoppingBean.getSon_number());//市场id
                zoushi.putExtra("market_name",shoppingBean.getMarket_name());//市场名
                zoushi.putExtra("classify_id",shoppingBean.getType_four_id() );//三级分类id
                zoushi.putExtra("classify_name",shoppingBean.getClassify_name());//三级分类名称
                Log.e("ceshi----------", shoppingBean.getSon_number()+shoppingBean.getMarket_name()+shoppingBean.getType_four_id()+shoppingBean.getClassify_name()+"================================");
                mContext.startActivity(zoushi);
            }
        });
        if (mOnItemClickListener!=null){
            holder.ivDanxuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    gouWuCheFragment.getalllist();
                    shoppingBean.setSelected(!shoppingBean.isSelected());
                    holder.ivDanxuan.setSelected(shoppingBean.isSelected());
                    mList.get(position).setSelected(shoppingBean.isSelected());
                    mOnItemClickListener.onClick(v,position,mList);
                }
            });
            final String qidingliang1 = shoppingBean.getRation_one();
            holder.tvNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GWCXiuGaiShuLiangDialog()
                            .setKuCun(shoppingBean.getInventory(), qidingliang1,Integer.parseInt(holder.tvNumber.getText().toString().trim()))
                            .setCallBack(new GWCXiuGaiShuLiangDialog.CallBack() {
                        @Override
                        public void shuliang(final String msg) {
                            HttpManager.getInstance()
                                    .with(mContext)
                                    .setObservable(
                                            RetrofitManager
                                                    .getService()
                                                    .editcar(PreferenceUtils.getString(MyApplication.mContext, "token",""),shoppingBean.getShopping_id(),3+"",msg))
                                    .setDataListener(new HttpDataListener<String>() {
                                        @Override
                                        public void onNext(String data) {
                                            holder.tvNumber.setText(msg);
                                            mList.get(position).setNumber(Integer.parseInt(msg));
                                        }
                                    },false);

                        }
                    }).show(gouWuCheFragment.getActivity().getSupportFragmentManager());
                }
            });
            final int[] shuliang = {Integer.parseInt(holder.tvNumber.getText().toString().trim())};
            holder.tvJiahao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shuliang[0] ==Integer.parseInt(shoppingBean.getInventory())){
                        ToastUtil.showToast("不能再加啦");
                        return;
                    }else{
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .editcar(PreferenceUtils.getString(MyApplication.mContext, "token",""),shoppingBean.getShopping_id(),1+"",shuliang[0]+""))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        shuliang[0]++;
                                        holder.tvNumber.setText(shuliang[0]+"");
                                        mList.get(position).setNumber(shuliang[0]);
//                        ToastUtil.showToast("修改成功");
                                    }
                                },false);

                    }

                }
            });
            holder.tvJianhao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shuliang[0] <=Integer.parseInt(qidingliang1)){
                        ToastUtil.showToast("不能再减啦");
                        return;
                    }else{
                        if (shuliang[0]==Integer.parseInt(qidingliang1)){
                            ToastUtil.showToast("不能再减啦");
                            return;
                        }
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .editcar(PreferenceUtils.getString(MyApplication.mContext, "token",""),shoppingBean.getShopping_id(),2+"",shuliang[0]+""))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        shuliang[0]--;
                                        holder.tvNumber.setText(shuliang[0]+"");
                                        mList.get(position).setNumber(shuliang[0]);

//                        ToastUtil.showToast("修改成功");
                                    }
                                },false);
                    }

                }


            });
        }
    }

    /*private void showQiDingLiangPop(String qidingliang1,String qidingliang2,String qidingliang3,String qidingliangjiage1,String qidingliangjiage2,String qidingliangjiage3,TextView spming) {
        View view = View.inflate(mContext, R.layout.pop_gouwuche_qidingliang, null);
        PopupWindow qidingliangPop = new PopupWindow(view);
        WindowManager wm1 = MainActivity.instance.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        qidingliangPop.setWidth(AppUtil.dip2px(170));
        qidingliangPop.setHeight(AppUtil.dip2px(70));
        TextView tv_qidingliang1 = view.findViewById(R.id.tv_qidingliang);
*//*        TextView tv_qidingliang2 = view.findViewById(R.id.tv_qidingliang2);
        TextView tv_qidingliang3 = view.findViewById(R.id.tv_qidingliang3);*//*
        TextView tv_qidingliangjiage1 = view.findViewById(R.id.tv_qidingliangjiage1);
*//*        TextView tv_qidingliangjiage2 = view.findViewById(R.id.tv_qidingliangjiage2);
        TextView tv_qidingliangjiage3 = view.findViewById(R.id.tv_qidingliangjiage3);*//*
        Log.e("qidingliang", qidingliang1+"qidingliang:------------------" );
        if (!StringUtil.isEmpty(qidingliang1)){
            tv_qidingliang1.setText(qidingliang1+"<");
        }
        *//*if (!StringUtil.isEmpty(qidingliang2)){
            if (!StringUtil.isEmpty(qidingliang3)){
                tv_qidingliang2.setText(qidingliang2+"-"+qidingliang3);
            }else{
                tv_qidingliang2.setText(qidingliang2);
            }

        }
        if (!StringUtil.isEmpty(qidingliang3)){
            tv_qidingliang3.setText(qidingliang3+"<");
        }*//*
        if (!StringUtil.isEmpty(qidingliangjiage1)){
            tv_qidingliangjiage1.setText("¥"+qidingliangjiage1);
        }
        *//*if (!StringUtil.isEmpty(qidingliangjiage2)){
            tv_qidingliangjiage2.setText("¥"+qidingliangjiage2);
        }
        if (!StringUtil.isEmpty(qidingliangjiage3)){
            tv_qidingliangjiage3.setText("¥"+qidingliangjiage3);
        }*//*
        qidingliangPop.setOutsideTouchable(true);
        qidingliangPop.setBackgroundDrawable(new BitmapDrawable());
        qidingliangPop.showAsDropDown(spming);
    }*/


    //修改购物车数量
    private void editcar(String shoppingid,String shuliang) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .editcar(PreferenceUtils.getString(MyApplication.mContext, "token",""),shoppingid,3+"",shuliang))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
//                        ToastUtil.showToast("修改成功");
                    }
                },false);
    }

    public List<GWCShangPinBean.ShoppingBean> getmList(){
    return mList;
}
public void setmList(List<GWCShangPinBean.ShoppingBean> list){
    mList=list;
    notifyDataSetChanged();
}

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_danxuan)
        ImageView ivDanxuan;
        @BindView(R.id.iv_tu)
        ImageView ivTu;
        @BindView(R.id.tv_name)
        TextView tvName;
/*        @BindView(R.id.tv_guige)
        TextView tvGuige;*/
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_jianhao)
        TextView tvJianhao;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_jiahao)
        TextView tvJiahao;
/*        @BindView(R.id.ll_qidingliang)
        LinearLayout llQidingliang;*/
        @BindView(R.id.iv_zoushi)
        ImageView ivZoushi;
        @BindView(R.id.tv_qidingliang)
        TextView tvQidingliang;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    }

