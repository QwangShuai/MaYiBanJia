package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.XuanZeDialog;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class JueSeGuanLiAdapter extends RecyclerView.Adapter<JueSeGuanLiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<JueSeBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<TextView> tvs;
    public JueSeGuanLiAdapter(Context mContext, List<JueSeBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void setmList(List<JueSeBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zizhanghu, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final JueSeBean data = mList.get(position);
        holder.tvJuese.setText(data.getPart()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final XuanZeDialog dialog = new XuanZeDialog(mContext,mList.get(position).getSon_role_id(), new XuanZeDialog.CallBack() {
                    @Override
                    public void confirm(String id, String name) {
                        addQuanxian(data.getSon_role_id(),id);
                        holder.tvQuanxian.setText(name);
                    }
                });
                dialog.show();
            }
        });
        if((data.getQxList()==null?0:data.getQxList().size())!=0){
            initShangpinChildViews(holder.xcfQuanxian,data.getQxList());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_juese)
        TextView tvJuese;
        @BindView(R.id.tv_quanxian)
        TextView tvQuanxian;
        @BindView(R.id.xcf_quanxian)
        XCFlowLayout xcfQuanxian;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void addQuanxian(String id,String role_id){
            HttpManager.getInstance()
                    .with(mContext)
                    .setObservable(RetrofitManager.getService()
                            .addJueseQuanxian(PreferenceUtils.getString(MyApplication.mContext, "token", ""),id,role_id))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            ToastUtil.showToast("添加成功");
                        }
                    });
    }

    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo, final List<JueSeBean.QxList> mList) {
        xcfShangpinlishisousuo.removeAllViews();
        tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.size(); i++) {
            TextView view = new TextView(mContext);
            view.setText(mList.get(i).getRoleName());
            view.setTextColor(mContext.getResources().getColor(R.color.zangqing));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
    }
}
