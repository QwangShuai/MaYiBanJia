package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DiquXuanzeBean;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.DiquOneAdapter;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/10.
 */

public class DiQuDialog extends Dialog {
    @BindView(R.id.rl_close)
    RelativeLayout rlClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_queren)
    TextView tvQueren;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.rv_one)
    RecyclerView rvOne;
    @BindView(R.id.rv_two)
    RecyclerView rvTwo;
    @BindView(R.id.rv_three)
    RecyclerView rvThree;
    private Context c;

    private int one;
    private int two;
    private int three;

    private String oneName;
    private String twoName;
    private String threeName;

    private DiquOneAdapter adapter;
    private DiquOneAdapter city_adapter;
    private DiquOneAdapter qu_adapter;

    private List<JsonBean> oneList = new ArrayList<>();
    private List<JsonBean.CitylistBean> twoList = new ArrayList<>();
    private List<JsonBean.CitylistBean.QulistBean> threeList = new ArrayList<>();

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public DiQuDialog(Context context, int theme) {
        super(context, theme);
        this.c = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        String JsonData = StringUtil.getJson(c, "province.json");//获取assets目录下的json文件数据
        oneList = StringUtil.parseData(JsonData);//用Gson 转成实体
        View view = LayoutInflater.from(c).inflate(R.layout.dialog_diqu, null);
        setContentView(view);
        ButterKnife.bind(this);

        adapter = new DiquOneAdapter(c,oneList);
        city_adapter = new DiquOneAdapter(c,twoList,2);
        qu_adapter = new DiquOneAdapter(c,threeList,"3");

        adapter.setOnItemClickListener(new DiquOneAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(one!=oneList.get(position).getQuybm()){
                    tvTwo.setText("");
                    tvThree.setText("");
                    tvThree.setVisibility(View.GONE);
                }
//                EventBus.getDefault().post(oneList.get(position));
                adapter.setXztype(oneList.get(position).getQuybm());
                one = oneList.get(position).getQuybm();
                oneName = oneList.get(position).getQuymc();

                tvOne.setText(oneName);

                rvOne.setVisibility(View.GONE);
                twoList.clear();
                for (JsonBean.CitylistBean bean:oneList.get(position).getCitylist()) {
                    twoList.add(bean);
                }
                clearColor();
                tvTwo.setTextColor(c.getResources().getColor(R.color.zangqing));
                city_adapter.notifyDataSetChanged();
                tvTwo.setVisibility(View.VISIBLE);
                rvTwo.setVisibility(View.VISIBLE);
            }
        });

        city_adapter.setOnItemClickListener(new DiquOneAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(two!=twoList.get(position).getQuybm()){
                    tvThree.setText("");
                }
                city_adapter.setXztype(twoList.get(position).getQuybm());
                rvTwo.setVisibility(View.GONE);
                two = twoList.get(position).getQuybm();
                twoName = twoList.get(position).getQuymc();
                threeList.clear();
                for (JsonBean.CitylistBean.QulistBean bean:
                        twoList.get(position).getQulist()) {
                    threeList.add(bean);
                }
                qu_adapter.notifyDataSetChanged();
                tvTwo.setText(twoName);
                clearColor();
                tvThree.setTextColor(c.getResources().getColor(R.color.zangqing));
                tvThree.setVisibility(View.VISIBLE);
                rvThree.setVisibility(View.VISIBLE);
            }
        });

        qu_adapter.setOnItemClickListener(new DiquOneAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                DiquXuanzeBean bean = new DiquXuanzeBean();
                bean.setSheng_bm(one);
                bean.setCity_bm(two);
                bean.setQu_bm(threeList.get(position).getQuybm());
                bean.setSheng_name(oneName);
                bean.setCity_name(twoName);
                bean.setQu_name(threeList.get(position).getQuymc());
                EventBus.getDefault().post(bean);
                dismiss();
            }
        });

        adapter.setXztype(one);
        city_adapter.setXztype(two);
        qu_adapter.setXztype(three);

        rvOne.setLayoutManager(new LinearLayoutManager(c,LinearLayoutManager.VERTICAL,false));
        rvTwo.setLayoutManager(new LinearLayoutManager(c,LinearLayoutManager.VERTICAL,false));
        rvThree.setLayoutManager(new LinearLayoutManager(c,LinearLayoutManager.VERTICAL,false));

        rvOne.setAdapter(adapter);
        rvTwo.setAdapter(city_adapter);
        rvThree.setAdapter(qu_adapter);

        if(two!=0){
            tvTwo.setVisibility(View.VISIBLE);
            tvOne.setText(oneName);
            tvTwo.setText(twoName);
            tvTwo.setTextColor(c.getResources().getColor(R.color.lishisousuo));
            for (JsonBean bean:oneList) {
                if(bean.getQuybm()==one){
                    twoList.addAll(bean.getCitylist());
                    city_adapter.notifyDataSetChanged();
                    break;
                }
            }

        }
        if(three!=0){
            tvThree.setVisibility(View.VISIBLE);
            tvThree.setText(threeName);
            tvThree.setTextColor(c.getResources().getColor(R.color.lishisousuo));
            for (JsonBean.CitylistBean bean:twoList) {
                if(bean.getQuybm()==two){
                    threeList.addAll(bean.getQulist());
                    qu_adapter.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    /**
     * 显示确认信息弹窗
     */
    public void showDialog(int one,int two,int three,String oneName,String twoName,String threeName) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.oneName = oneName;
        this.twoName = twoName;
        this.threeName = threeName;
//
//        adapter.setXztype(one);
//        city_adapter.setXztype(two);
//        qu_adapter.setXztype(three);
//
//        adapter.notifyDataSetChanged();
//        city_adapter.notifyDataSetChanged();
//        qu_adapter.notifyDataSetChanged();
        this.show();
    }

    public void cancel() {
        if (!isShowing()) {
            return;
        }
        super.cancel();
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         //         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

    public void setView(View view) {
        this.setContentView(view);
    }


    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    @OnClick({R.id.rl_close, R.id.tv_queren, R.id.tv_one, R.id.tv_two, R.id.tv_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                dismiss();
                break;
            case R.id.tv_queren:
                dismiss();
                break;
            case R.id.tv_one:
                clearShowView();
                tvOne.setTextColor(c.getResources().getColor(R.color.zangqing));
                rvOne.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_two:
                clearShowView();
                rvTwo.setVisibility(View.VISIBLE);
                tvTwo.setTextColor(c.getResources().getColor(R.color.zangqing));
                break;
            case R.id.tv_three:
                clearShowView();
                rvThree.setVisibility(View.VISIBLE);
                tvThree.setTextColor(c.getResources().getColor(R.color.zangqing));
                break;
        }
    }

    private void clearShowView(){
        rvOne.setVisibility(View.GONE);
        rvTwo.setVisibility(View.GONE);
        rvThree.setVisibility(View.GONE);

        tvOne.setTextColor(c.getResources().getColor(R.color.lishisousuo));
        tvTwo.setTextColor(c.getResources().getColor(R.color.lishisousuo));
        tvThree.setTextColor(c.getResources().getColor(R.color.lishisousuo));
    }

    private void clearColor(){
        tvOne.setTextColor(c.getResources().getColor(R.color.lishisousuo));
        tvTwo.setTextColor(c.getResources().getColor(R.color.lishisousuo));
        tvThree.setTextColor(c.getResources().getColor(R.color.lishisousuo));
    }
}
