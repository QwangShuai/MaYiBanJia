package com.mingmen.mayi.mayibanjia.ui.fragment.shouye.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.ShouYeBannerBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeShangChangBean;
import com.mingmen.mayi.mayibanjia.bean.ShouYeTeJiaBean;
import com.mingmen.mayi.mayibanjia.ui.activity.HuoDongActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.QuanBuShiChangActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ZhuCeCanTingActivity;
import com.mingmen.mayi.mayibanjia.ui.view.GlideImageLoader;
import com.mingmen.mayi.mayibanjia.ui.view.GlideImageYuanLoader;
import com.mingmen.mayi.mayibanjia.ui.view.PageIndicatorView;
import com.mingmen.mayi.mayibanjia.ui.view.ZiXunPagingScrollHelper;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.HorizontalPageLayoutManager;
import com.mingmen.mayi.mayibanjia.utils.custom.zixun.PagingScrollHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18.
 */

public class ShouYeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int BANNER = 0xff00;
    public static final int BIAOQIAN = 0xff02;
    public static final int SHANGJIA = 0xff01;
    public static final int SHISHIDA = 0xff03;
    public static final int TEJIA = 0xff04;
    public static final int TUIJIAN = 0xff05;
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    private ZiXun myHolder;
    private TuiJian tuijianHolder;
    private Shishida ssdHolder;
    private ShangJia mMyViewHolder;
    private Context mContext;
    private View inflate;
    private ArrayList<String> shijilist;
    private ShouYeTeJiaAdapter shiJiBiaoQianAdapter;
    private ShouYeTeJiaAdapter tuijianAdapter;
    private List<String> imgs = new ArrayList<>();
    private List<ShouYeBannerBean> bannerBean;
    private List<FCGName> leiBean;
    private List<ShouYeTeJiaBean> teJiaBean;
    private List<ShouYeTeJiaBean> tuijianBean;
    private List<ShouYeShangChangBean> shangJiaBean;
    private MainActivity activity;
    private String dingwei = "";
    private String province = "";

    //    public ShouYeAdapter(Context context, List<ShouYeBannerBean> bannerBean,List<ShouYeLeiBean> leiBean,List<ShouYeTeJiaBean> teJiaBean,MainActivity activity) {
    public ShouYeAdapter(Context context, List<ShouYeBannerBean> bannerBean, List<ShouYeShangChangBean> shangJiaBean,
                         List<FCGName> leiBean, List<ShouYeTeJiaBean> teJiaBean,List<ShouYeTeJiaBean> tuijianBean, MainActivity activity,
                         String dingwei, String province) {
        this.mContext = context;
        this.bannerBean = bannerBean;
        this.leiBean = leiBean;
        this.shangJiaBean = shangJiaBean;
        this.teJiaBean = teJiaBean;
        this.tuijianBean = tuijianBean;
        this.activity = activity;
        this.dingwei = dingwei;
        this.province = province;
        shijilist = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            shijilist.add(i + "");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_banner, parent, false);
                return new Banner(inflate);
            case SHANGJIA:
                mMyViewHolder = new ShangJia(LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_shangjia, parent, false));
                return mMyViewHolder;
            case BIAOQIAN:
                myHolder = new ZiXun(LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_biaoqian, parent, false));
                return myHolder;
            case SHISHIDA:
                ssdHolder = new Shishida(LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_shishida, parent, false));
                return ssdHolder;
            case TUIJIAN:
                 tuijianHolder = new TuiJian(LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_tuijian, parent, false));
                return tuijianHolder;
            case TEJIA:
                inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_item_tejia, parent, false);
                return new TeJia(inflate);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Banner) {
            bindBanner((Banner) holder, position);
        } else if (holder instanceof ShangJia) {
            bindShangjia((ShangJia) holder, position);
        } else if (holder instanceof ZiXun) {
            bindZiXun((ZiXun) holder, position);
        } else if (holder instanceof Shishida) {
            bindShishida((Shishida) holder, position);
        }  else if (holder instanceof TeJia) {
            bindTejia((TeJia) holder, position);
        } else {
            bindTuiJian((TuiJian) holder,position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER;
            case 1:
                return SHANGJIA;
            case 2:
                return BIAOQIAN;
            case 3:
                return SHISHIDA;
            case 4:
                return TEJIA;
            case 5:
                return TUIJIAN;
            default:
                return TEJIA;
        }
    }

    @Override
    public int getItemCount() {
        return shijilist == null ? 0 : shijilist.size();
    }


    //banner
    private void bindBanner(Banner holder, int position) {
        Log.e("sssss", "banner");
        for (int i = 0; i < bannerBean.size(); i++) {
            imgs.add(bannerBean.get(i).getPicture_address());
        }
        //设置banner样式
        holder.banner_home_lunbo.setBannerStyle(6);
        //设置图片集合
        holder.banner_home_lunbo.setImages(imgs);
        holder.banner_home_lunbo.setImageLoader(new GlideImageYuanLoader());
        //设置banner动画效果
        holder.banner_home_lunbo.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        holder.banner_home_lunbo.isAutoPlay(true);
        //设置轮播时间
        holder.banner_home_lunbo.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        holder.banner_home_lunbo.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        holder.banner_home_lunbo.start();
        holder.banner_home_lunbo.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent it = new Intent(mContext, HuoDongActivity.class);
                it.putExtra("url", bannerBean.get(position).getAddress());
                it.putExtra("title", "活动详情");
                mContext.startActivity(it);
//                Log.e("bannerzt",mHomeBean.getBanner().get(position).getZt()+"zt");
//                Log.e("bannerlink",mHomeBean.getBanner().get(position).getAp_link()+"link");
//                tiaozhuan(mHomeBean.getBanner().get(position).getZt(),mHomeBean.getBanner().get(position).getAp_link());
            }
        });
    }

    //标签
    private void bindZiXun(final ZiXun holder, int position) {
//        Log.e("sssss", "zixun");
////        if(shiJiBean.getZixun().size()!=0){
//        //滑动卡片
//        List<List<FCGName>> datas = new ArrayList<>();
////        datas.addAll(leiBean);
//        int count = 0;
//        int mysize = 0;
//        int minsize = 0;
//        mysize = leiBean.size() / 8;
//        minsize = leiBean.size() % 8;
//        if (minsize != 0) {
//            mysize++;
//        }
//        for (int i = 0; i < mysize; i++) {
//            List<FCGName> list = new ArrayList<>();
//            if (i < mysize - 1) {
//                for (int j = 0; j < 8; j++) {
//                    list.add(leiBean.get(count));
//                    count++;
//                }
//            } else {
//                for (int j = 0; j < minsize; j++) {
//                    list.add(leiBean.get(count));
//                    count++;
//                }
//            }
//
//            datas.add(list);
//            Log.e("长度", datas.size() + "");
//        }
        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        HorizontalPageLayoutManager hLinearLayoutManager = null;
//        final int horizontal = LinearLayoutManager.HORIZONTAL;
        hLinearLayoutManager = new HorizontalPageLayoutManager(2, 5);
//        holder.recycler_view.setLayoutManager(new LinearLayoutManager(mContext, horizontal, false));
        holder.recycler_view.setLayoutManager(hLinearLayoutManager);
        ShouYeLeiAdapter adapter = new ShouYeLeiAdapter(mContext, leiBean, activity);
        holder.recycler_view.setAdapter(adapter);
        holder.recycler_view.setHasFixedSize(true);
        scrollHelper.setUpRecycleView(holder.recycler_view);
        scrollHelper.setIndicator(holder.indicator);
        scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                holder.indicator.setSelectedPage(index);
            }
        });
        holder.recycler_view.setHorizontalScrollBarEnabled(true);
        scrollHelper.setAdapter(adapter,0);
        scrollHelper.updateLayoutManger();
        scrollHelper.scrollToPosition(0);
        adapter.notifyDataSetChanged();
//        ZiXunPagingScrollHelper rp = new ZiXunPagingScrollHelper();
//        rp.setIndicator(holder.indicator);
//        rp.setUpRecycleView(holder.recycler_view);
//        rp.setAdapter(adapter, 0);
//        rp.setOnPageChangeListener(new ZiXunPagingScrollHelper.onPageChangeListener() {
//            @Override
//            public void onPageChange(int index) {
//                holder.indicator.setSelectedPage(index);
//            }
//        });

    }

    private void bindShangjia(final ShangJia holder, int position) {
        holder.tvGys.setText(shangJiaBean.get(0).getCompany_gy_sum() + "");
        holder.tvCt.setText(shangJiaBean.get(0).getCompany_ct_sum() + "");
        holder.tvSc.setText(shangJiaBean.get(0).getMarket_count() + "");
        final Bundle bundle = new Bundle();
        bundle.putString("province", province);
        bundle.putString("city", dingwei);
        holder.llGys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.Jump_intent(mContext, ShangJiaActivity.class, bundle);
            }
        });
        holder.llSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.Jump_intent(mContext, QuanBuShiChangActivity.class, bundle);
            }
        });
        holder.llCt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtil.Jump_intent(mContext, ZhuCeCanTingActivity.class, bundle);
            }
        });
//    holder.btnCaigou.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            activity.gaibianye(1);
//        }
//    });
    }

    //特价
    private void bindTejia(TeJia holder, int position) {
        Log.e("sssss", "tejia");
//

        if (teJiaBean.size() == 0) {
            holder.ll_kuang.setVisibility(View.GONE);
        } else {
            if (shiJiBiaoQianAdapter == null) {
                shiJiBiaoQianAdapter = new ShouYeTeJiaAdapter(mContext, teJiaBean);
            }
            holder.rv_tejia.setLayoutManager(new GridLayoutManager(mContext, 3));
            holder.rv_tejia.setAdapter(shiJiBiaoQianAdapter);
            holder.rv_tejia.setFocusable(false);
            holder.ll_more.setOnClickListener(new View.OnClickListener() {//更多特价
                @Override
                public void onClick(View v) {
//                    Map<String,Boolean> map = new HashMap<>();
//                    map.put("isTejia",true);
//                    EventBus.getDefault().post(map);
                    activity.setTejia(true);
                    activity.gaibianye(1);
                }
            });
        }

    }
    //推荐
    private void bindTuiJian(TuiJian holder, int position) {

        if (tuijianBean.size() != 0) {
            if (tuijianAdapter == null) {
                tuijianAdapter = new ShouYeTeJiaAdapter(mContext, tuijianBean);
            }
            holder.rv_tuijian.setLayoutManager(new GridLayoutManager(mContext, 3));
            holder.rv_tuijian.setAdapter(tuijianAdapter);
            holder.rv_tuijian.setFocusable(false);
        }

    }
    private void bindShishida(Shishida holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//筛选实时达
//                Map<String,Boolean> map = new HashMap<>();
//                map.put("isZhunshida",true);
//                EventBus.getDefault().post(map);
                activity.setZhunshida(true);
                activity.gaibianye(1);
            }
        });
    }
    public class TeJia extends RecyclerView.ViewHolder {

        public RecyclerView rv_tejia;
        public LinearLayout ll_kuang;
        public LinearLayout ll_more;

        public TeJia(final View itemView) {
            super(itemView);
            rv_tejia = (RecyclerView) itemView.findViewById(R.id.rv_tejia);
            ll_kuang = itemView.findViewById(R.id.ll_kuang);
            ll_more = itemView.findViewById(R.id.ll_more);
        }
    }
    public class TuiJian extends RecyclerView.ViewHolder {

        public RecyclerView rv_tuijian;

        public TuiJian(final View itemView) {
            super(itemView);
            rv_tuijian = (RecyclerView) itemView.findViewById(R.id.rv_tuijian);
        }
    }
    public class Banner extends RecyclerView.ViewHolder {
        private com.youth.banner.Banner banner_home_lunbo;

        public Banner(View itemView) {
            super(itemView);
            banner_home_lunbo = (com.youth.banner.Banner) itemView.findViewById(R.id.shouye_banner);
        }
    }

    public class ZiXun extends RecyclerView.ViewHolder {
        private RecyclerView recycler_view;
        private PageIndicatorView indicator;

        public ZiXun(View itemView) {
            super(itemView);
            indicator = (PageIndicatorView) itemView.findViewById(R.id.indicator);
            recycler_view = (RecyclerView) itemView.findViewById(R.id.recycler_view);

        }
    }

    public class ShangJia extends RecyclerView.ViewHolder {
        private TextView tvGys;
        private TextView tvCt;
        private TextView tvSc;
        private LinearLayout llSc;
        private LinearLayout llCt;
        private LinearLayout llGys;
//    private Button btnCaigou;

        public ShangJia(View itemView) {
            super(itemView);
            tvGys = (TextView) itemView.findViewById(R.id.tv_gys);
            tvCt = (TextView) itemView.findViewById(R.id.tv_ct);
            tvSc = (TextView) itemView.findViewById(R.id.tv_sc);
            llSc = (LinearLayout) itemView.findViewById(R.id.ll_sc);
            llCt = (LinearLayout) itemView.findViewById(R.id.ll_ct);
            llGys = (LinearLayout) itemView.findViewById(R.id.ll_gys);
//        btnCaigou=(Button) itemView.findViewById(R.id.btn_caigou);

        }
    }

    public class Shishida extends RecyclerView.ViewHolder{
        public Shishida(View itemView) {
            super(itemView);
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

    public void setPosition(int pos){
        myHolder.indicator.setSelectedPage(pos);
    }

}
