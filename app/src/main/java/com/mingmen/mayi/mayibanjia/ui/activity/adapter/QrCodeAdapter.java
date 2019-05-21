package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gprinter.command.CpclCommand;
import com.gprinter.command.EscCommand;
import com.gprinter.command.LabelCommand;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DaYinQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.DaYinQrCodeActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;
import com.mingmen.mayi.mayibanjia.utils.dayinji.BluetoothDeviceList;
import com.mingmen.mayi.mayibanjia.utils.dayinji.DeviceConnFactoryManager;
import com.mingmen.mayi.mayibanjia.utils.dayinji.PrinterCommand;
import com.mingmen.mayi.mayibanjia.utils.dayinji.ThreadPool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/8.
 */

public class QrCodeAdapter extends RecyclerView.Adapter<QrCodeAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<DaYinQrCodeBean> mList;
    private DaYinQrCodeActivity activity;
    private BluetoothAdapter bluetoothAdapter;

    public QrCodeAdapter(Context mContext, List<DaYinQrCodeBean> list, DaYinQrCodeActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new QrCodeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qr_code, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final DaYinQrCodeBean bean = mList.get(position);
        GlideUtils.cachePhoto(mContext,holder.iv_qr_code,bean.getTwocode());
//        holder.tv_biaoshi.setText(bean.getIdentifying());
        holder.tv_dianpu.setText(bean.getCompany_name() + "(餐厅端)");
//        holder.tv_dizhi.setText(bean.getCompanyAddress());
        holder.tv_phone.setText(bean.getDriverPhone());
        holder.tv_spmc.setText(bean.getClassify_name());
        holder.tv_spmc.setMarqueeEnable(true);
        // holder.tv_weiyima.setText(bean.getOnlyCode());
        holder.tv_onlyCode.setText("唯一码" + bean.getOnlyCode());
        holder.tv_chepaihao.setText(bean.getPlateNumber());
//        holder.tv_dianming.setText(bean.getGy_company_name()+"(卖家店铺)");
        holder.tv_zuofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .delQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bean.getTwocode_id()))
                        .setDataListener(new HttpDataListener<String>() {
                            @Override
                            public void onNext(String data) {
//                                mList.remove(position);
//                                notifyDataSetChanged();
                                activity.getQrCodeList();
                            }
                        });
            }
        });
        holder.tv_dayin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter == null) {
                    ToastUtil.showToastLong("该设备暂不支持蓝牙功能");
                } else if (bluetoothAdapter.isEnabled()) {
//                    Bitmap bitmap = convertViewToBitmap(holder.rl_dayin, holder.rl_dayin.getWidth(), holder.rl_dayin.getHeight());
                    if (true) {
                        activity.dayinQrCode(holder.rl_dayin);
                    } else {
                        Intent it = new Intent(mContext, BluetoothDeviceList.class);
//                    it.putExtra("iv",bean.getTwocode());
                        mContext.startActivity(it);
                    }
                } else {
                    ToastUtil.showToastLong("请先打开蓝牙");
                }


//                activity.dayinQrCode(holder.rl_dayin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_suoyin)
        TextView tv_suoyin;
        @BindView(R.id.iv_qr_code)
        ImageView iv_qr_code;
        @BindView(R.id.tv_biaoshi)
        TextView tv_biaoshi;
        @BindView(R.id.tv_dianpu)
        TextView tv_dianpu;
        @BindView(R.id.tv_spmc)
        MarqueeTextView tv_spmc;
        @BindView(R.id.tv_onlyCode)
        TextView tv_onlyCode;
        @BindView(R.id.tv_dizhi)
        TextView tv_dizhi;
        @BindView(R.id.tv_phone)
        TextView tv_phone;
        @BindView(R.id.tv_zuofei)
        TextView tv_zuofei;
        @BindView(R.id.tv_dayin)
        TextView tv_dayin;
        @BindView(R.id.rl_dayin)
        RelativeLayout rl_dayin;
        //        @BindView(R.id.tv_weiyima)
//                TextView tv_weiyima;
        @BindView(R.id.tv_dianming)
        TextView tv_dianming;
        @BindView(R.id.tv_chepaihao)
        TextView tv_chepaihao;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        Bitmap bm = Bitmap.createScaledBitmap(bitmap, 383, 200, true);
        return bm;
    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }
}
