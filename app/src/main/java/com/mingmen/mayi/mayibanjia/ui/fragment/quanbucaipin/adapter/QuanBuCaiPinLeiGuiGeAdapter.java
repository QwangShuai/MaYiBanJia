package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class QuanBuCaiPinLeiGuiGeAdapter extends RecyclerView.Adapter<QuanBuCaiPinLeiGuiGeAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ShangPinSouSuoBean.ZhengchangBean.ListGG> mList;
    private JiaRuGouWuCheDialog jiarugouwuchedialog;


    public QuanBuCaiPinLeiGuiGeAdapter(Context mContext, List<ShangPinSouSuoBean.ZhengchangBean.ListGG> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guige_qbcp, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShangPinSouSuoBean.ZhengchangBean.ListGG bean = mList.get(position);
        jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        holder.tvGuige.setText(bean.getCommodity_name());
        holder.tvJiage.setText(bean.getPrice());
        holder.ivAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiarugouwuchedialog.showDialog(bean.getInventory(),bean.getCommodity_name(), "", bean.getRation_one() + "", bean.getPrice() + ""
                        , bean.getPicture_url());
                jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                        if (Integer.parseInt(shuliang) >= Integer.parseInt(bean.getRation_one())) {
                            addcar(bean.getCommodity_id(), shuliang, bean.getCompany_id());
                        } else {
                            ToastUtil.showToast("不够起订量");
                        }
                        jiarugouwuchedialog.getEtShuliang().setText("0");
                        jiarugouwuchedialog.cancel();
                        if(MainActivity.instance!=null){
                            MainActivity.instance.getGwcNo();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.iv_addcar)
        ImageView ivAddcar;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid, shuliang, dianpuid, "", ""))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("添加购物车成功");
                    }
                });
    }
}
