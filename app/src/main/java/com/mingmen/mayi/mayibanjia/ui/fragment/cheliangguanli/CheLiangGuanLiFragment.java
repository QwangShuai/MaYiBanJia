package com.mingmen.mayi.mayibanjia.ui.fragment.cheliangguanli;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CheliangBean;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.AddWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.FenPeiWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ShaiXuanWuLiuCheDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseFragment;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/7/16/016.
 */

public class CheLiangGuanLiFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_sousuo)
    EditText etSousuo;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.rv_dingdan)
    SwipeMenuRecyclerView rvDingdan;
    @BindView(R.id.tv_tishi_left)
    TextView tvTishiLeft;
    @BindView(R.id.tv_tishi_center)
    TextView tvTishiCenter;
    @BindView(R.id.tv_tishi_right)
    TextView tvTishiRight;
    @BindView(R.id.ll_list_null)
    LinearLayout llListNull;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;

    private View viewSPYXFragment;
    private Context mContext;
    private CheliangGuanliAdapter adapter;
    private List<CheliangBean> mList = new ArrayList<>();
    private int ye = 1;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;

    @Override
    protected View getSuccessView() {
        viewSPYXFragment = View.inflate(MyApplication.mContext, R.layout.fragment_cheliangguanli, null);
        ButterKnife.bind(this, viewSPYXFragment);
        return viewSPYXFragment;
    }

    @Override
    protected void loadData() {
        mContext = getActivity();
        tvTitle.setText("车辆管理");
        ivBack.setVisibility(View.GONE);
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
        getCheliang("","","","","");

    }

    private void initView(){
        EventBus.getDefault().register(this);
        adapter = new CheliangGuanliAdapter(mContext,mList,getActivity());
        StringUtil.setInputNoEmoj(etSousuo,24);
        etSousuo.clearFocus();
        etSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getCheliang("",etSousuo.getText().toString().trim(),"","","");
                    return true;
                }
                return false;

            }
        });
        mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
//                ToastUtil.showToastLong("没有了");
            }
        };
        tvTishiLeft.setText("哇~列表里是空的,请前去");
        tvTishiCenter.setText("添加车辆");
        tvTishiRight.setText("吧~");
        rvDingdan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvDingdan.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        refreshLayout.setColorSchemeResources(R.color.zangqing, R.color.zangqing,
                R.color.zangqing, R.color.zangqing);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ye = 1;
                getCheliang("","","","","");
                refreshLayout.setRefreshing(false);
            }
        });
        rvDingdan.setAdapter(adapter);
        rvDingdan.loadMoreFinish(false, true);
    }

    //查询购物车
    public void getCheliang(String new_driver_phone,String new_driver_name,String new_wl_cars_type,String new_plate_number,String cars_type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .cheliangList(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                        new_driver_phone, new_driver_name, new_wl_cars_type, new_plate_number,cars_type))
                .setDataListener(new HttpDataListener<List<CheliangBean>>() {
                    @Override
                    public void onNext(List<CheliangBean> list) {
                        mList.clear();
                        adapter.notifyDataSetChanged();
                        mList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_shaixuan, R.id.ll_list_null,R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_shaixuan:
                ShaiXuanWuLiuCheDialog dialog = new ShaiXuanWuLiuCheDialog(mContext,getActivity());
                dialog.show();
                break;
            case R.id.ll_list_null:
                AddWuLiuCheDialog add_null_dialog = new AddWuLiuCheDialog(mContext,getActivity(),new CheliangBean() );
                add_null_dialog.show();
                break;
            case R.id.btn_add:
                AddWuLiuCheDialog add_dialog = new AddWuLiuCheDialog(mContext,getActivity(),new CheliangBean());
                add_dialog.show();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getList(CheliangBean bean) {
        getCheliang(bean.getNew_driver_phone(),"",bean.getNew_wl_cars_type(),
                bean.getNew_plate_number(),bean.getCars_type());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateList(String data) {
        if(data.equals("0000")){
            getCheliang("","","","","");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        etSousuo.setText("");
        etSousuo.clearFocus();
        getCheliang("","","","","");
    }
}
