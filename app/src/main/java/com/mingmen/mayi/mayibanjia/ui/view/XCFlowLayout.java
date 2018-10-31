package com.mingmen.mayi.mayibanjia.ui.view;

/**
 * Created by Administrator on 2018/7/12/012.
 */


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caizhiming
 * @created on 2015-4-13
 */
public class XCFlowLayout extends ViewGroup {

    //存储所有子View
    private List<List<View>> mAllChildViews = new ArrayList<>();
    //每一行的高度
    private List<Integer> mLineHeight = new ArrayList<>();

    public XCFlowLayout(Context context) {
        this(context, null);
    }
    public XCFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public XCFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //在onMeasure里，测量所有子View的宽高，以及确定Viewgroup自己的宽高。
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取系统传递过来测量出的宽度 高度，以及相应的测量模式。
        //如果测量模式为 EXACTLY( 确定的dp值，match_parent)，则可以调用setMeasuredDimension()设置，
        //如果测量模式为 AT_MOST(wrap_content),则需要经过计算再去调用setMeasuredDimension()设置
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //计算宽度 高度 //wrap_content测量模式下会使用到:
        //存储最后计算出的宽度，
        int maxLineWidth = 0;
        //存储最后计算出的高度
        int totalHeight = 0;
        //存储当前行的宽度
        int curLineWidth = 0;
        //存储当前行的高度
        int curLineHeight = 0;

        // 得到内部元素的个数
        int count = getChildCount();

        //存储子View
        View child =null;
        //存储子View的LayoutParams
        MarginLayoutParams params =null;
        //子View Layout需要的宽高(包含margin)，用于计算是否越界
        int childWidth;
        int childHeight;

        //遍历子View 计算父控件宽高
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            //如果gone，不测量了
            if (View.GONE == child.getVisibility()) {
                continue;
            }
            //先测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            //获取子View的LayoutParams，(子View的LayoutParams的对象类型，取决于其ViewGroup的generateLayoutParams()方法的返回的对象类型，这里返回的是MarginLayoutParams)
            params = (MarginLayoutParams) child.getLayoutParams();
            //子View需要的宽度 为 子View 本身宽度+marginLeft + marginRight
            childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            Log.i("sss", "子View Layout需要的宽高(包含margin)：childWidth:" + childWidth + "   ,childHeight:" + childHeight);

            //如果当前的行宽度大于 父控件允许的最大宽度 则要换行
            //父控件允许的最大宽度 如果要适配 padding 这里要- getPaddingLeft() - getPaddingRight()
            //即为测量出的宽度减去父控件的左右边距
            if (curLineWidth + childWidth > widthMeasure - getPaddingLeft() - getPaddingRight()) {
                //通过比较 当前行宽 和以前存储的最大行宽,得到最新的最大行宽,用于设置父控件的宽度
                maxLineWidth = Math.max(maxLineWidth, curLineWidth);
                //父控件的高度增加了，为当前高度+当前行的高度
                totalHeight += curLineHeight;
                //换行后 刷新 当前行 宽高数据： 因为新的一行就这一个View，所以为当前这个view占用的宽高(要加上View 的 margin)
                curLineWidth = childWidth;
                curLineHeight = childHeight;
            } else {
                //不换行：叠加当前行宽 和 比较当前行高:
                curLineWidth += childWidth;
                curLineHeight = Math.max(curLineHeight, childHeight);
            }
            //如果已经是最后一个View,要比较当前行的 宽度和最大宽度，叠加一共的高度
            if (i == count - 1) {
                maxLineWidth = Math.max(maxLineWidth, curLineWidth);
                totalHeight += childHeight;
            }
        }

        Log.i("sss", "系统测量允许的尺寸最大值：widthMeasure:" + widthMeasure + "   ,heightMeasure:" + heightMeasure);
        Log.i("sss", "经过我们测量实际的尺寸(不包括父控件的padding)：maxLineWidth:" + maxLineWidth + "   ,totalHeight:" + totalHeight);

        //适配padding,如果是wrap_content,则除了子控件本身占据的控件，还要在加上父控件的padding
        setMeasuredDimension(
                widthMode != MeasureSpec.EXACTLY? maxLineWidth + getPaddingLeft() + getPaddingRight() : widthMeasure,
                heightMode != MeasureSpec.EXACTLY ? totalHeight + getPaddingTop() + getPaddingBottom() : heightMeasure);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////
//        //父控件传进来的宽度和高度以及对应的测量模式
//        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
//        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
//        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
//
//        //如果当前ViewGroup的宽高为wrap_content的情况
//        int width = 0;//自己测量的 宽度
//        int height = 0;//自己测量的高度
//        //记录每一行的宽度和高度
//        int lineWidth = 0;
//        int lineHeight = 0;
//
//        //获取子view的个数
//        int childCount = getChildCount();
//        for(int i = 0;i < childCount; i ++){
//            View child = getChildAt(i);
//            //测量子View的宽和高
//            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            //得到LayoutParams
//            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
//            //子View占据的宽度
//            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
//            //子View占据的高度
//            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
//            //换行时候
//            if(lineWidth + childWidth > sizeWidth){
//                //对比得到最大的宽度
//                width = Math.max(width, lineWidth);
//                //重置lineWidth
//                lineWidth = childWidth;
//                //记录行高
//                height += lineHeight;
//                lineHeight = childHeight;
//            }else{//不换行情况
//                //叠加行宽
//                lineWidth += childWidth;
//                //得到最大行高
//                lineHeight = Math.max(lineHeight, childHeight);
//            }
//            //处理最后一个子View的情况
//            if(i == childCount -1){
//                width = Math.max(width, lineWidth);
//                height += lineHeight;
//            }
//        }
//        //wrap_content
//        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
//                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllChildViews.clear();
        mLineHeight.clear();
        //获取当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        //记录当前行的view
        List<View> lineViews = new ArrayList<View>();
        int childCount = getChildCount();
        for(int i = 0;i < childCount; i ++){
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //如果需要换行
            if(childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width){
                //记录LineHeight
                mLineHeight.add(lineHeight);
                //记录当前行的Views
                mAllChildViews.add(lineViews);
                //重置行的宽高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置view的集合
                lineViews = new ArrayList();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllChildViews.add(lineViews);

        //设置子View的位置
        int left = 0;
        int top = 0;
        //获取行数
        int lineCount = mAllChildViews.size();
        for(int i = 0; i < lineCount; i ++){
            //当前行的views和高度
            lineViews = mAllChildViews.get(i);
            lineHeight = mLineHeight.get(i);
            for(int j = 0; j < lineViews.size(); j ++){
                View child = lineViews.get(j);
                //判断是否显示
                if(child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int cLeft = left + lp.leftMargin;
                int cTop = top + lp.topMargin;
                int cRight = cLeft + child.getMeasuredWidth();
                int cBottom = cTop + child.getMeasuredHeight();
                //进行子View进行布局
                child.layout(cLeft, cTop, cRight, cBottom);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }

    }
    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new MarginLayoutParams(getContext(), attrs);
    }
}