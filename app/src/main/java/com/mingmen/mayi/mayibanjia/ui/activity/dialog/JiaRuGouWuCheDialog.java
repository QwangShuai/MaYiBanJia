package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/8/10.
 */

public class JiaRuGouWuCheDialog extends Dialog {
    public View view;
    @BindView(R.id.tv_spming)
    TextView tvSpming;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.tv_guigexiangqing)
    TextView tvSpguige;
    @BindView(R.id.tv_qidingliang1)
    TextView tvQidingliang1;
    @BindView(R.id.tv_qidingliangjiage1)
    TextView tvQidingliangjiage1;
//    @BindView(R.id.tv_qidingliang2)
//    TextView tvQidingliang2;
//    @BindView(R.id.tv_qidingliangjiage2)
//    TextView tvQidingliangjiage2;
//    @BindView(R.id.tv_qidingliang3)
//    TextView tvQidingliang3;
//    @BindView(R.id.tv_qidingliangjiage3)
//    TextView tvQidingliangjiage3;
    @BindView(R.id.tv_jianhao)
    TextView tvJianhao;
    @BindView(R.id.et_shuliang)
    EditText etShuliang;
    @BindView(R.id.tv_jiahao)
    TextView tvJiahao;
    @BindView(R.id.bt_queding)
    Button btQueding;
    @BindView(R.id.iv_sptu)
    ImageView ivSptu;
    private Context c;
    private int shuliang=0;

    /**
     * 构造确认信息弹窗
     *
     * @param context 上下文
     */
    public JiaRuGouWuCheDialog(Context context, int theme) {
        super(context, theme);
        view = View.inflate(context, R.layout.dialog_jiarugouwuche, null);
        this.setContentView(view);
        this.c = context;
        tvSpming = (TextView) findViewById(R.id.tv_spming);
        tvSpguige =  (TextView)findViewById(R.id.tv_guigexiangqing);
        tvQidingliang1 = (TextView) findViewById(R.id.tv_qidingliang1);
        tvQidingliangjiage1 = (TextView) findViewById(R.id.tv_qidingliangjiage1);
//        tvQidingliang2 = (TextView) findViewById(R.id.tv_qidingliang2);
//        tvQidingliangjiage2 =(TextView)  findViewById(R.id.tv_qidingliangjiage2);
//        tvQidingliang3 =(TextView)  findViewById(R.id.tv_qidingliang3);
//        tvQidingliangjiage3 = (TextView) findViewById(R.id.tv_qidingliangjiage3);
        tvJianhao = (TextView) findViewById(R.id.tv_jianhao);
        etShuliang = (EditText) findViewById(R.id.et_shuliang);
        tvJiahao = (TextView) findViewById(R.id.tv_jiahao);
        btQueding = (Button) findViewById(R.id.bt_queding);
        ivSptu = (ImageView) findViewById(R.id.iv_sptu);
        tvKucun = findViewById(R.id.tv_kucun);
        tvJiahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(etShuliang.getText().toString().trim())){
                    shuliang = Integer.parseInt(etShuliang.getText().toString().trim());
                }else{
                    shuliang=0;
                }
                shuliang=shuliang+1;
                etShuliang.setText(shuliang+"");
            }
        });
        tvJianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(etShuliang.getText().toString().trim())){
                    shuliang = Integer.parseInt(etShuliang.getText().toString().trim());
                }else{
                    shuliang=1;
                }
                if (shuliang==1){
                    ToastUtil.showToast("不能再减了");
                }else{
                    shuliang=shuliang-1;
                    etShuliang.setText(shuliang+"");
                }

            }
        });
    }

    /**
     * 显示确认信息弹窗
     */
    public void showDialog(String kucun,String spming,String spguige,String qidingliang1,String qidingliangjiage1,String qidingliang2,String qidingliangjiage2,String qidingliang3,String qidingliangjiage3,String sptu) {

        tvSpming.setText(spming);
        tvSpguige.setText(spguige);
        if (!StringUtil.isEmpty(qidingliang1)){
            tvQidingliang1.setText(qidingliang1);
        }
        if (!StringUtil.isEmpty(qidingliang1)){
            etShuliang.setText(qidingliang1);
        }
//        if (!StringUtil.isEmpty(qidingliang2)){
//            tvQidingliang2.setText(qidingliang2);
//        }
//        if (!StringUtil.isEmpty(qidingliang3)){
//            tvQidingliang3.setText(qidingliang3);
//        }
        if (!StringUtil.isEmpty(qidingliangjiage1)){
            tvQidingliangjiage1.setText(qidingliangjiage1);
        }
//        if (!StringUtil.isEmpty(qidingliangjiage2)){
//            if (!StringUtil.isEmpty(qidingliang2)){
//                tvQidingliangjiage2.setText(qidingliangjiage2);
//            }
//
//        }
//        if (!StringUtil.isEmpty(qidingliangjiage3)){
//            if (!StringUtil.isEmpty(qidingliang3)){
//                tvQidingliangjiage3.setText(qidingliangjiage3);
//            }
//        }
        if (!StringUtil.isEmpty(kucun)){
            tvKucun.setText(kucun);
        }

        Glide.with(c).load(sptu).into(ivSptu);
        btQueding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("kucunsss");
            }
        });
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
        this.view = view;
        this.setContentView(view);
    }

    public View getView() {
        return view;
    }

    public TextView getTvSpming() {
        return tvSpming;
    }

    public void setTvSpming(TextView tvSpming) {
        this.tvSpming = tvSpming;
    }

    public TextView getTvSpguige() {
        return tvSpguige;
    }

    public void setTvSpguige(TextView tvSpguige) {
        this.tvSpguige = tvSpguige;
    }

    public TextView getTvQidingliang1() {
        return tvQidingliang1;
    }

    public void setTvQidingliang1(TextView tvQidingliang1) {
        this.tvQidingliang1 = tvQidingliang1;
    }

    public TextView getTvQidingliangjiage1() {
        return tvQidingliangjiage1;
    }

    public void setTvQidingliangjiage1(TextView tvQidingliangjiage1) {
        this.tvQidingliangjiage1 = tvQidingliangjiage1;
    }

//    public TextView getTvQidingliang2() {
//        return tvQidingliang2;
//    }
//
//    public void setTvQidingliang2(TextView tvQidingliang2) {
//        this.tvQidingliang2 = tvQidingliang2;
//    }
//
//    public TextView getTvQidingliangjiage2() {
//        return tvQidingliangjiage2;
//    }

//    public void setTvQidingliangjiage2(TextView tvQidingliangjiage2) {
//        this.tvQidingliangjiage2 = tvQidingliangjiage2;
//    }
//
//    public TextView getTvQidingliang3() {
//        return tvQidingliang3;
//    }
//
//    public void setTvQidingliang3(TextView tvQidingliang3) {
//        this.tvQidingliang3 = tvQidingliang3;
//    }
//
//    public TextView getTvQidingliangjiage3() {
//        return tvQidingliangjiage3;
//    }

//    public void setTvQidingliangjiage3(TextView tvQidingliangjiage3) {
//        this.tvQidingliangjiage3 = tvQidingliangjiage3;
//    }

    public TextView getTvJianhao() {
        return tvJianhao;
    }

    public void setTvJianhao(TextView tvJianhao) {
        this.tvJianhao = tvJianhao;
    }

    public EditText getEtShuliang() {
        return etShuliang;
    }

    public void setEtShuliang(EditText etShuliang) {
        this.etShuliang = etShuliang;
    }

    public TextView getTvJiahao() {
        return tvJiahao;
    }

    public void setTvJiahao(TextView tvJiahao) {
        this.tvJiahao = tvJiahao;
    }

    public Button getBtQueding() {
        return btQueding;
    }

    public void setBtQueding(Button btQueding) {
        this.btQueding = btQueding;
    }

    public ImageView getIvSptu() {
        return ivSptu;
    }

    public void setIvSptu(ImageView ivSptu) {
        this.ivSptu = ivSptu;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }
}
