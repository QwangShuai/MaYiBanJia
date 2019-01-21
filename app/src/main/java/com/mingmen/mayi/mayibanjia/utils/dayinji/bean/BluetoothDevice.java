package com.mingmen.mayi.mayibanjia.utils.dayinji.bean;

/**
 * 蓝牙设备类
 * @author  张彬
 * @date  2018.07.10
 */
public class BluetoothDevice {
    private String mPrinterName;
    private String mPrinterAddress;

    public BluetoothDevice(String printerName, String printerAddress) {
        mPrinterName = printerName;
        mPrinterAddress = printerAddress;
    }

    public String getPrinterName() {
        return mPrinterName;
    }

    public String getPrinterAddress() {
        return mPrinterAddress;
    }




}
