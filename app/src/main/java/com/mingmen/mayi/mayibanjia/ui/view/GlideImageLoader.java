package com.mingmen.mayi.mayibanjia.ui.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.utils.custom.GlideRoundTransform;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/7/5/005.
 */


public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Glide.with(context).load(path).transform(new GlideRoundTransform(context,15)).into(imageView);

    }
}