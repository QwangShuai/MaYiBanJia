package com.mingmen.mayi.mayibanjia.utils.dayinji.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.dayinji.bean.BluetoothDevice;

import java.util.List;

/**
 * 蓝牙设备列表适配器
 *
 * @author 张彬
 * @date 2018.07.10
 */
public class BluetoothDeviceAdapter extends BaseAdapter {

   @SuppressWarnings("CanBeFinal")
   private List<BluetoothDevice> mBluetoothDeviceList;
   private LayoutInflater mLayoutInflater;

    public BluetoothDeviceAdapter(List<BluetoothDevice> bluetoothDeviceList, Context context) {
        mBluetoothDeviceList = bluetoothDeviceList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBluetoothDeviceList == null ? 0 : mBluetoothDeviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBluetoothDeviceList == null ? null : mBluetoothDeviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        BluetoothDevice bluetoothDevice = mBluetoothDeviceList.get(position);
        ViewHolder viewHolder;
        if (contentView == null) {
            contentView = mLayoutInflater.inflate(R.layout.blue_device_item, null);
            viewHolder = new ViewHolder();
            viewHolder.printerName = contentView.findViewById(R.id.tv_blue_name);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        viewHolder.printerName.setText(bluetoothDevice.getPrinterName() == null ? "" : bluetoothDevice.getPrinterName());
        return contentView;
    }

    private class ViewHolder {
        TextView printerName;
    }

}
