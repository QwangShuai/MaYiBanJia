package com.mingmen.mayi.mayibanjia.http.result;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.ui.activity.LoginActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.json.JSONObject;

import io.reactivex.functions.Function;

public class ResultMap<T> implements Function<ResultModel<T>, T> {
  private Context mContext;
  public ResultMap(Context mContext){
    this.mContext = mContext;
  }
  @Override
  public T apply(ResultModel<T> httpResult) {
    if ("0000".equals(httpResult.getStatus())) {
      Log.e("codecode", httpResult.getStatus());
      Log.e("message", httpResult.getMsg());
      Log.e("object", new Gson().toJson(httpResult.getData()) + "---");
      return httpResult.getData();
    } else if("8888".equals(httpResult.getStatus())){
//      BaseActivity.showDialog(mContext,httpResult.getMsg());
      ToastUtil.showToastLong(httpResult.getMsg());
      BaseActivity.goLogin(mContext,"login");
      throw new RuntimeException(httpResult.getMsg());
//      return httpResult.getData();
    } else if("9999".equals(httpResult.getStatus())){
      ToastUtil.showToastLong(httpResult.getMsg());
//      BaseActivity.goLogin(mContext,"");
      LoginActivity.instance.setView();
      throw new RuntimeException(httpResult.getMsg());
    } else if("6666".equals(httpResult.getStatus())){
      BaseActivity.showDialog(mContext,httpResult.getMsg());
      throw new RuntimeException(httpResult.getMsg());
    }  else if("1024".equals(httpResult.getStatus())){
      BaseActivity.exitApp(mContext,httpResult.getMsg());
      throw new RuntimeException(httpResult.getMsg());
    } else {
      Log.e("codecode", httpResult.getStatus());
      Log.e("message", httpResult.getMsg());
      Log.e("object", httpResult.getData() + "---");
      ToastUtil.showToastLong(httpResult.getMsg().toString());

      throw new RuntimeException("请求失败(code=" + httpResult.getStatus() + ",message=" + httpResult.getMsg() + ")");
//      return httpResult.getData();
    }
  }
}
