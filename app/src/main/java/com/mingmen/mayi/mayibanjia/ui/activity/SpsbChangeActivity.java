package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyGridViewAdpter;
import com.mingmen.mayi.mayibanjia.utils.custom.lable.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class SpsbChangeActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.points)
    LinearLayout points;
//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
//    @BindView(R.id.ll_rv)
//    LinearLayout llRv;
//    @BindView(R.id.bt_xiayibu)
//    Button btXiayibu;
//    @BindView(R.id.indicator)
//    PageIndicatorView indicator;

    private Context mContext;

    //测试全部菜品分类标签
//    PagingScrollHelper scrollHelper = new PagingScrollHelper();
//    private QuanBuCaiPinLeiOneAdapter adapter;
    private List<FCGName> mlist = new ArrayList<>();
    //    private HorizontalPageLayoutManager hLinearLayoutManager = null;
//    private PagingItemDecoration pagingItemDecoration  = null;
    private String yclId = "346926195929448587b078e7fe613530";
    private String xzid = "";
    private View[] ivPoints;//小圆点图片的集合
    private int totalPage; //总的页数
    private int mPageSize = 10; //每页显示的最大的数量
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private MyViewPagerAdapter adapter;
    private List<MyGridViewAdpter> adpters = new ArrayList<>();
    //private int currentPage;//当前页
    private int dotSize = 10;
    private int margins = 10;
    LinearLayout.LayoutParams params;

    @Override
    public int getLayoutId() {
        return R.layout.activity_spsb_change;
    }

    @Override
    protected void initData() {
        mContext = SpsbChangeActivity.this;
        dotSize = AppUtil.Dp2px(mContext, dotSize);
        margins = AppUtil.Dp2px(mContext, margins);
        params = new LinearLayout.LayoutParams(dotSize, dotSize);
        params.setMargins(margins, margins, margins, margins);
        getShouyeFenLei(yclId, "2");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                break;
            case R.id.bt_xiayibu:
                break;
        }
    }


    private void initViewLable() {//测试显示标签
//        adapter = new QuanBuCaiPinLeiOneAdapter(mContext,mList);
//        recyclerView.setAdapter(adapter);
//        scrollHelper.setUpRecycleView(recyclerView);
//        scrollHelper.setIndicator(indicator);
//        hLinearLayoutManager = new HorizontalPageLayoutManager(2, 5);
//        pagingItemDecoration = new PagingItemDecoration(mContext, hLinearLayoutManager);
//        recyclerView.setLayoutManager(hLinearLayoutManager);
//        scrollHelper.setAdapter(adapter,2);
//        scrollHelper.updateLayoutManger();
//        scrollHelper.scrollToPosition(0);
//        scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
//            @Override
//            public void onPageChange(int index) {
//                indicator.setSelectedPage(index);
//            }
//        });
//        recyclerView.setHorizontalScrollBarEnabled(true);


        totalPage = (int) Math.ceil(mlist.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<View>();
        for (int i = 0; i < totalPage; i++) {
            //每个页面都是inflate出一个新实例
            MyGridViewAdpter myGridViewAdpter = new MyGridViewAdpter(this, mlist, i, mPageSize);
            adpters.add(myGridViewAdpter);
            final GridView gridView = (GridView) View.inflate(this, R.layout.item_grid, null);
            gridView.setAdapter(adpters.get(i));
            //添加item点击监听
            gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Object obj = gridView.getItemAtPosition(position);
                    if (obj != null && obj instanceof FCGName) {
                        System.out.println(obj);
                        ToastUtil.showToastLong(((FCGName)obj).getClassify_name());
                        for (MyGridViewAdpter myadapter: adpters) {
                            myadapter.setXzid(((FCGName)obj).getClassify_id());
                            myadapter.notifyDataSetChanged();
                        }
                    }
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        adapter = new MyViewPagerAdapter(viewPagerList);
        //设置ViewPager适配器
        viewpager.setAdapter(adapter);

        //添加小圆点
        ivPoints = new View[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new View(this);
            if (i == 0) {
                ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
            } else {
                ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
            }

//            ivPoints[i].setPadding(8, 8, 8, 8);

            points.addView(ivPoints[i],params);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
                    } else {
                        ivPoints[i].setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
                    }
                }
            }
        });

        if(ivPoints.length>1){
            points.setVisibility(View.VISIBLE);
        } else {
            points.setVisibility(View.GONE);
        }

    }


    private void getShouyeFenLei(String id, final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getFeiLei(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<List<FCGName>>() {

                    @Override
                    public void onNext(List<FCGName> list) {
                        int mysize = list == null ? 0 : list.size();
                        if (mysize != 0) {
                            xzid = list.get(0).getClassify_id();
                            mlist.addAll(list);
                            initViewLable();
//                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToastLong("当前类别暂无品类");
                        }
                    }
                }, false);
    }

}
