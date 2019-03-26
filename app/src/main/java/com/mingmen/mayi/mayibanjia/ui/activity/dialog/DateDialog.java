package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.QueRenDingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DataAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TimeAdapter;
import com.mingmen.mayi.mayibanjia.utils.DateUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Created by Administrator on 2018/10/19.
 */

public class DateDialog extends BaseFragmentDialog implements View.OnClickListener {
    @BindView(R.id.rl_close)
    RelativeLayout rlClose;
    @BindView(R.id.rv_date)
    RecyclerView rvDate;
    @BindView(R.id.rv_time)
    RecyclerView rvTime;
    @BindView(R.id.tv_queren)
    TextView tvQueren;
    Unbinder unbinder;

    private List<SongDaShiJianBean> dateList = new ArrayList<>();
    private DataAdapter dateAdapter;
    private TimeAdapter timeAdapter;
    private String dqId;
    private String xzId;
    private Context mContext;
    private List<SongDaShiJianBean> songdashijianlist = new ArrayList<SongDaShiJianBean>();
    private SongDaShiJianBean bean = new SongDaShiJianBean();

    public DateDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pop_date_wuliu;
    }

    @Override
    protected void init() {
        getData();
        getsongdashijian();
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
                    bean.setSon_name(dqId+" / "+bean.getSon_name());
                    EventBus.getDefault().post(bean);
                } else {
                    ToastUtil.showToastLong("请选择一个送达时间");
                }
                break;
        }
    }

    public void getData(){
        Map<String,Integer> map= DateUtil.genDate(3);
        int count = 0;
        for (String i:
                map.keySet()) {
            SongDaShiJianBean bean = new SongDaShiJianBean();
            bean.setSon_number(count+"");
            bean.setSon_name(i);
            dateList.add(bean);
        }
        dateAdapter = new DataAdapter(mContext,dateList);
        timeAdapter = new TimeAdapter(mContext,songdashijianlist);
        dateAdapter.setXztype(dateList.get(0));
        timeAdapter.setDqId(dateList.get(0).getSon_name());
        dqId = dateList.get(0).getSon_name();
        dateAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_ming:
                        dateAdapter.setXztype(dateList.get(position));
                        dqId = dateList.get(position).getSon_name();
                        timeAdapter.setDqId(dateList.get(position).getSon_name());
                        break;
                }
            }
        });
        rvDate.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvDate.setAdapter(dateAdapter);

        timeAdapter.setOnItemClickListener(new TimeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_ming:
                        xzId = songdashijianlist.get(position).getSon_name();
                        bean = songdashijianlist.get(position);
                        timeAdapter.setXztype(songdashijianlist.get(position));
                        timeAdapter.setXzId(dqId);
                        break;
                }
            }
        });
        rvTime.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvTime.setAdapter(timeAdapter);
        Log.e("getData: ", new Gson().toJson(dateList) );
    }

    //送达时间
    private void getsongdashijian() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getsongdashijian(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<SongDaShiJianBean>>() {
                    @Override
                    public void onNext(List<SongDaShiJianBean> data) {
                        Log.e("data", new Gson().toJson(data) + "---");
                        songdashijianlist.addAll(data);
                        timeAdapter.notifyDataSetChanged();
                    }
                });

    }
}
