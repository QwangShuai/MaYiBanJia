package com.mingmen.mayi.mayibanjia.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;


import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘佳 on 2018/4/19.
 */


public class PageIndicatorView extends LinearLayout {

    private Context mContext = null;
    private int dotSize = 2; // 指示器的大小（dp）
    private int margins = 4; // 指示器间距（dp）
    private List<View> indicatorViews = null; // 存放指示器

    public PageIndicatorView(Context context) {
        this(context, null);
        init(context);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        setGravity(Gravity.CENTER);
        setOrientation(HORIZONTAL);

        dotSize = AppUtil.Dp2px(context, dotSize);
        margins = AppUtil.Dp2px(context, margins);
    }

    // 初始化指示器，默认选中第一页

    public void initIndicator(int count) {

        if (indicatorViews == null) {
            indicatorViews = new ArrayList<>();
        } else {
            indicatorViews.clear();
            removeAllViews();
        }
        View view;
        LayoutParams params = new LayoutParams(dotSize, dotSize);
        params.setMargins(margins, margins, margins, margins);
        for (int i = 0; i < count; i++) {
            view = new View(mContext);
            view.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
            addView(view, params);
            indicatorViews.add(view);
        }
        if (indicatorViews.size() > 0) {
            indicatorViews.get(0).setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
        }
    }

    //设置选中页
    public void setSelectedPage(int selected) {
        for (int i = 0; i < indicatorViews.size(); i++) {
            if (i == selected) {
                indicatorViews.get(i).setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_20));
            } else {
                indicatorViews.get(i).setBackground(getResources().getDrawable(R.drawable.fillet_solid_cutoff_20));
            }
        }
    }

}