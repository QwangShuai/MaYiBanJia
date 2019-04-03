package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DataAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.TimeAdapter;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
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

/**
 * Created by Administrator on 2018/10/19.
 */

public class GuigeDialog extends BaseFragmentDialog implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.rl_close)
    RelativeLayout rlClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_queren)
    TextView tvQueren;
    @BindView(R.id.xcf_guige)
    XCFlowLayout xcfGuige;
    @BindView(R.id.ll)
    LinearLayout ll;

    private List<SongDaShiJianBean> dateList = new ArrayList<>();
    private DataAdapter dateAdapter;
    private TimeAdapter timeAdapter;
    private String dqId;
    private String xzId;
    private Context mContext;
    private List<SongDaShiJianBean> songdashijianlist = new ArrayList<SongDaShiJianBean>();
    private SongDaShiJianBean bean = new SongDaShiJianBean();

    public GuigeDialog setData(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_guige;
    }

    @Override
    protected void init() {
        tvTitle.setText("请选择送达时间");
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) ll.getLayoutParams();
        linearParams.height = getAndroiodScreenHeight(mContext) / 3;
        ll.setLayoutParams(linearParams);
        getData();
//        getsongdashijian();
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

    @OnClick({R.id.rl_close, R.id.tv_queren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                this.dismiss();
                break;
            case R.id.tv_queren:
                if (StringUtil.isValid(xzId)) {
                    this.dismiss();
                    bean.setSon_name(dqId + " / " + bean.getSon_name());
                    EventBus.getDefault().post(bean);
                } else {
                    ToastUtil.showToastLong("请选择一个送达时间");
                }
                break;
        }
    }

    public void getData() {
    }

    //送达时间
    private void getGuige() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getguige(PreferenceUtils.getString(MyApplication.mContext, "token", ""),""))
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
