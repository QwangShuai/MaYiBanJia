package com.mingmen.mayi.mayibanjia.http.result;

import android.util.Log;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import org.json.JSONObject;

import io.reactivex.functions.Function;

public class ResultMap<T> implements Function<ResultModel<T>, T> {

  @Override
  public T apply(ResultModel<T> httpResult) {
    if ("0000".equals(httpResult.getStatus())) {
      Log.e("codecode", httpResult.getStatus());
      Log.e("message", httpResult.getMsg());
      Log.e("object", new Gson().toJson(httpResult.getData()) + "---");
      return httpResult.getData();
    } else {
      Log.e("najsjsjsj", "请求失败了hhhhh");
      Log.e("codecode", httpResult.getStatus());
      Log.e("message", httpResult.getMsg());
      Log.e("object", httpResult.getData() + "---");
      ToastUtil.showToast(httpResult.getMsg().toString());

      throw new RuntimeException("请求失败(code=" + httpResult.getStatus() + ",message=" + httpResult.getMsg() + ")");
//      return httpResult.getData();
    }

  }
}
