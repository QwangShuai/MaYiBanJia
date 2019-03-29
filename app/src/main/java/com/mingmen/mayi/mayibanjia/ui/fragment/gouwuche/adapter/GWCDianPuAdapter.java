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
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
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

import java.util.List;

import butterknife.ButterKnife;
import it.sephiroth.android.library.easing.Linear;

import static android.widget.ListPopupWindow.MATCH_PARENT;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class GWCDianPuAdapter extends  RecyclerView.Adapter<GWCDianPuAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<GWCDianPuShangPinBean> dianpulist;
    private OnItemClickListener mOnItemClickListener;
    private GouWuCheFragment gouWuCheFragment;
    Gson gson=new Gson();
    private boolean isSelected;
    private List<GWCShangPinBean.ShoppingBean> shangpinlist;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GWCDianPuAdapter(Context mContext, List<GWCDianPuShangPinBean> list, boolean isSelected, GouWuCheFragment gouWuCheFragment) {
        this.mContext = mContext;
        this.dianpulist = list;
        this.isSelected = isSelected;
        this.gouWuCheFragment = gouWuCheFragment;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position, List<GWCDianPuShangPinBean> dianpulist);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public GWCDianPuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_dianpu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GWCDianPuShangPinBean datas = dianpulist.get(position);
//        holder.tv_name.setText(string.getTitle());
//        holder.tv_laizi.setText(string.getCat_name());
//        Glide.with(mContext).load("http://www.zhenlvw.com/"+string.getFile_url())
//                .into(holder.iv_danxuan);
        holder.iv_jishida.setVisibility(View.GONE);
        holder.tv_dianming.setText(datas.getCompany_name());
        holder.tv_dianpushichang.setText(datas.getMarket_name());
        holder.iv_danxuan.setSelected(isSelected);
        shangpinlist = datas.getShangPinBeen();
        if(StringUtil.isValid(datas.getRealtime())&&datas.getRealtime().equals("0")){
            holder.iv_jishida.setVisibility(View.VISIBLE);
        }
        final GWCShangPinAdapter shangpinadapter = new GWCShangPinAdapter(mContext, shangpinlist, isSelected, gouWuCheFragment);

        holder.ll_jindian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jindian=new Intent(mContext,DianPuActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("dianpuid",datas.getCompany_id());
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
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
//                data.remove(adapterPosition);
//                adapter.notifyDataSetChanged();
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .delgwc(PreferenceUtils.getString(MyApplication.mContext, "token",""),"1",datas.getShangPinBeen().get(adapterPosition).getShopping_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                gouWuCheFragment.setShuaxin();
                            }
                        },false);
            }
        };

        // 设置监听器。
        holder.rv_shangpin.setSwipeMenuCreator(mSwipeMenuCreator);
        // 菜单点击监听。
        holder.rv_shangpin.setSwipeMenuItemClickListener(mMenuItemClickListener);
        holder.rv_shangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rv_shangpin.setAdapter(shangpinadapter);
        if (mOnItemClickListener!=null){
            holder.iv_danxuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.setSelected(!datas.isSelected());
                    holder.iv_danxuan.setSelected(datas.isSelected());
                    dianpulist.get(position).setSelected(datas.isSelected());
                    shangpinadapter.setSelected(datas.isSelected());
                    List<GWCShangPinBean.ShoppingBean> splist = shangpinadapter.getmList();
                    for (int i = 0; i <splist.size() ; i++) {
                        if(!splist.get(i).getCommodity_state().equals("1")){
                            if (datas.isSelected()){
                                splist.get(i).setSelected(true);
                            }else{
                                splist.get(i).setSelected(false);
                            }
                        } else {
                            ToastUtil.showToastLong("包含已下架商品，请及时删除");
                        }

                    }
                    shangpinadapter.setmList(splist);
                    gouWuCheFragment.getalllist();
                    gouWuCheFragment.getZongJia();
                    mOnItemClickListener.onClick(v,position,dianpulist);
                }
            });
        }
        shangpinadapter.setOnItemClickListener(new GWCShangPinAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position1, List<GWCShangPinBean.ShoppingBean> mList) {
                dianpulist.get(position).setShangPinBeen(mList);
                gouWuCheFragment.getalllist();
                gouWuCheFragment.getZongJia();
                for (int i = 0; i < mList.size(); i++) {
                    if (!mList.get(i).isSelected()) {
//                        //遍历一级列表数据
//                            //有一个未被选中,则全选按钮不会被选中
                        isSelected = false;
                        holder.iv_danxuan.setSelected(false);
                        dianpulist.get(position).setSelected(isSelected);
                        gouWuCheFragment.setSelect(false);
                        gouWuCheFragment.getIvQuanxuan().setSelected(false);
                        return;
                    }
                }
                //如果都被选中,则全选按钮选中
                isSelected=true;
                holder.iv_danxuan.setSelected(isSelected);
                dianpulist.get(position).setSelected(isSelected);
                gouWuCheFragment.setSelect(isquanxuan());
                gouWuCheFragment.getIvQuanxuan().setSelected(isquanxuan());

            }
        });
    }

    //全选
    public boolean isquanxuan(){
        for (int i = 0; i <dianpulist.size(); i++) {
            if (!dianpulist.get(i).isSelected()) {
                return false;
            } else {

            }
        }
        return true;
    }
    //选中的商品

    public List<GWCDianPuShangPinBean> getdianpulist() {
        return dianpulist;
    }

    public void setdianpulist(List<GWCDianPuShangPinBean> dianpulist) {
        this.dianpulist = dianpulist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  dianpulist.size();
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
            ll_jindian=view.findViewById(R.id.ll_jindian);
            iv_danxuan=(ImageView) view.findViewById(R.id.iv_danxuan);
            iv_jishida=(ImageView) view.findViewById(R.id.iv_jishida);
            tv_dianming=(TextView)view.findViewById(R.id.tv_dianming);
            tv_dianpushichang=(TextView)view.findViewById(R.id.tv_dianpushichang);
            rv_shangpin=(SwipeMenuRecyclerView)view.findViewById(R.id.rv_shangpin);
        }
    }

}
