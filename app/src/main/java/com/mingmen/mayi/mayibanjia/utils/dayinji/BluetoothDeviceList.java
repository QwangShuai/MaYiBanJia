package com.mingmen.mayi.mayibanjia.utils.dayinji;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BluetoothDeviceList extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lvPairedDevices)
    ListView lvPairedDevice;
    @BindView(R.id.btBluetoothScan)
    Button btBluetoothScan;

    /**
     * Debugging
     */
    private static final String DEBUG_TAG = "DeviceListActivity";

    /**
     * Member fields
     */
    //   private TextView tvPairedDevice = null, tvNewDevice = null;
    private Button btDeviceScan = null;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> DevicesArrayAdapter;
    //   private ArrayAdapter<String> mNewDevicesArrayAdapter;
    public static final String EXTRA_DEVICE_ADDRESS = "address";
    public static final int    REQUEST_ENABLE_BT      = 2;
    public static final int    REQUEST_CONNECT_DEVICE = 3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bluetooth_device_list;
    }

    @Override
    protected void initData() {
        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mFindBlueToothReceiver, filter);
        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mFindBlueToothReceiver, filter);
        initBluetooth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right, R.id.btBluetoothScan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                break;
            case R.id.tv_right:
                break;
            case R.id.btBluetoothScan:
                btBluetoothScan.setVisibility(View.GONE);
                discoveryDevice();
                break;
        }
    }

    private void initBluetooth(){
        // Get the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            ToastUtil.showToastLong("Bluetooth is not supported by the device");
        } else {
            // If BT is not on, request that it be enabled.
            // setupChat() will then be called during onActivityResult
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent,
                        REQUEST_ENABLE_BT);
            } else {
                getDeviceList();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure we're not doing discovery anymore
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        if (mFindBlueToothReceiver != null) {
            unregisterReceiver(mFindBlueToothReceiver);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                // bluetooth is opened
                getDeviceList();
            } else {
                ToastUtil.showToastLong(""+R.string.bluetooth_is_not_enabled);
            }
        }
    }
    protected void getDeviceList() {
        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        DevicesArrayAdapter = new ArrayAdapter<>(this,
                R.layout.bluetooth_device_name_item);
//        mNewDevicesArrayAdapter = new ArrayAdapter<>(this,
//                R.layout.bluetooth_device_name_item);
        lvPairedDevice.setAdapter(DevicesArrayAdapter);
        lvPairedDevice.setOnItemClickListener(mDeviceClickListener);
//        lvNewDevice.setAdapter(mNewDevicesArrayAdapter);
//        lvNewDevice.setOnItemClickListener(mDeviceClickListener);
//        // Get the local Bluetooth adapter
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices, add each one to the ArrayAdapter
        DevicesArrayAdapter.add(getString(R.string.str_title_pairedev));
        if (pairedDevices.size() > 0) {
            //  tvPairedDevice.setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                DevicesArrayAdapter.add(device.getName() + "\n"
                        + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired)
                    .toString();
            DevicesArrayAdapter.add(noDevices);
        }
    }

    /**
     * changes the title when discovery is finished
     */
    private final BroadcastReceiver mFindBlueToothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed
                // already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    DevicesArrayAdapter.add(device.getName() + "\n"
                            + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle(R.string.select_bluetooth_device);
                Log.i("tag", "finish discovery" + (DevicesArrayAdapter.getCount()-2));
                if (DevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(
                            R.string.none_bluetooth_device_found).toString();
                    DevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };

    private void discoveryDevice() {
        // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);
        setTitle(R.string.scaning);
        // Turn on sub-title for new devices
        //tvNewDevice.setVisibility(View.VISIBLE);
        DevicesArrayAdapter.add(getString(R.string.str_title_newdev));
        // lvNewDevice.setVisibility(View.VISIBLE);
        // If we're already discovering, stop it
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        // Request discover from BluetoothAdapter
        mBluetoothAdapter.startDiscovery();
    }

    /**
     * The on-click listener for all devices in the ListViews
     */
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Cancel discovery because it's costly and we're about to connect
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String noDevices = getResources().getText(R.string.none_paired).toString();
            String noNewDevice = getResources().getText(R.string.none_bluetooth_device_found).toString();
            Log.i("tag", info);
            if (!info.equals(noDevices) && !info.equals(noNewDevice)&&!info.equals(getString(R.string.str_title_newdev))&&!info.equals(getString(R.string.str_title_pairedev))) {
                mBluetoothAdapter.cancelDiscovery();
                String address = info.substring(info.length() - 17);
                // Create the result Intent and include the MAC address
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DEVICE_ADDRESS, address);
                // Set result and finish this Activity
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    };
}
