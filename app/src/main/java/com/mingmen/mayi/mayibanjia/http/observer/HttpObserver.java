package com.mingmen.mayi.mayibanjia.http.observer;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HttpObserver<T> implements Observer<T> {

    private HttpDataListener mSubscriberOnNextListener;
    private WeakReference<Context> context;
    private ProgressDialog dialog;

    public HttpObserver(HttpDataListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = new WeakReference<>(context);
        initProgressDialog();

    }

    public HttpObserver(HttpDataListener mSubscriberOnNextListener, Context context,boolean isdialog) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = new WeakReference<>(context);
        if (isdialog){
            initProgressDialog();
        }else{

        }
    }

    //自定义ProgressDialog提示文字
    public HttpObserver(HttpDataListener mSubscriberOnNextListener, Context context, String message) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = new WeakReference<>(context);
        initProgressDialog(message);
    }

    //自定义ProgressDialog
    public HttpObserver(HttpDataListener mSubscriberOnNextListener, Context context, ProgressDialog dialog) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = new WeakReference<>(context);
        this.dialog = dialog;
    }

    private void initProgressDialog() {
        Context context = this.context.get();
        if (dialog == null && context != null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("加载中……");
            dialog.setCancelable(false);
        }
    }

    private void initProgressDialog(String message) {
        Context context = this.context.get();
        if (dialog == null && context != null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage(message);
            dialog.setCancelable(false);
        }
    }

    private void showProgressDialog() {
        Context context = this.context.get();
        if (dialog == null || context == null) return;
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        showProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        Context context = this.context.get();
        if (context == null) return;
        if (e instanceof SocketTimeoutException) {
            Log.e("SocketTimeoutException", "请求超时");
            ToastUtil.showToast("请求超时,请稍后重试");
        } else if (e instanceof ConnectException) {
            Log.e("ConnectException", "网络中断，请检查您的网络状态");
            ToastUtil.showToast("网络中断，请检查您的网络状态");
        } else {
            Log.e("e.getMessage()", e.getMessage());
//            ToastUtil.showToast(e.getMessage());
            Log.e("http", "error----------->" + e.toString());

        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    public T onNextT(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
        return  t;
    }

}