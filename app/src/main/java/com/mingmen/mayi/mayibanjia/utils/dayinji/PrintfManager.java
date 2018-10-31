package com.mingmen.mayi.mayibanjia.utils.dayinji;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.android.print.sdk.PrinterConstants;
import com.android.print.sdk.PrinterInstance;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrintfManager {

    public static int ORDINARY = 1, SERIALIZE = 2;

    protected String TAG = "PrintfManager";

    protected List<BluetoothChangLister> bluetoothChangListerList = new ArrayList<>();

    private ConnectSuccess connectSuccess;

    public void setConnectSuccess(ConnectSuccess connectSuccess) {
        this.connectSuccess = connectSuccess;
    }

    /**
     * 是否正在连接
     */
    private volatile boolean CONNECTING = false;

    public boolean isCONNECTING() {
        return CONNECTING;
    }

    /**
     * 添加蓝牙改变监听
     *
     * @param bluetoothChangLister
     */
    public void addBluetoothChangLister(BluetoothChangLister bluetoothChangLister) {
        bluetoothChangListerList.add(bluetoothChangLister);
    }

    /**
     * 解除观察者
     *
     * @param bluetoothChangLister
     */
    public void removeBluetoothChangLister(BluetoothChangLister bluetoothChangLister) {
        if (bluetoothChangLister == null) {
            return;
        }
        if (bluetoothChangListerList.contains(bluetoothChangLister)) {
            bluetoothChangListerList.remove(bluetoothChangLister);
        }
    }

    protected Context context;

    protected PrinterInstance mPrinter;


    private PrintfManager() {
    }

    static class PrintfManagerHolder {
        private static PrintfManager instance = new PrintfManager();
    }


    public static PrintfManager getInstance(Context context) {
        if (PrintfManagerHolder.instance.context == null) {
            PrintfManagerHolder.instance.context = context.getApplicationContext();
        }
        return PrintfManagerHolder.instance;
    }

    public void setPrinter(PrinterInstance mPrinter) {
        this.mPrinter = mPrinter;
    }

    public void connection() {
        if (mPrinter != null) {
            CONNECTING = true;
            mPrinter.openConnection();
        }
    }

    public PrinterInstance getPrinter() {
        return mPrinter;
    }

    private boolean isHasPrinter = false;

    public boolean isConnect() {
        return isHasPrinter;
    }

    public void disConnect(final String text) {
        MyApplication.getInstance().getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                isHasPrinter = false;
                if (mPrinter != null) {
                    mPrinter.closeConnection();
                    mPrinter = null;
                }
                Util.ToastTextThread(context, text);
            }
        });
    }

    public void changBlueName(final String name) {
        //启动线程，来接收数据
        MyApplication.getInstance().getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Util.ToastTextThread(context, context.getString(R.string.chang_bluetooth_name_now));
                    //进入空中AT指令
                    String AT = "$OpenFscAtEngine$";
                    mPrinter.sendByteData(AT.getBytes());
                    Thread.sleep(500);
                    byte[] read = mPrinter.read();
                    if (read == null) {
                        Util.ToastTextThread(context, context.getString(R.string.chang_bluetooth_name_fail));
                    } else {
                        String readString = new String(read);
                        if (readString.contains("$OK,Opened$")) {//进入空中模式
                            mPrinter.sendByteData(("AT+NAME=" + name +"\r\n").getBytes());
                            Thread.sleep(500);
                            byte[] isSuccess = mPrinter.read();
                            if(new String(isSuccess).contains("OK")){
                                Util.ToastTextThread(context, context.getString(R.string.chang_bluetooth_name_success));
                                SharedPreferencesManager.saveBluetoothName(context,name);
                            }else{
                                Util.ToastTextThread(context, context.getString(R.string.chang_bluetooth_name_fail));
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public final static int NAME_CHANG = 104;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String bluetoothName = context.getString(R.string.no_connect_blue_tooth);
            String bluetoothAddress = bluetoothName;
            switch (msg.what) {
                case PrinterConstants.Connect.SUCCESS://成功
                    isHasPrinter = true;
                    Util.ToastText(context, context.getString(R.string.connection_success));
                    bluetoothName = SharedPreferencesManager.getBluetoothName(context);
                    bluetoothAddress = SharedPreferencesManager.getBluetoothAddress(context);
                    if (connectSuccess != null) {
                        connectSuccess.success();
                    }
                    break;
                case PrinterConstants.Connect.FAILED://失败
                    disConnect(context.getString(R.string.connection_fail));
                    break;
                case PrinterConstants.Connect.CLOSED://关闭
                    disConnect(context.getString(R.string.bluetooth_disconnect));
                    break;
                case NAME_CHANG://名称改变
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    bluetoothAddress = device.getAddress();
                    bluetoothName = device.getName();
                    break;

            }

            for (BluetoothChangLister bluetoothChangLister : bluetoothChangListerList) {
                if (bluetoothChangLister != null) {
                    bluetoothChangLister.chang(bluetoothName, bluetoothAddress);
                }
            }
            CONNECTING = false;
        }
    };

    public void printf(final int width, final int height, final Bitmap bitmap, final Activity activity) {
        MyApplication.getInstance().getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if (isConnect()) {
                    Util.ToastTextThread(activity, context.getString(R.string.print_now));
                    realPrintfBitmapByLabelView(width,height,bitmap,128,1);
                } else {
                    Util.ToastTextThread(activity, context.getString(R.string.please_connect_bluetooth));
                    PrintfBlueListActivity.startActivity(activity);
                }
            }
        });

    }

    public void defaultConnection() {
        String bluetoothName = SharedPreferencesManager.getBluetoothName(context);
        if (bluetoothName == null) {
            return;
        }

        String bluetoothAddress = SharedPreferencesManager.getBluetoothAddress(context);
        if (bluetoothAddress == null) {
            return;
        }

        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            if (device.getAddress().equals(bluetoothAddress)) {
                mPrinter = new PrinterInstance(context, device, mHandler);
                connection();
                return;
            }
        }
    }

    /**
     * 清除画布
     */
    public void clearCanvas() {
        mPrinter.sendByteData("CLS\r\n".getBytes());
    }

    /**
     * 初始化画布
     *
     * @param w
     * @param h
     */
    public void initCanvas(int w, int h) {
        byte[] data = new StringBuilder().append("SIZE ").append(w + " mm").append(",")
                .append(h + " mm").append("\r\n").toString().getBytes();
        mPrinter.sendByteData(data);
    }

    /**
     * @param x    ：图片打印的x坐标
     * @param y    ：图片打印的y坐标
     * @param data ：图片资源
     */
    public void printfBitmap(int x, int y, Bitmap data, int concentration) {
        //换行
        byte[] crlf = {0x0d, 0x0a};
        StringBuilder BITMAP = new StringBuilder()
                .append("BITMAP ")
                .append(x)
                .append(",")
                .append(y)
                .append(",")
                .append((data.getWidth()) / 8)
                .append(",")
                .append(data.getHeight())
                .append(",1,");
        byte[] bitmapByte = convertToBMW(data, concentration);
        mPrinter.sendByteData(BITMAP.toString().getBytes());
        mPrinter.sendByteData(bitmapByte);
        mPrinter.sendByteData(crlf);
    }


    public void beginPrintf() {
        beginPrintf(1, 1);
    }

    /**
     * 开始打印
     *
     * @param sequence    : 序列
     * @param groupNumber ：组数
     */
    public void beginPrintf(int sequence, int groupNumber) {
        String PRINT = "PRINT " + sequence + "," + groupNumber + "\r\n";
        mPrinter.sendByteData(PRINT.getBytes());
    }

    /**
     * 连接
     *
     * @param mDevice
     */
    public void openPrinter(final BluetoothDevice mDevice) {
        MyApplication.getInstance().getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                setPrinter(new PrinterInstance(context, mDevice, mHandler));
                // default is gbk...
                connection();
                //连接保存 地址+名称
                SharedPreferencesManager.updateBluetooth(context, mDevice);
            }
        });
    }

    /**
     * 真正打印的地方
     *
     * @param concentration
     * @param number
     */
    private boolean realPrintfBitmapByLabelView(int width,int height,Bitmap bitmap, int concentration, int number) {
        try {
            initCanvas(width, height);
            clearCanvas();
            printfBitmap(0, 0, bitmap, concentration);
            beginPrintf(1, number);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            CrashHandler.getInstance().saveCrashInfo2File(e);
            Util.ToastTextThread(context, context.getString(R.string.printf_error_check));
            return false;
        }
    }

    /**
     * 图片二值化
     *
     * @param bmp
     * @return
     */
    public static byte[] convertToBMW(Bitmap bmp, int concentration) {
        if (concentration <= 0 || concentration >= 255) {
            concentration = 128;
        }
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        byte[] bytes = new byte[(width) / 8 * height];
        int[] p = new int[8];
        int n = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width / 8; j++) {
                for (int z = 0; z < 8; z++) {
                    int grey = bmp.getPixel(j * 8 + z, i);
                    int red = ((grey & 0x00FF0000) >> 16);
                    int green = ((grey & 0x0000FF00) >> 8);
                    int blue = (grey & 0x000000FF);
                    int gray = (int) (0.29900 * red + 0.58700 * green + 0.11400 * blue); // 灰度转化公式
//                    int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                    if (gray <= concentration) {
                        gray = 0;
                    } else {
                        gray = 1;
                    }
                    p[z] = gray;
                }
                byte value = (byte) (p[0] * 128 + p[1] * 64 + p[2] * 32 + p[3] * 16 + p[4] * 8 + p[5] * 4 + p[6] * 2 + p[7]);
                bytes[width / 8 * i + j] = value;
            }
        }
        return bytes;
    }

    public interface BluetoothChangLister {
        void chang(String name, String address);
    }

    public interface ConnectSuccess {
        void success();
    }
}
