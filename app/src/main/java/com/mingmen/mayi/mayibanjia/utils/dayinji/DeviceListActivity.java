package com.mingmen.mayi.mayibanjia.utils.dayinji;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import HPRTAndroidSDKA300.HPRTPrinterHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceListActivity extends Activity {

    @BindView(R.id.title_paired_devices)
    TextView titlePairedDevices;
    @BindView(R.id.paired_devices)
    ListView pairedDevices;
    @BindView(R.id.title_new_devices)
    TextView titleNewDevices;
    @BindView(R.id.new_devices)
    ListView newDevices;
    @BindView(R.id.button_scan)
    Button buttonScan;
    @BindView(R.id.progress)
    ProgressBar progress;

    public static final String TAG = "DeviceListActivity";
    public static final boolean D = true;
    // 返回  Intent的extra
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    // 成员字段
    public BluetoothAdapter mBtAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private BluetoothDevice mmDevice;
    private BluetoothSocket mmSocket;
    private InputStream mmInStream;
    private OutputStream mmOutStream;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public List<String> pairedDeviceList = null;
    public List<String> newDeviceList = null;
    public ArrayAdapter<String> mPairedDevicesArrayAdapter;
    public ArrayAdapter<String> mNewDevicesArrayAdapter;
    public static String toothAddress = null;
    public static String toothName = null;
    private Context thisCon = null;
    private String strAddressList = "";
    private Thread thread;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            progress.setVisibility(View.GONE);
            ToastUtil.showToastLong("点击触发" + msg.what);
            Intent intent = new Intent();
            intent.putExtra("is_connected", msg.what);
            intent.putExtra("BTAddress", toothAddress);
            setResult(HPRTPrinterHelper.ACTIVITY_CONNECT_BT, intent);
            finish();
        }

        ;
    };
    private Message message;


    private void initData() {
        //启用窗口拓展功能，方便调用

        setResult(Activity.RESULT_CANCELED);
        thisCon = this.getApplicationContext();
        tvTitle.setText("蓝牙设备");
        initView();
        // 初始化 arryadapter 已经配对的设备和新扫描到得设备
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getPairedData());
        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);// 用BroadcastReceiver来取得搜索结果
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intent.addAction(ACTION_PAIRING_REQUEST);
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intent);
        pairedDevices.setAdapter(mPairedDevicesArrayAdapter);
        newDevices.setAdapter(mNewDevicesArrayAdapter);
        try {
            pairedDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean hasConnected = false;
                    progress.setVisibility(View.VISIBLE);
                    try {
                        if (mBtAdapter.isDiscovering()) {
                            mBtAdapter.cancelDiscovery();
                        }

                        //取得蓝牙mvc地址
                        String info = ((TextView) view).getText().toString();
                        toothAddress = info.substring(info.length() - 17);
                        if (!toothAddress.contains(":")) {
                            return;
                        }
                        thread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                try {
                                    new HPRTPrinterHelper(thisCon, HPRTPrinterHelper.PRINT_NAME_A300);
                                    int portOpen = HPRTPrinterHelper.PortOpen("Bluetooth," + toothAddress);
                                    HPRTPrinterHelper.logcat("portOpen:" + portOpen);
                                    message = new Message();
                                    message.what = portOpen;
                                    handler.sendMessage(message);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
//	            hasConnected= ConnectDevice();
//	            if (hasConnected)
//	            {
//	            	DisConnect();
//	            }

                    } catch (Exception e) {
                        progress.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
            });
            newDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean hasConnected = false;
                    progress.setVisibility(View.VISIBLE);
                    try {
                        if (mBtAdapter.isDiscovering()) {
                            mBtAdapter.cancelDiscovery();
                        }

                        //取得蓝牙mvc地址
                        String info = ((TextView) view).getText().toString();
                        toothAddress = info.substring(info.length() - 17);
                        if (!toothAddress.contains(":")) {
                            return;
                        }
                        thread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                try {
                                    new HPRTPrinterHelper(thisCon, HPRTPrinterHelper.PRINT_NAME_A300);
                                    int portOpen = HPRTPrinterHelper.PortOpen("Bluetooth," + toothAddress);
                                    HPRTPrinterHelper.logcat("portOpen:" + portOpen);
                                    message = new Message();
                                    message.what = portOpen;
                                    handler.sendMessage(message);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
//	            hasConnected= ConnectDevice();
//	            if (hasConnected)
//	            {
//	            	DisConnect();
//	            }

                    } catch (Exception e) {
                        progress.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception excpt) {
            ToastUtil.showToastLong("暂无蓝牙设备链接");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //     TODO: add setContentView(...) invocation
        this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        this.setContentView(R.layout.activity_device_list);
        initData();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_paired_devices, R.id.title_new_devices, R.id.button_scan,R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_paired_devices:
                break;
            case R.id.title_new_devices:
                break;
            case R.id.button_scan:
                strAddressList = "";
                doDiscovery();
                buttonScan.setVisibility(View.GONE);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }


    //取得已经配对的蓝牙信息,用来加载到ListView中去
    public List<String> getPairedData() {
        List<String> data = new ArrayList<String>();
        //默认的蓝牙适配器
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        // 得到当前的一个已经配对的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) //遍历
            {
                data.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = "暂无已链接蓝牙设备";
            data.add(noDevices);
        }
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 确认是否还需要做扫描
        if (mBtAdapter != null)
            mBtAdapter.cancelDiscovery();
        if (thread != null) {
            Thread dummy = thread;
            thread = null;
            dummy.interrupt();
        }
    }

    /**
     * 启动装置发现的BluetoothAdapter
     */
    public void doDiscovery() {
        if (D) Log.d(TAG, "doDiscovery()");
        // 在标题中注明扫描
        setProgressBarIndeterminateVisibility(true);
        setTitle("蓝牙设备扫描");
        // 打开子标题的新设备
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
        // 若启动了扫描，关闭扫描
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
        //扫描
        int intStartCount = 0;
        while (!mBtAdapter.startDiscovery() && intStartCount < 5) {
            Log.e("BlueTooth", "扫描尝试失败");
            intStartCount++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 扫描完成时候，改变按钮text
    public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            BluetoothDevice device = null;
            // 搜索设备时，取得设备的MAC地址
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                    if (device.getBluetoothClass().getMajorDeviceClass() == 1536) {
                        if (!strAddressList.contains(device.getAddress())) {
                            Bundle b = intent.getExtras();
                            String object = String.valueOf(b.get("android.bluetooth.device.extra.RSSI"));
                            int valueOf = Integer.valueOf(object);
                            float power = (float) ((Math.abs(valueOf) - 59) / (10 * 2.0));
                            float pow = (float) Math.pow(10, power);
                            strAddressList += device.getAddress() + ",";
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            mNewDevicesArrayAdapter.add(device.getName() + "  " + decimalFormat.format(pow) + " m" + "\n" + device.getAddress());
                        }
                    }
                }
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        ToastUtil.showToastLong("正在配对。。。。。。");
                        Log.d("BlueToothTestActivity", "正在配对......");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        ToastUtil.showToastLong("完成配对。。。。。。");
                        Log.d("BlueToothTestActivity", "完成配对");
                        break;
                    case BluetoothDevice.BOND_NONE:
                        ToastUtil.showToastLong("取消配对。。。。。。");
                        Log.d("BlueToothTestActivity", "取消配对");
                    default:
                        break;
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle("蓝牙设备列表");
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                }
            }
        }
    };

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (thread != null) {
            Thread dummy = thread;
            thread = null;
            dummy.interrupt();
        }
    }

    private void initView() {
        titlePairedDevices = findViewById(R.id.title_paired_devices);
        pairedDevices = findViewById(R.id.paired_devices);
        titleNewDevices = findViewById(R.id.title_new_devices);
        newDevices = findViewById(R.id.new_devices);
        buttonScan = findViewById(R.id.button_scan);
        progress = findViewById(R.id.progress);
    }

}
