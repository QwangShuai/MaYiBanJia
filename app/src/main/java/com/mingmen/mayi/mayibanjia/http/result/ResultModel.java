package com.mingmen.mayi.mayibanjia.http.result;

public class ResultModel<T> {

    private String status;
    private String msg;
    private T object;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public T getData() {
        return object;
    }

    public void setData(T data) {
        this.object = data;
    }
}
