package com.mingmen.mayi.mayibanjia.utils.dayinji;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.dayinji.adapter.BluetoothDeviceAdapter;
import com.mingmen.mayi.mayibanjia.utils.dayinji.bean.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class DaYinJiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.iv_zs)
    ImageView ivZs;

    private Bitmap bitmap;
    private BluetoothDeviceAdapter bluetoothDeviceAdapter;
    /**
     * 创建接口对象
     */
    private zpBluetoothPrinter mZpAPI;
    /**
     * 存储蓝牙设备列表
     */
    private List<BluetoothDevice> mBluetoothDeviceList = new ArrayList<>();


    /**
     * 获取当前activity对象
     *
     * @return 返回当前activity对象
     */
    private Activity getActivity() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_da_yin_ji;
    }

    @Override
    protected void initData() {
        tvTitle.setText("打印机");
        //初始化打印机控件
        //实例化列表适配器
        bluetoothDeviceAdapter = new BluetoothDeviceAdapter(mBluetoothDeviceList, getActivity());
        //给列表设置适配器
        listView.setAdapter(bluetoothDeviceAdapter);
        init();
        //获取已配对打印机，遍历添加到打印机列表中
        //获取图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.qr_code);
        Glide.with(this).load(getIntent().getStringExtra("iv")).into(ivZs);


        //点击列表监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //提示开始连接
                ToastUtil.showToastLong(R.string.start_connect + "");
                //连接打印机,连接之前确认是SDK所支持的打印机
                if (isSupported(mBluetoothDeviceList.get(position).getPrinterName())) {
                    //开启打印机,传入打印机MAC地址
                    if (mZpAPI.openPrinterSync(mBluetoothDeviceList.get(position).getPrinterAddress())) {
                        //提示连接成功
                        ToastUtil.showToastLong(R.string.connect_success + "");
                    } else {
                        //提示连接失败
                        ToastUtil.showToastLong(R.string.connect_fail + "");
                    }

                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_print})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.btn_print:
                //打印
                if (printLabel()) {
                    //提示打印成功
                    ToastUtil.showToastLong(R.string.print_success+"");
                } else {
                    //提示打印失败
                    ToastUtil.showToastLong(R.string.print_fail+"");
                }
                break;
        }
    }
    /**
     * 初始化打印控件
     */
    private void init() {
        //打印控件不为null,进行实例化
        if (mZpAPI == null) {
            mZpAPI = new zpBluetoothPrinter(getActivity());
            int size = mZpAPI.getAllPrinters()==null?0:mZpAPI.getAllPrinters().size();
            for (int i = 0; i < size; i++) {
                mBluetoothDeviceList.add(new BluetoothDevice(mZpAPI.getAllPrinters().get(i).GetName(), mZpAPI.getAllPrinters().get(i).Getmac()));
                bluetoothDeviceAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 判断给定的打印机名称是否是接口所支持的打印机,防止非SDK支持打印机调用出错
     *
     * @param printerName 打印机名称
     * @return 是否支持
     */
    public boolean isSupported(String printerName) {
        return Pattern.compile("^B3" + "_\\d{4}[L]?$").matcher(printerName).matches();
    }




    /**
     * 打印标签
     *
     * @return 打印是否成功
     */
    private boolean printLabel() {

        /*
         *打印标签方法
         * 1.bitmap 需要打印的图像
         * 2.xx 标签x轴偏移（单位像素）
         * 3.yy 标签y轴偏移（单位像素）
         * 4.width 标签宽度（单位像素）
         * 5.height 标签高度（单位像素）
         * 6.Rotate 旋转角度（0,90,180,270）
         * 7.gotopaper 标签类型定位类型 0连续纸,1间隙纸,2 左侧黑标定位,3右侧黑标定位,4孔定位,5背部黑标定位
         */
        return mZpAPI.drawBitmap(bitmap, 50, 50, 200, 200, 0, 1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭打印机

    }
}
