package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JueSeBean;
import com.mingmen.mayi.mayibanjia.bean.RoleBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/27.
 */

public class JueSeDialog extends Dialog {

    @BindView(R.id.xcf_xuanze)
    XCFlowLayout xcfXuanze;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private Context context;
    private CallBack callBack;
    private String lableId="";
    private String name="";
    private String account_id="";
    private String xzid="";
    private boolean[] isSelect;
    ArrayList<TextView> tvs;
    String[] strList = new String[]{};
    private HashMap<String, JueSeBean> xuanzhong = new HashMap<>();
    public JueSeDialog(@NonNull Context context,String account_id,String xzid, CallBack callBack) {
        super(context);
        this.context = context;
        this.account_id = account_id;
        this.xzid = xzid;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    @OnClick({R.id.tv_submit, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                getLableId();
                if(StringUtil.isValid(lableId)){
                    dismiss();
                    callBack.confirm(lableId,name);
                } else {

                }

                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public interface CallBack {
        void confirm(String id, String name);
    }

    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_xuanze, null);
        setContentView(view);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
        if(StringUtil.isValid(xzid)){
            strList = xzid.split(",");
            for (int i=0;i<xzid.split(",").length;i++){
                strList[i] = xzid.split(",")[i];
            }
        }
        getmoren();
    }

    private void initShangpinChildViews(XCFlowLayout xcfShangpinlishisousuo, final List<JueSeBean> mList) {
        xcfShangpinlishisousuo.removeAllViews();
        tvs = new ArrayList();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = AppUtil.dip2px(12);
        lp.rightMargin = AppUtil.dip2px(0);
        lp.topMargin = AppUtil.dip2px(12);
        lp.bottomMargin = 0;
        for (int i = 0; i < mList.size(); i++) {

            if(mList.get(i).getIsSelected().equals("0")){
                isSelect[i] = true;
                addViewShow(mList.get(i));
            }
            if(strList!=null&&strList.length!=0){
                for (int j=0;j<strList.length;j++){
                    if(strList[j].equals(mList.get(i).getSon_role_id())){
                        isSelect[i] = true;
                        addViewShow(mList.get(i));
                    }
                }
            }
            TextView view = new TextView(context);
            view.setText(mList.get(i).getPart());
            view.setTextColor(isSelect[i]?context.getResources().getColor(R.color.white):context.getResources().getColor(R.color.zangqing));
            view.setTextSize(12);
            view.setPadding(AppUtil.dip2px(12), AppUtil.dip2px(8), AppUtil.dip2px(12), AppUtil.dip2px(8));
            view.setBackground(isSelect[i]?context.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3):
                    context.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            tvs.add(view);
            xcfShangpinlishisousuo.addView(view, lp);
        }
        for (int i = 0; i < tvs.size(); i++) {
            final int finalI = i;
            tvs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isSelect[finalI]){
                        isSelect[finalI] = true;
                        addViewShow(mList.get(finalI));
                        tvs.get(finalI).setTextColor(context.getResources().getColor(R.color.white));
                        tvs.get(finalI).setBackground(context.getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                    } else {
                        isSelect[finalI] = false;
                        delViewShow(mList.get(finalI));
                        tvs.get(finalI).setTextColor(context.getResources().getColor(R.color.zangqing));
                        tvs.get(finalI).setBackground(context.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
                    }

                }
            });
        }
    }
    private void getmoren() {
        HttpManager.getInstance()
                .with(context)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getJuese(PreferenceUtils.getString(MyApplication.mContext, "token", ""),account_id))
                .setDataListener(new HttpDataListener<List<JueSeBean>>() {
                    @Override
                    public void onNext(List<JueSeBean> data) {
                        int mysize = data==null?0:data.size();
                        isSelect = new boolean[mysize];
                        initShangpinChildViews(xcfXuanze,data);
                    }
                },false);
    }
    public void delViewShow(JueSeBean item) {//删除item
        xuanzhong.remove(item.getSon_role_id());
    }
    public void addViewShow(JueSeBean item) {//存储点击item
        xuanzhong.put(item.getSon_role_id(),item);
    }

    private void getLableId(){
        lableId = "";
        int count = 0;
        Set<String> mapkey = xuanzhong.keySet();
        for (String key : mapkey) {
            JueSeBean value = xuanzhong.get(key);
            if (value.getSon_role_id().isEmpty()) {//没选中的不拼   避免有多余的,
            } else {
                lableId += key + ",";
                name += value.getPart()+",";
                count++;
            }
        }
        if (count != 0) {
            lableId = lableId.substring(0, lableId.length() - 1);
        } else {
            ToastUtil.showToast("您还没有选择标签啊");
        }
    }
}
