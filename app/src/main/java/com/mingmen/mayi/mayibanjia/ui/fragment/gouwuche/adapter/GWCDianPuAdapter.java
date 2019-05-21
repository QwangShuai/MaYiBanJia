package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheDianpuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.GouWuCheActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import it.sephiroth.android.library.easing.Linear;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class GWCDianPuAdapter extends RecyclerView.Adapter<GWCDianPuAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<GouwucheDianpuBean> dianpulist;
    private OnItemClickListener mOnItemClickListener;
    private GouWuCheFragment gouWuCheFragment;
    private GouWuCheActivity activity;
    private boolean isTeshu;
    private GWCShangPinAdapter shangpinadapter;
    private List<GouwucheDianpuBean.SplistBean> shangpinlist;
    private ConfirmDialog confirmDialog;

    public GWCDianPuAdapter(Context mContext, List<GouwucheDianpuBean> list, GouWuCheFragment gouWuCheFragment) {
        this.mContext = mContext;
        this.dianpulist = list;
        this.gouWuCheFragment = gouWuCheFragment;
    }

    public GWCDianPuAdapter(Context mContext, List<GouwucheDianpuBean> list, GouWuCheActivity activity) {
        this.mContext = mContext;
        this.dianpulist = list;
        this.isTeshu = true;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position, List<GouwucheDianpuBean> dianpulist);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public GWCDianPuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_dianpu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        final GouwucheDianpuBean datas = dianpulist.get(position);
        shangpinlist = datas.getSplist();
//        holder.tv_name.setText(string.getTitle());
//        holder.tv_laizi.setText(string.getCat_name());
        holder.iv_jishida.setVisibility(View.GONE);
        holder.tv_dianming.setText(datas.getCompany_name());
        holder.tv_dianpushichang.setText(datas.getMarket_name());
        holder.iv_danxuan.setSelected(datas.isSelect());

        if (StringUtil.isValid(datas.getRealtime()) && datas.getRealtime().equals("0")) {
            holder.iv_jishida.setVisibility(View.VISIBLE);
        }
        if (isTeshu) {
            shangpinadapter = new GWCShangPinAdapter(mContext, shangpinlist,  activity);
        } else {
            shangpinadapter = new GWCShangPinAdapter(mContext, shangpinlist, gouWuCheFragment);
        }


        holder.ll_jindian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jindian = new Intent(mContext, DianPuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dianpuid", datas.getCompany_id());
                jindian.putExtras(bundle);
                mContext.startActivity(jindian);
            }
        });

// 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu rightMenuleftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
                deleteItem.setText("删除");
                deleteItem.setTextColor(mContext.getResources().getColor(R.color.white));
                deleteItem.setTextSize(15);
                deleteItem.setHeight(MATCH_PARENT);
                deleteItem.setWidth(200);
//                deleteItem.setWidth(getWindowManager().getDefaultDisplay().getWidth() * 1 / 6);
                deleteItem.setBackground(R.color.red_ff3300);
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。


//                shanchu();
//                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
//                leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。
                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
//                data.remove(adapterPosition);
//                adapter.notifyDataSetChanged();
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                confirmDialog.showDialog("是否确定删除商品");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                .delgwc(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "1", datas.getSplist().get(adapterPosition).getShopping_id()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        if (isTeshu) {
                                            activity.setShuaxin();

                                        } else {
                                            gouWuCheFragment.setShuaxin();
                                        }

                                        if(MainActivity.instance!=null){
                                            MainActivity.instance.getGwcNo();
                                        }
                                    }
                                }, false);
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });

            }
        };

        // 设置监听器。
        holder.rv_shangpin.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        holder.rv_shangpin.setSwipeMenuItemClickListener(mMenuItemClickListener);
        holder.rv_shangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rv_shangpin.setAdapter(shangpinadapter);
//        if (mOnItemClickListener != null) {
            holder.iv_danxuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.setSelect(!datas.isSelect());
                    holder.iv_danxuan.setSelected(datas.isSelect());
                    Log.e("onClick: ",position+"---" );
//                    dianpulist.get(position).setSelect(datas.isSelect());
                    if (isTeshu) {
//                        for (int i = 0; i < dianpulist.get(position).getSplist().size(); i++) {
//                            if (activity.isGuanli()) {
//                                activity.allCheck(datas.isSelect(),position);
//                            } else {
//                                if (!dianpulist.get(position).getSplist().get(i).getCommodity_state().equals("1")) {
                                    activity.allCheck(datas.isSelect(),position);
//                                } else {
//                                    ToastUtil.showToastLong("包含已下架商品，请及时删除");
//                                }
//                            }
//                        }

                    } else {
                        gouWuCheFragment.allCheck(datas.isSelect(),position);
                    }
                    mOnItemClickListener.onClick(v, position, dianpulist);
                    if (isTeshu) {
                        activity.getalllist();
                        if(!activity.isGuanli()){
                            activity.getZongJia();
                        } else {
                            return;
                        }

                    } else {
                        gouWuCheFragment.getalllist();
                        if(!gouWuCheFragment.isGuanli()){
                            gouWuCheFragment.getZongJia();
                        } else {
                            return;
                        }

                    }

                }
            });
//        }
        shangpinadapter.setOnItemClickListener(new GWCShangPinAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position1, List<GouwucheDianpuBean.SplistBean> mList) {
                if (isTeshu) {
                    activity.getalllist();
                    if (!activity.isGuanli()) {
                        activity.getZongJia();
                    }
                    for (int i = 0; i < mList.size(); i++) {
                        if (!mList.get(i).isSelect()) {
//                        //遍历一级列表数据
//                            //有一个未被选中,则全选按钮不会被选中
                            holder.iv_danxuan.setSelected(false);
                            dianpulist.get(position).setSelect(false);
                            activity.setSelect(false);
                            activity.getIvQuanxuan().setSelected(false);
                            return;
                        }
                    }
                    //如果都被选中,则全选按钮选中
                    holder.iv_danxuan.setSelected(true);
                    dianpulist.get(position).setSelect(true);
                    activity.setSelect(isquanxuan());
                    activity.getIvQuanxuan().setSelected(isquanxuan());
                } else {
                    gouWuCheFragment.getalllist();
                    if (!gouWuCheFragment.isGuanli()) {
                        gouWuCheFragment.getZongJia();
                    }
                    for (int i = 0; i < mList.size(); i++) {
                        if (!mList.get(i).isSelect()) {
//                        //遍历一级列表数据
//                            //有一个未被选中,则全选按钮不会被选中
                            holder.iv_danxuan.setSelected(false);
                            dianpulist.get(position).setSelect(false);
                            gouWuCheFragment.setSelect(false);
                            gouWuCheFragment.getIvQuanxuan().setSelected(false);
                            return;
                        }
                    }
                    //如果都被选中,则全选按钮选中
                    holder.iv_danxuan.setSelected(true);
                    dianpulist.get(position).setSelect(true);
                    gouWuCheFragment.setSelect(isquanxuan());
                    gouWuCheFragment.getIvQuanxuan().setSelected(isquanxuan());
                }


            }
        });
    }

    //全选
    public boolean isquanxuan() {
        for (int i = 0; i < dianpulist.size(); i++) {
            if (!dianpulist.get(i).isSelect()) {
                return false;
            } else {

            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return dianpulist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_danxuan;
        ImageView iv_jishida;
        TextView tv_dianming;
        TextView tv_dianpushichang;
        LinearLayout ll_jindian;
        SwipeMenuRecyclerView rv_shangpin;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ll_jindian = view.findViewById(R.id.ll_jindian);
            iv_danxuan = (ImageView) view.findViewById(R.id.iv_danxuan);
            iv_jishida = (ImageView) view.findViewById(R.id.iv_jishida);
            tv_dianming = (TextView) view.findViewById(R.id.tv_dianming);
            tv_dianpushichang = (TextView) view.findViewById(R.id.tv_dianpushichang);
            rv_shangpin = (SwipeMenuRecyclerView) view.findViewById(R.id.rv_shangpin);
        }
    }

}
