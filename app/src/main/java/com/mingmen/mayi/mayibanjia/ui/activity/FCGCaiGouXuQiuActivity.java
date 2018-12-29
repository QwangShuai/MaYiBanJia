package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.FCGSaveFanHuiBean;
import com.mingmen.mayi.mayibanjia.bean.FCGShangPinbean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouGuiGeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.FaCaiGouMohuAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.TiJiaoXuQiuDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/20/020.
 */

public class FCGCaiGouXuQiuActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_shangpin_ming)
    EditText etShangpinMing;
    @BindView(R.id.et_caigouliang)
    EditText etCaigouliang;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_xiangxia)
    ImageView tvXiangxia;
    @BindView(R.id.iv_teshu)
    ImageView ivTeshu;
    @BindView(R.id.ll_teshu)
    LinearLayout llTeshu;
    @BindView(R.id.rl_teshu)
    RelativeLayout rlTeshu;
    @BindView(R.id.et_teshu)
    EditText etTeshu;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.bt_shanchu)
    Button btShanchu;
    @BindView(R.id.bt_shangyige)
    Button btShangyige;
    @BindView(R.id.bt_xiayige)
    Button btXiayige;
    @BindView(R.id.bt_tianjia)
    Button btTianjia;
    @BindView(R.id.bt_tijiao)
    Button btTijiao;
    @BindView(R.id.rl_guige)
    RelativeLayout rlGuige;
    private int isTeshu = 0;
    private int dijige = 0;

    private String caigouliang = "";
    private String teshuyaoqiu = "";
    private List<FCGShangPinbean> mlist = new ArrayList<>();
    private String guige;
    private Context mContext;
    private String shichangid;
    private String purchase_id = "";
    private TiJiaoXuQiuDialog tijiaoxuqiuDialog;
    private PopupWindow mPopWindow;
    private ArrayList<FCGName> datas = new ArrayList<>();
    private String leibieid;
    private FaCaiGouMohuAdapter mohuAdapter;
    private RecyclerView rv_mohu;
    private String sanjifenleiName;
    private String sanjifenleiId = "";
    private String pack_standard_id = "";
    private String purchase_name = "";
    private PopupWindow guigePop;
    private RecyclerView rvguige;
    private FaCaiGouGuiGeAdapter guigeadapter;
    private ArrayList<FCGGuige> guigedatas;
    private ConfirmDialog confirmDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_facaigou_fabucaigou;
    }

    @Override
    protected void initData() {
        mContext = FCGCaiGouXuQiuActivity.this;
        tvTitle.setText("采购需求");
        tvRight.setText("采购列表");
        tvRight.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        bundle = getIntent().getExtras();
        shichangid = bundle.getString("shichang");
        purchase_name = bundle.getString("caigouming");
        teshu(isTeshu);
        tijiaoxuqiuDialog = new TiJiaoXuQiuDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        etShangpinMing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    if (s.toString().trim().equals(sanjifenleiName)) {

                    } else {
                        getfcgname(s.toString().trim());
                    }
                }

            }
        });
        etTeshu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tishi.setText(s.toString().trim().length() + "/50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
    }

    private void getfcgguige(String sanjifenleiId) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgguige(sanjifenleiId))
                .setDataListener(new HttpDataListener<List<FCGGuige>>() {
                    @Override
                    public void onNext(List<FCGGuige> data) {
                        guigedatas = new ArrayList<>();
                        guigedatas.addAll(data);
                        tvGuige.setText("");
                        pack_standard_id = "";
                        if (guigeadapter != null) {
                            guigeadapter.setData(guigedatas);
                        }
                        if(guigedatas!=null&&guigedatas.size()!=0){
                            tvGuige.setText(guigedatas.get(0).getSpec_name());
                            pack_standard_id = guigedatas.get(0).getSpec_id();
                        }
                    }
                }, false);
    }

    private void getfcgname(final String name) {
        if (name.equals(sanjifenleiName)) {
            return;
        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getfcgname(PreferenceUtils.getString(MyApplication.mContext, "token", ""), name))
                .setDataListener(new HttpDataListener<List<FCGName>>() {
                    @Override
                    public void onNext(List<FCGName> data) {
                        datas = new ArrayList<FCGName>();
                        datas.addAll(data);
                        Log.e("data", data + "---");
                        if (mPopWindow != null) {
                            if (name.equals(sanjifenleiName)) {
                                return;
                            }
                            mPopWindow.showAsDropDown(etShangpinMing);
                            mohuAdapter.setData(datas);
                        } else {
                            showPopupWindow();
                        }

                    }
                }, false);
    }

    //PopupWindow
    private void showPopupWindow() {
        View view = View.inflate(mContext, R.layout.pp_textview_recycleview, null);
        mPopWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 2 / 6);
        mPopWindow.setHeight(height * 2 / 9);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(etShangpinMing);
        rv_mohu = (RecyclerView) view.findViewById(R.id.rv_list);
        mohuAdapter = new FaCaiGouMohuAdapter(mContext, datas);
        rv_mohu.setAdapter(mohuAdapter);
        rv_mohu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mohuAdapter.setOnItemClickListener(new FaCaiGouMohuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                leibieid = datas.get(position).getClassify_id();
                Log.e("leibieid", leibieid + "--");
                etShangpinMing.setText("" + datas.get(position).getClassify_name());
                sanjifenleiName = datas.get(position).getClassify_name();
                sanjifenleiId = datas.get(position).getClassify_id();
                getfcgguige(sanjifenleiId);
                mPopWindow.dismiss();
            }
        });
    }

    private void showGuigePopupWindow() {
        View view = View.inflate(mContext, R.layout.pp_textview_recycleview, null);
        guigePop = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        guigePop.setWidth(rlGuige.getWidth());
        guigePop.setHeight(height * 2 / 9);
        guigePop.setOutsideTouchable(true);
        guigePop.setBackgroundDrawable(new BitmapDrawable());
        guigePop.showAsDropDown(rlGuige);
        rvguige = (RecyclerView) view.findViewById(R.id.rv_list);

        guigeadapter = new FaCaiGouGuiGeAdapter(mContext, guigedatas);
        rvguige.setAdapter(guigeadapter);
        rvguige.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        guigeadapter.setOnItemClickListener(new FaCaiGouGuiGeAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvGuige.setText(guigedatas.get(position).getSpec_name());
                pack_standard_id = guigedatas.get(position).getSpec_id();
//                leibieid = datas.get(position).getClassify_id();
//                Log.e("leibieid",leibieid+"--");
//                etShangpinMing.setText(""+datas.get(position).getClassify_name());
//                sanjifenleiName=datas.get(position).getClassify_name();
//                sanjifenleiId=datas.get(position).getClassify_id();
//                getfcgguige(sanjifenleiId);
                guigePop.dismiss();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.rl_guige, R.id.ll_teshu, R.id.bt_shanchu, R.id.bt_shangyige, R.id.bt_xiayige, R.id.bt_tianjia, R.id.bt_tijiao,R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_guige:
//                tv_guigename
                if (guigedatas != null && guigedatas.size() > 0) {
                    showGuigePopupWindow();
                } else {
                    ToastUtil.showToast("请输入商品名，并选择相应的分类");
                }

                break;
            case R.id.ll_teshu:
                if (isTeshu == 0) {
                    ivTeshu.setSelected(true);
                    rlTeshu.setVisibility(View.VISIBLE);
                    isTeshu = 1;
                } else {
                    ivTeshu.setSelected(false);
                    rlTeshu.setVisibility(View.GONE);
                    isTeshu = 0;
                }
                break;
            case R.id.bt_shanchu:
                etTeshu.setText("");
                ivTeshu.setSelected(false);
                rlTeshu.setVisibility(View.GONE);
                etShangpinMing.setText("");
                etCaigouliang.setText("");
                isTeshu = 0;
                break;
//            case R.id.bt_shangyige:
//                if (mlist.size() == 0 | dijige == 0) {
//                    ToastUtil.showToast("前面没商品了");
//                } else {
//                    dijige--;
//                    fuxian(dijige);
//                }
//                break;
//            case R.id.bt_xiayige:
//                if (dijige >= mlist.size() - 1) {
//                    ToastUtil.showToast("后面没商品了");
//                } else {
//                    dijige++;
//                    fuxian(dijige);
//                }
//                break;
            case R.id.bt_tianjia:
                huoqushuju();
                if (!"".equals(sanjifenleiId) & !"".equals(caigouliang)) {
                    if (mlist.size() >= dijige) {
                        save(dijige, 3);
                    } else {
                    }
                } else {
                    ToastUtil.showToast("请确认填写好商品名以及采购量");
                }
                break;
            case R.id.bt_tijiao:
                huoqushuju();
                if (!"".equals(sanjifenleiId) & !"".equals(caigouliang)) {
//                    mlist.get(dijige).setSon_order_id(purchase_id);
                    save(dijige, 4);
                } else {
                    if (mlist.size() == 0) {
                        ToastUtil.showToast("请先添加至少一个商品再提交");
                    } else {
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                                .postCaigoudan(purchase_id))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        ToastUtil.showToast("data");
                                    }

                                }, false);
                        //dialog
                        tijiaoxuqiuDialog.showDialog();
                        tijiaoxuqiuDialog.getTvCaigoudan().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, CaiGouDanActivity.class);
                                startActivity(intent);
                                FCGDiQuXuanZeActivity.instance.finish();
                                tijiaoxuqiuDialog.dismiss();
                                finish();
                            }
                        });
                        tijiaoxuqiuDialog.getTvShouye().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                tijiaoxuqiuDialog.dismiss();
                                finish();
                            }
                        });
                    }
                }
                break;
            case R.id.tv_right:
                if(StringUtil.isValid(purchase_id)){
                    Bundle liebiao = new Bundle();
                    liebiao.putString("id",purchase_id);
                    Jump_intent(CaiGouListActivity.class,liebiao);
                } else {
                    ToastUtil.showToast("您还没有添加过采购商品");
                }

                break;
        }
    }


    //复现
    private void fuxian(int dijige) {
        FCGShangPinbean muqianbean = mlist.get(dijige);
        pack_standard_id = muqianbean.getGuigeId();
        sanjifenleiName = muqianbean.getSpname();
        sanjifenleiId = muqianbean.getSpid();
        etShangpinMing.setText(muqianbean.getSpname());
        etCaigouliang.setText(muqianbean.getNumber());
        etTeshu.setText(muqianbean.getTeshuyaoqiu());
        tvGuige.setText(muqianbean.getGuigeName());
        tishi.setText(muqianbean.getTeshuyaoqiu().length() + "/50");
        if (muqianbean.getTeshuyaoqiu() != null & !"".equals(muqianbean.getTeshuyaoqiu())) {
            teshu(1);
        } else {
            teshu(0);
        }


    }

    //保存新添加的

    private void baocunxintianjiade(int dijige) {
        FCGShangPinbean fcgShangPinbean = new FCGShangPinbean();
        fcgShangPinbean.setSpname(sanjifenleiName);
        fcgShangPinbean.setSpid(sanjifenleiId);
        fcgShangPinbean.setNumber(caigouliang);
        fcgShangPinbean.setGuigeName(tvGuige.getText().toString().trim());
        fcgShangPinbean.setGuigeId(pack_standard_id);
        fcgShangPinbean.setTeshuyaoqiu(teshuyaoqiu);
        if (this.dijige == mlist.size()) {
            fcgShangPinbean.setGe(mlist.size());
            mlist.add(fcgShangPinbean);
        } else {
            mlist.set(dijige, fcgShangPinbean);
        }


    }

    //保存商品
    private void save(final int dijige, final int leixing) {
        huoqushuju();

        if (sanjifenleiId.isEmpty()) {
            ToastUtil.showToast("请输入商品名，并选择名称");
            return;
        }
        if (pack_standard_id.isEmpty()) {
            ToastUtil.showToast("请选择规格");
            return;
        }



        String son_order_id = "";
        String getTeshuyaoqiu = "";

        if (dijige != mlist.size()) {
            son_order_id = mlist.get(dijige).getSon_order_id();
            getTeshuyaoqiu = mlist.get(dijige).getTeshuyaoqiu();
        } else {

        }
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                .getfcgsave(PreferenceUtils.getString(MyApplication.mContext, "token", ""), "".equals(getTeshuyaoqiu) ? teshuyaoqiu : getTeshuyaoqiu,
                                        shichangid, sanjifenleiId, purchase_id, son_order_id, pack_standard_id, purchase_name, caigouliang + ""))
                .setDataListener(new HttpDataListener<FCGSaveFanHuiBean>() {
                    @Override
                    public void onNext(FCGSaveFanHuiBean data) {
                        if (data != null) {
                            purchase_id = data.getPurchase_id();
                        }
                        baocunxintianjiade(dijige);
                        qingkongshuju();
                        if (leixing == 1) {
                            FCGCaiGouXuQiuActivity.this.dijige++;
                        } else if (leixing == 2) {
                            FCGCaiGouXuQiuActivity.this.dijige--;
                        } else if (leixing == 3) {
                            FCGCaiGouXuQiuActivity.this.dijige = mlist.size();
                        } else if (leixing == 4) {
                            FCGCaiGouXuQiuActivity.this.dijige = mlist.size();
                            confirmDialog.showDialog("是否确认提交采购单");
                            confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        HttpManager.getInstance()
                                                .with(mContext)
                                                .setObservable(
                                                        RetrofitManager
                                                                .getService()
                                                                //user_token  是否是特殊商品不是0 是1    如果是特殊商品 填写要求   市场id  类别id  产品数量
                                                                .postCaigoudan(purchase_id))
                                                .setDataListener(new HttpDataListener<String>() {
                                                    @Override
                                                    public void onNext(String data) {
                                                        ToastUtil.showToast("主表真是好使啊");
                                                    }

                                                }, false);
                                    confirmDialog.cancel();
                                    tijiaoxuqiuDialog.showDialog();
                                    tijiaoxuqiuDialog.getTvCaigoudan().setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mContext, CaiGouDanActivity.class);
                                            startActivity(intent);
                                            FCGDiQuXuanZeActivity.instance.finish();
                                            tijiaoxuqiuDialog.dismiss();
                                            finish();
                                        }
                                    });
                                    tijiaoxuqiuDialog.getTvShouye().setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            tijiaoxuqiuDialog.dismiss();
                                            finish();
                                        }
                                    });
                                }
                            });
                            confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });
                        }

                    }
                });

    }

    //特殊
    private void teshu(int teshu) {
        if (teshu == 1) {
            ivTeshu.setSelected(true);
            rlTeshu.setVisibility(View.VISIBLE);
        } else {
            ivTeshu.setSelected(false);
            rlTeshu.setVisibility(View.GONE);
        }
    }

    //清空各种数据  让用户重新填写
    private void qingkongshuju() {
        etShangpinMing.setText("");
        etCaigouliang.setText("");
        etTeshu.setText("");
        tvGuige.setText("");
        sanjifenleiName = "";
        sanjifenleiId = "";
        pack_standard_id = "";
        datas = new ArrayList<>();
        mohuAdapter.setData(datas);
        guigedatas = new ArrayList<>();
        if(guigeadapter!=null)
            guigeadapter.setData(guigedatas);
        etShangpinMing.setFocusable(true);
        etShangpinMing.setFocusableInTouchMode(true);
        etShangpinMing.requestFocus();
        ivTeshu.setSelected(false);
        rlTeshu.setVisibility(View.GONE);

//        shangpinid="";
//        shangpinming="";
//        caigouliang="";
//        teshuyaoqiu="";
    }

    //获取用户填写的数据
    private void huoqushuju() {
        sanjifenleiName = etShangpinMing.getText().toString().trim();
        caigouliang = etCaigouliang.getText().toString().trim();
        teshuyaoqiu = etTeshu.getText().toString().trim();
        guige = tvGuige.getText().toString().trim();
    }
}
