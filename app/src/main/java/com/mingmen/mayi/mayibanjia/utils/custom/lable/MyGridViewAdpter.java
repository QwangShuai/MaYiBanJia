package com.mingmen.mayi.mayibanjia.utils.custom.lable;

/**
 * Created by Administrator on 2019/6/6.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * GridView加载数据的适配器
 *
 * @author Administrator
 */
public class MyGridViewAdpter extends BaseAdapter {

    private Context context;
    private List<FCGName> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量
    private String xzid;

    public void setXzid(String xzid) {
        this.xzid = xzid;
        notifyDataSetChanged();
    }

    public MyGridViewAdpter(Context context, List<FCGName> lists,
                            int mIndex, int mPargerSize) {
        this.context = context;
        this.lists = lists;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
    }

    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size() > (mIndex + 1) * mPargerSize ?
                mPargerSize : (lists.size() - mIndex * mPargerSize);
    }

    @Override
    public FCGName getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shouyefenlei, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        holder.tv1.setText(lists.get(pos).getClassify_name() + "");
        holder.tvName.setText(lists.get(pos).getClassify_name() + "");
        GlideUtils.cachePhoto(context, holder.iv1, lists.get(pos).getPicture_url());
        setTextViewColor(holder.tv1,holder.tvName,holder.viewZhezhao, lists.get(pos).getClassify_id());
        //添加item监听
//        convertView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "您点击了"  + lists.get(pos).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv1)
        CircleImageView iv1;
        @BindView(R.id.view_zhezhao)
        View viewZhezhao;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.ll1)
        LinearLayout ll1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private void setTextViewColor(TextView tv, TextView tv_zs, View v, String id) {
        if (id.equals(xzid)) {
            tv.setVisibility(View.GONE);
            tv_zs.setVisibility(View.VISIBLE);
            v.setVisibility(View.VISIBLE);
//            tv.setTextColor(mContext.getResources().getColor(R.color.white));
//            tv.setBackground(mContext.getResources().getDrawable(R.drawable.zangqing_yuan));
        } else {
//            tv.setTextColor(mContext.getResources().getColor(R.color.zicolor));
//            tv.setBackground(mContext.getResources().getDrawable(R.drawable.white_yuan));
            tv.setVisibility(View.VISIBLE);
            tv_zs.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
        }
    }
}