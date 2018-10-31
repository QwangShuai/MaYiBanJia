package com.mingmen.mayi.mayibanjia.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2018/8/6/006.
 */

public class JumpUtil {

    public static void Jump_intent(Context mContext, Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(mContext, cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}
