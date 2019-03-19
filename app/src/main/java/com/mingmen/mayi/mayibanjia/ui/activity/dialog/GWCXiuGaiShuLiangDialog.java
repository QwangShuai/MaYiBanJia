package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShangPinBean;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/17.
 */

public class GWCXiuGaiShuLiangDialog extends BaseFragmentDialog {

    public final static int JIA=1;
    public final static int JIAN=2;
    public final static int GAI=3;
    @BindView(R.id.tv_jianhao)
    TextView tvJianhao;
    @BindView(R.id.et_shuzi)
    EditText etShuzi;
    @BindView(R.id.tv_jiahao)
    TextView tvJiahao;
    @BindView(R.id.bt_queding)
    Button btQueding;
    private CallBack mCallBack;
    private String kuCun;
    private String qidingliang;
    private int chushishuliang;
    private int shuliang;

    public GWCXiuGaiShuLiangDialog() {

    }

    public GWCXiuGaiShuLiangDialog setKuCun(String kuCun, String qidingliang, int chushishuliang) {
        this.kuCun = kuCun;
        this.qidingliang = qidingliang;
        this.chushishuliang = chushishuliang;
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_gouwuchexiugaishuliang;
    }

    @Override
    protected void init() {
        Log.e("initinit","initinit");
        shuliang = chushishuliang;
        etShuzi.setText(chushishuliang+"");
        etShuzi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(StringUtil.isValid(s.toString().trim())){
                    shuliang = Integer.valueOf(s.toString().trim());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvJiahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuliang==Integer.parseInt(kuCun)){
                    ToastUtil.showToast("不能再加啦");
                    return;
                }else{
                    xieru(JIA);
                }
            }


        });
        tvJianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuliang<=Integer.parseInt(qidingliang)){
                    ToastUtil.showToast("不能再减啦");
                    return;
                }else{
                    xieru(JIAN);
                }
            }


        });
        btQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shuzi = etShuzi.getText().toString().trim();
                if (shuzi.equals("")){
                    ToastUtil.showToast("不能再减啦");
                    return;
                }
                if (Integer.parseInt(shuzi)>Integer.parseInt(kuCun)){
                    dismiss();
                    ToastUtil.showToast("超过库存了");
                    return;
                }
                Log.e("qidingliang",qidingliang+"---");
                if (Integer.parseInt(shuzi)<Integer.parseInt(qidingliang)){
                    dismiss();
                    ToastUtil.showToast("低于起订量不能买");
                    return;
                }
                if (mCallBack != null)
                    mCallBack.shuliang(shuzi);
                dismiss();
            }
        });
    }

    private void xieru(int type) {
        switch (type){
            case JIA:
                shuliang=shuliang+1;
                break;
            case JIAN:
                shuliang=shuliang-1;
                break;
        }
        etShuzi.setText(shuliang+"");

    }

    public GWCXiuGaiShuLiangDialog setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
        return this;
    }

    @Override
    protected void initConfiguration(Configuration configuration) {
        configuration
                .setWidth(AppUtil.dip2px(340))
                .setHeight(AppUtil.dip2px(350))
                .setGravity(Gravity.CENTER);
        Log.e("initConfiguration","initConfiguration");
    }


    public interface CallBack {
        void shuliang(String msg);
    }
}
