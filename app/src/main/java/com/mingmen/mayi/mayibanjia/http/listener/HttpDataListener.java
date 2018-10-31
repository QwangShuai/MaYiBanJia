package com.mingmen.mayi.mayibanjia.http.listener;

import java.util.List;

public interface HttpDataListener<T> {
    void onNext(T t);
}
