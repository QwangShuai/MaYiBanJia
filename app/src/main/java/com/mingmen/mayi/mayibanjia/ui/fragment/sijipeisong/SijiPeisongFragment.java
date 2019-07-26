package com.mingmen.mayi.mayibanjia.ui.fragment.sijipeisong;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShaiXuanWuLiuDingdanDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliujingli.JingliAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.wuliusiji.SijiAdapter;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class SijiPeisongFragment extends BaseFragment {
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    ViewPager vpDingdan;
    Unbinder unbinder;

    private View viewSPYXFragment;
    private Context mContext;
    private SijiAdapter adapter;

    private Timer timer;
    private int i = 1;


    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_wuliujingli, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {
        mContext = getActivity();
        if (AppUtil.isConnNet()) {
            stateLayout.showSuccessView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.beijing));
            }
        } else {
            stateLayout.showErrorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                //设置状态栏颜
                window.setStatusBarColor(getResources().getColor(R.color.white));
            }
        }
        initView();
    }

    private void initView() {
        tvTitle.setText("配送信息");
        adapter =new SijiAdapter(getChildFragmentManager(),mContext);
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        vpDingdan.setOffscreenPageLimit(0);
        vpDingdan.setCurrentItem(0);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("run: ","走"+ i );
                i++;
                EventBus.getDefault().post("update");

            }
        },0,60*1000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        timer.cancel();
    }

    @OnClick({R.id.ll_title, R.id.iv_sangedian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title:
                break;
            case R.id.iv_sangedian:
//                ShaiXuanWuLiuDingdanDialog dialog = new ShaiXuanWuLiuDingdanDialog(mContext);
//                dialog.show();
                break;
        }
    }
}
