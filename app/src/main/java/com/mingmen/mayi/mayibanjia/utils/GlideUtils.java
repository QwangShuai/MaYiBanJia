package com.mingmen.mayi.mayibanjia.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.signature.StringSignature;
import com.mingmen.mayi.mayibanjia.utils.custom.GlideRoundTransform;

/**
 * Created by Administrator on 2019/5/16.
 */

public class GlideUtils {
    public static void cachePhoto(Context mContext, ImageView iv, String url){
        if(TextUtils.isEmpty(url)){
            return;
        }
        if(url.indexOf("?")!=-1) {
            Glide.with(mContext).load(url.substring(0, url.indexOf("?"))).
                    signature(new StringSignature(url.substring(0, url.indexOf("?")))).into(iv);
        } else {
            Glide.with(mContext).load(url).
                    signature(new StringSignature(url)).into(iv);
        }
    }

    public static void cachePhotoRound(Context mContext, ImageView iv, String url,int round){
        if(TextUtils.isEmpty(url)){
            return;
        }
        if(url.indexOf("?")!=-1){
            Glide.with(mContext).load(url.substring(0,url.indexOf("?"))).transform(new GlideRoundTransform(mContext,round)).
                    signature(new StringSignature(url.substring(0,url.indexOf("?")))).into(iv);
        } else {
            Glide.with(mContext).load(url).transform(new GlideRoundTransform(mContext,round)).
                    signature(new StringSignature(url)).into(iv);
        }

    }
}
