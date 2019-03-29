package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.PostShichangBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DataAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangQuanAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TimeAdapter;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.cityPicker.GetCityListUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/10/19.
 */

public class ShangQuanDialog extends BaseFragmentDialog implements View.OnClickListener {
    @BindView(R.id.rl_close)
    RelativeLayout rlClose;
    @BindView(R.id.rv_date)
    RecyclerView rvDate;
    @BindView(R.id.rv_time)
    RecyclerView rvTime;
    @BindView(R.id.tv_queren)
    TextView tvQueren;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;

    private List<JsonBean.CitylistBean.QulistBean> dateList = new ArrayList<>();
    private QuAdapter dateAdapter;
    private ShangQuanAdapter timeAdapter;
    private String dqId;
    private String xzId;
    private String dqName;
    private String xzName;
    private Context mContext;
    private List<ProvinceBean> songdashijianlist = new ArrayList<>();
    private PostShichangBean bean = new PostShichangBean();

    public ShangQuanDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pop_date_wuliu;
    }

    @Override
    protected void init() {
        tvTitle.setText("请选择区域商圈");
//        getAndroiodScreenProperty();
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) ll.getLayoutParams();
        linearParams.height = getAndroiodScreenHeight(mContext)/2;
        ll.setLayoutParams(linearParams);
        getData();
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration.fullWidth()
                .setGravity(Gravity.BOTTOM);
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

    @OnClick({R.id.rl_close,R.id.tv_queren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                this.dismiss();
                break;
            case R.id.tv_queren:
                if(StringUtil.isValid(xzId)){
                    this.dismiss();
                    bean.setName(dqName+xzName);
                    bean.setQu_bm(dqId+"");
                    bean.setSq_bm(xzId+"");
                    EventBus.getDefault().post(bean);
                } else {
                    ToastUtil.showToastLong("请选择商圈");
                }
                break;
        }
    }

    public void getData(){
        dateList = GetCityListUtil.getQuList(mContext,"230000", "230100");
        dateAdapter = new QuAdapter(mContext,dateList);
        timeAdapter = new ShangQuanAdapter(mContext,songdashijianlist);
        dateAdapter.setXztype(dateList.get(0));
        dqId = dateList.get(0).getQuybm()+"";
        dqName = dateList.get(0).getQuymc()+"";
        dateAdapter.setOnItemClickListener(new QuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_ming:
                        dateAdapter.setXztype(dateList.get(position));
                        dqId = dateList.get(position).getQuybm()+"";
                        dqName = dateList.get(position).getQuymc()+"";
//                        timeAdapter.setXztype(songdashijianlist.get(position));
                        getsongdashijian();
                        break;
                }
            }
        });
        rvDate.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvDate.setAdapter(dateAdapter);

        timeAdapter.setOnItemClickListener(new ShangQuanAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_ming:
                        xzId = songdashijianlist.get(position).getQuybm()+"";
                        xzName = songdashijianlist.get(position).getQuymc()+"";
                        timeAdapter.setXztype(songdashijianlist.get(position));
                        break;
                }
            }
        });
        rvTime.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvTime.setAdapter(timeAdapter);
        getsongdashijian();
        Log.e("getData: ", new Gson().toJson(dateList) );
    }

    //送达时间
    private void getsongdashijian() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsheng(PreferenceUtils.getString(MyApplication.mContext, "token", ""), dqId))
                .setDataListener(new HttpDataListener<List<ProvinceBean>>() {
                    @Override
                    public void onNext(List<ProvinceBean> data) {
                        songdashijianlist.clear();
                        songdashijianlist.addAll(data);
                        timeAdapter.notifyDataSetChanged();
                    }
                });

    }
}
