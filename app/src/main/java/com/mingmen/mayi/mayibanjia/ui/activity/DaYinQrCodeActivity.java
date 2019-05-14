package com.mingmen.mayi.mayibanjia.ui.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gprinter.command.CpclCommand;
import com.gprinter.command.EscCommand;
import com.gprinter.command.LabelCommand;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.DaYinQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.QrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.mingmen.mayi.mayibanjia.utils.dayinji.BluetoothDeviceList;
import com.mingmen.mayi.mayibanjia.utils.dayinji.Constant;
import com.mingmen.mayi.mayibanjia.utils.dayinji.DeviceConnFactoryManager;
import com.mingmen.mayi.mayibanjia.utils.dayinji.PrinterCommand;
import com.mingmen.mayi.mayibanjia.utils.dayinji.ThreadPool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_ATTACHED;
import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;

public class DaYinQrCodeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.rv_qr_code)
    RecyclerView rv_qr_code;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.bt_add_qr_code)
    Button btAddQrCode;
    @BindView(R.id.tv_tishi_left)
    TextView tvTishiLeft;
    @BindView(R.id.tv_tishi_center)
    TextView tvTishiCenter;
    @BindView(R.id.tv_tishi_right)
    TextView tvTishiRight;
    @BindView(R.id.ll_list_null)
    LinearLayout llListNull;
    private Context mContext;
    private String id = "";
    private String sp_id = "";
    private String type = "2";
    private QrCodeAdapter adapter;
    private int count = 0;
    private ConfirmDialog confirmDialog;

    //打印机
    private ThreadPool threadPool;
    private static final int	CONN_PRINTER		= 0x12;
    private static final int	REQUEST_CODE = 0x004;
    /**
     * 连接状态断开
     */
    private static final int CONN_STATE_DISCONN = 0x007;
    /**
     * 使用打印机指令错误
     */
    private static final int PRINTER_COMMAND_ERROR = 0x008;
    private int dyid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_da_yin_qr_code;
    }

    @Override
    protected void initData() {
        mContext = DaYinQrCodeActivity.this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        id = getIntent().getStringExtra("id");
        sp_id = getIntent().getStringExtra("sp_id");
        type = getIntent().getStringExtra("type");
        if (type.equals("0")) {
            llAdd.setVisibility(View.GONE);
            btAddQrCode.setVisibility(View.GONE);
        }
        tvTishiLeft.setText("点击右上角");
        tvTishiCenter.setText("新增");
        tvTishiRight.setText("生成二维码用于贴在商品外包装");
        getQrCodeList();
    }

    @OnClick({R.id.iv_back, R.id.tv_add, R.id.bt_add_qr_code})
    protected void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                addQrCode();
                break;
            case R.id.bt_add_qr_code:
                if (count == 0) {
                    ToastUtil.showToast("暂无二维码");
                } else {
                    confirmDialog.showDialog("点击确认按钮后所有商品二维码标签将不可编辑，请慎重操作");
                    confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            packageEnd();
                            confirmDialog.cancel();
                        }
                    });
                    confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmDialog.cancel();
                        }
                    });

                }
                break;
        }
    }

    public void addQrCode() {//新增
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .createQrCode(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, sp_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        getQrCodeList();
                    }
                });
    }

    public void packageEnd() {//打包完成
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .packageEnd(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, sp_id))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
//                        ToastUtil.showToast("打包完成,作废功能和新增功能已关闭");
                        llAdd.setVisibility(View.GONE);
                        btAddQrCode.setVisibility(View.GONE);

                    }
                });
    }

    public void getQrCodeList() {//获取列表
        Log.e("2222", "巴巴爱你");
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .getQrCodeList(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, sp_id))
                .setDataListener(new HttpDataListener<List<DaYinQrCodeBean>>() {

                    @Override
                    public void onNext(List<DaYinQrCodeBean> data) {
                        count = data == null ? 0 : data.size();
                        tvNo.setText("" + count);
                        if(count==0){
                            rv_qr_code.setVisibility(View.GONE);
                            llListNull.setVisibility(View.VISIBLE);
                        } else {
                            rv_qr_code.setVisibility(View.VISIBLE);
                            llListNull.setVisibility(View.GONE);
                        }
                        if (count == 0)
                            return;
//                        if(StringUtil.isValid(data.getIs_true())&&data.getIs_true().equals("0")){
//                            llAdd.setVisibility(View.GONE);
//                            btAddQrCode.setVisibility(View.GONE);
//                        }
//                        Log.e("12333",data.getList().size()+"");
                        adapter = new QrCodeAdapter(DaYinQrCodeActivity.this, data, DaYinQrCodeActivity.this);
                        rv_qr_code.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        rv_qr_code.setAdapter(adapter);
                    }
                });
    }

    public void dayinQrCode(View v) {
        final Bitmap bitmap = convertViewToBitmap(v,v.getWidth(),v.getHeight());
        threadPool = ThreadPool.getInstantiation();
        threadPool.addTask( new Runnable()
        {
            @Override
            public void run()
            {
                if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid] == null ||
                        !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].getConnState() )
                {
                    mHandler.obtainMessage( CONN_PRINTER ).sendToTarget();
                    return;
                }

                if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].getCurrentPrinterCommand() == PrinterCommand.CPCL )
                {
                    Log.e("run: ","CPCL" );
                    CpclCommand cpcl = new CpclCommand();
                    cpcl.addInitializePrinter( 1500, 1 );
                    cpcl.addCGraphics( 0, 0, (80 - 10) * 8, bitmap );
                    cpcl.addPrint();
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].sendDataImmediately( cpcl.getCommand() );
                } else if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].getCurrentPrinterCommand() == PrinterCommand.TSC )
                {
                    Log.e("run: ","TSC" );
                    LabelCommand labelCommand = new LabelCommand();
                    labelCommand.addDensity(LabelCommand.DENSITY.DNESITY10);  //设置打印浓度
                    labelCommand.addSize( 80, 180 );
                    labelCommand.addCls();
                    labelCommand.addBitmap( 0, 40, (80 - 10) * 8, bitmap );
                    labelCommand.addPrint( 1 );
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].sendDataImmediately( labelCommand.getCommand() );
                }else if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].getCurrentPrinterCommand() == PrinterCommand.ESC )
                {
                    Log.e("run: ","ESC" );
                    EscCommand esc = new EscCommand();
                    esc.addInitializePrinter();
                    esc.addInitializePrinter();
                    esc.addRastBitImage( bitmap, (80 - 10) * 8, 0 );
                    esc.addPrintAndLineFeed();
                    esc.addPrintAndLineFeed();
                    esc.addPrintAndLineFeed();
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].sendDataImmediately( esc.getCommand() );
                }
            }
        } );
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        IntentFilter filter = new IntentFilter( ACTION_USB_PERMISSION );
//        filter.addAction( ACTION_USB_DEVICE_DETACHED );
//        filter.addAction( ACTION_QUERY_PRINTER_STATE );
//        filter.addAction( DeviceConnFactoryManager.ACTION_CONN_STATE );
//        filter.addAction( ACTION_USB_DEVICE_ATTACHED );
//        registerReceiver( receiver, filter );
//    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }

    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }
//public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
//    Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
//    view.draw(new Canvas(bitmap));
//    Bitmap bm = Bitmap.createScaledBitmap(bitmap, 383, 200, true);
//    return bm;
//}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭打印机

    }
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage( Message msg )
        {
            switch ( msg.what )
            {
                case CONN_STATE_DISCONN:
                    if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid] != null || !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].getConnState() )
                    {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].closePort( dyid );
                        ToastUtil.showToastLong(R.string.str_disconnect_success+"");
                    }
                    break;
                case PRINTER_COMMAND_ERROR:
                    ToastUtil.showToastLong(R.string.str_choice_printer_command+"");
                    break;
                case CONN_PRINTER:
                    ToastUtil.showToastLong(R.string.str_cann_printer+"");
//                    Intent it = new Intent(mContext, BluetoothDeviceList.class);
//                    it.putExtra("iv",bean.getTwocode());
//                    mContext.startActivity(it);
                    startActivityForResult( new Intent( mContext, BluetoothDeviceList.class ), Constant.BLUETOOTH_REQUEST_CODE );
                    break;
//                case MESSAGE_UPDATE_PARAMETER:
//                    String strIp = msg.getData().getString( "Ip" );
//                    String strPort = msg.getData().getString( "Port" );
//                    /* 初始化端口信息 */
//                    new DeviceConnFactoryManager.Build()
//                            /* 设置端口连接方式 */
//                            .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.WIFI )
//                            /* 设置端口IP地址 */
//                            .setIp( strIp )
//                            /* 设置端口ID（主要用于连接多设备） */
//                            .setId( dyid )
//                            /* 设置连接的热点端口号 */
//                            .setPort( Integer.parseInt( strPort ) )
//                            .build();
//                    threadPool = ThreadPool.getInstantiation();
//                    threadPool.addTask( new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].openPort();
//                        }
//                    } );
//                    break;
                default:
                    new DeviceConnFactoryManager.Build()
                            /* 设置端口连接方式 */
                            .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.WIFI )
                            /* 设置端口IP地址 */
                            .setIp( "192.168.2.227" )
                            /* 设置端口ID（主要用于连接多设备） */
                            .setId( dyid )
                            /* 设置连接的热点端口号 */
                            .setPort( 9100 )
                            .build();
                    threadPool.addTask( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].openPort();
                        }
                    } );
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK ) {
            Log.e("onActivityResult: ","requestCode:"+requestCode );
            switch (requestCode) {
                /*蓝牙连接*/
                case Constant.BLUETOOTH_REQUEST_CODE: {
                    closeport();
                    /*获取蓝牙mac地址*/
                    String macAddress = data.getStringExtra(BluetoothDeviceList.EXTRA_DEVICE_ADDRESS);
                    /* 初始化话DeviceConnFactoryManager */
                    new DeviceConnFactoryManager.Build()
                            .setId(dyid)
                            /* 设置连接方式 */
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
                            /* 设置连接的蓝牙mac地址 */
                            .setMacAddress(macAddress)
                            .build();
                    /* 打开端口 */
                    Log.d("1111", "onActivityResult: 连接蓝牙" + dyid);
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].openPort();
                        }
                    });

                    break;
                }
                default:
                    break;
            }
        }
    }

    /**
     * 重新连接回收上次连接的对象，避免内存泄漏
     */
    private void closeport()
    {
        if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid] != null &&DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].mPort != null )
        {
//            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].reader.cancel();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].mPort.closePort();
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[dyid].mPort = null;
        }
    }
}
