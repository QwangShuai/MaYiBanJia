package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.GWCDianPuShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.GouwucheDianpuBean;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.TuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.GouWuCheActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.GouWuCheFragment;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18.
 */

public class GouWuCheAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int SHANGPIN = 0xff00;
    public static final int WEINITUIJIAN = 0xff02;
    private Context mContext;
    private ArrayList<String> shijilist;
    private List<GouwucheDianpuBean> shangpindata;
    private List<TuiJianBean> weinituijiandata;
    private GWCDianPuAdapter dianpuadapter;
    private GWCWeiNiTuiJianAdapter weinituijianadapter;
    private GouWuCheFragment gouWuCheFragment;
    private GouWuCheActivity activity;
    private boolean isSelected;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;
    private boolean isTeshu;

    public GouWuCheAdapter(Context context, List<GouwucheDianpuBean> shangpindata, List<TuiJianBean> weinituijiandata, GouWuCheFragment gouWuCheFragment) {
        this.mContext = context;
        this.shangpindata = shangpindata;
        this.weinituijiandata = weinituijiandata;
        this.gouWuCheFragment = gouWuCheFragment;
        shijilist=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            shijilist.add(i + "");
        }
    }

    public GouWuCheAdapter(Context context, List<GouwucheDianpuBean> shangpindata, List<TuiJianBean> weinituijiandata, GouWuCheActivity activity) {
        this.mContext = context;
        this.shangpindata = shangpindata;
        this.weinituijiandata = weinituijiandata;
        this.activity = activity;
        shijilist=new ArrayList<>();
        this.isTeshu = true;
        for (int i = 0; i < 2; i++) {
            shijilist.add(i + "");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SHANGPIN:
                View inflate;
                if (shangpindata.size()>0){
                     inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_youshangpin, parent, false);
                    return new ShangPin(inflate);
                }else{
                    inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_wushangpin, parent, false);
                    return new WuShangPin(inflate);
                }

            case WEINITUIJIAN:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_weinituijian, parent, false);
                return new WeiNiTuiJian(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShangPin) {
            bindShangPin((ShangPin) holder, position);
        }
        else if (holder instanceof WuShangPin) {
            bindWuShangPin ((WuShangPin) holder, position);
        }
        else if (holder instanceof WeiNiTuiJian) {
            bindWeiNiTuiJian ((WeiNiTuiJian) holder, position);
        }
        else {

        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return SHANGPIN;
            case 2:
                return WEINITUIJIAN;
            default:
                return WEINITUIJIAN;
        }
    }

    @Override
    public int getItemCount() {
        return shijilist.size();
    }


    /*adapter适配器中的方法*/
    //全选
    public void allCheck(boolean isCheck)
    {
//        //通过遍历所有的集合,修改bean类来控制CheckBox的选中状态
        isSelected=isCheck;
//        dianpuadapter.setSelected(isSelected);
        for (int i=0;i<shangpindata.size();i++){
            shangpindata.get(i).setSelect(isSelected);
            for (int j=0;j<shangpindata.get(i).getSplist().size();j++){
                shangpindata.get(i).getSplist().get(j).setSelect(isSelected);
            }
            dianpuadapter.notifyDataSetChanged();
        }

        //刷新适配器
        notifyDataSetChanged();
    }



    public GWCDianPuAdapter getDianpuadapter() {
        return dianpuadapter;
    }

    public void setDianpuadapter(GWCDianPuAdapter dianpuadapter) {
        this.dianpuadapter = dianpuadapter;
    }

    //有商品
    private void bindShangPin(final ShangPin holder, int position) {
        Log.e("有商品","有商品");

        if (dianpuadapter == null) {
            if(isTeshu){
                dianpuadapter = new GWCDianPuAdapter(mContext, shangpindata,activity);
            } else {
                dianpuadapter = new GWCDianPuAdapter(mContext, shangpindata,gouWuCheFragment);
            }

        }
        holder.rv_youshangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rv_youshangpin.setAdapter(dianpuadapter);
        dianpuadapter.setOnItemClickListener(new GWCDianPuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position, List<GouwucheDianpuBean> dianpulist) {
                for (int i = 0; i < shangpindata.size(); i++) {
                    if (!shangpindata.get(i).isSelect()) {
//                        //遍历一级列表数据
//                            //有一个未被选中,则全选按钮不会被选中
                        isSelected=false;
                        if(isTeshu){
                            activity.getIvQuanxuan().setSelected(isSelected);
                            activity.setSelect(isSelected);
                        } else {
                            gouWuCheFragment.getIvQuanxuan().setSelected(isSelected);
                            gouWuCheFragment.setSelect(isSelected);
                        }
                        return;
//                        //如果都被选中,则全选按钮选中
                    }
                }
                isSelected=true;
                if(isTeshu){
                    activity.getIvQuanxuan().setSelected(isSelected);
                    activity.setSelect(isSelected);
                } else {
                    gouWuCheFragment.getIvQuanxuan().setSelected(isSelected);
                    gouWuCheFragment.setSelect(isSelected);
                }
            }
        });


    }
    //无商品
    private void bindWuShangPin(WuShangPin holder, int p) {
        Log.e("无商品","无商品");
    }
    //为你推荐
    private void bindWeiNiTuiJian(WeiNiTuiJian holder, int position) {

        if (weinituijianadapter == null) {
            weinituijianadapter = new GWCWeiNiTuiJianAdapter(mContext, weinituijiandata);
        }
        holder.rv_weinituijian.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rv_weinituijian.setAdapter(weinituijianadapter);
        weinituijianadapter.setOnItemClickListener(new GWCWeiNiTuiJianAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.iv_addcar:
                        //添加购物车
                        final TuiJianBean data = weinituijiandata.get(position);
                        String spguige = "";
                        String guigeid = "";
//                        Log.e("data.getChoose_specifications()",data.getChoose_specifications()+"==");
//                        switch (Integer.parseInt(data.getChoose_specifications())){
//                            case 1:
//                                spguige =data.getPack_standard_one_name();
//                                guigeid = data.getPack_standard_one();
//                                break;
//                            case 2:
//                                spguige =data.getPack_standard_two_name();
//                                guigeid = data.getPack_standard_two();
//                                break;
//                            case 3:
//                                spguige =data.getPack_standard_tree_name();
//                                guigeid = data.getPack_standard_tree();
//                                break;
//                        }
                        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
                        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
                        jiarugouwuchedialog.showDialog(data.getInventory(),data.getClassify_name(), data.getSpec_describe(),data.getRation_one()+"",data.getPrice()+""
                                ,data.getPicture_url()+"");
                        final String finalGuigeid = guigeid;
                        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                                    if (Integer.parseInt(shuliang)>=Integer.parseInt(data.getRation_one())){
                                        addcar(data.getCommodity_id(),shuliang,data.getCompany_id(),"", finalGuigeid);
                                    }else{
                                        ToastUtil.showToast("不够起订量");
                                    }
                                jiarugouwuchedialog.getEtShuliang().setText("0");
                                jiarugouwuchedialog.cancel();

                            }
                        });
                        break;
                }
            }
        });


    }
    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid, String gouwucheid, String guigeid) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addcar(PreferenceUtils.getString(MyApplication.mContext, "token",""),spid,shuliang,dianpuid,gouwucheid,guigeid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("添加购物车成功");
                        if(isTeshu){
                            activity.setShuaxin();
                        } else {
                            gouWuCheFragment.setShuaxin();
                        }

                    }
                });
    }

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
                        ToastUtil.showToast("修改成功");
                        if (isTeshu){
                            activity.setShuaxin();
                        } else {
                            gouWuCheFragment.setShuaxin();
                        }

                    }
                });
    }


    public class ShangPin extends RecyclerView.ViewHolder {

        public RecyclerView rv_youshangpin;


        public ShangPin(final View itemView) {
            super(itemView);
            rv_youshangpin = (RecyclerView) itemView.findViewById(R.id.rv_youshangpin);
        }
    }

    public class WuShangPin extends RecyclerView.ViewHolder {
        public WuShangPin(final View itemView) {
            super(itemView);
        }
    }
    public class WeiNiTuiJian extends RecyclerView.ViewHolder {

        public RecyclerView rv_weinituijian;


        public WeiNiTuiJian(final View itemView) {
            super(itemView);
            rv_weinituijian =  (RecyclerView) itemView.findViewById(R.id.rv_weinituijian);
        }
    }



    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
