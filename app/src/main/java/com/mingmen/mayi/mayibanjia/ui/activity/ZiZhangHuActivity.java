package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.JueSeGuanLiAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ZiZhangHuAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ZiZhangHuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rvList;

    private Context mContext;
    private String company_id="";
    private String company_name="";
    private List<ZiZhangHuBean> mList = new ArrayList<>();
    private ZiZhangHuAdapter adapter;
    private SwipeMenuCreator mSwipeMenuCreator;
    private SwipeMenuItemClickListener mMenuItemClickListener;
    @Override
    public int getLayoutId() {
        return R.layout.activity_zi_zhang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("子账户");
        tvRight.setText("添加");
        mContext = ZiZhangHuActivity.this;
        company_id = getIntent().getStringExtra("id");
        company_name = getIntent().getStringExtra("name");
        mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                deleteItem.setText("删除");
                deleteItem.setBackgroundColor(getResources().getColor(R.color.mayihong));
                deleteItem.setTextSize(18);
                deleteItem.setTextColor(getResources().getColor(R.color.white));
                deleteItem.setHeight(MATCH_PARENT);
                deleteItem.setWidth(AppUtil.dip2px(50));
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
            }
        };

        mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .delJuese(PreferenceUtils.getString(MyApplication.mContext, "token",""),mList.get(adapterPosition).getAccount_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
                                ToastUtil.showToast("删除子账户成功");
                                mList.remove(adapterPosition);
                                adapter.notifyDataSetChanged();
                            }
                        });



            }
        };
        adapter = new ZiZhangHuAdapter(mContext,mList);
        rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvList.setSwipeMenuCreator(mSwipeMenuCreator);
        rvList.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvList.setAdapter(adapter);

        getmoren();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                Bundle zzh  = new Bundle();
                zzh.putString("name",company_name);
                zzh.putString("id",company_id);
                Jump_intent(AddZiZhuangHuActivity.class,zzh);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getmoren();
    }

    private void getmoren() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getZizhanghuList(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<ZiZhangHuBean>>() {
                    @Override
                    public void onNext(List<ZiZhangHuBean> data) {
                        int mysize = data==null?0:data.size();
                        if(mysize!=0){
                            mList.clear();
                            mList.addAll(data);
                            adapter.notifyDataSetChanged();
                        }
                    }
                },true);
    }
}
