package com.mingmen.mayi.mayibanjia.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public abstract class BaseFragment extends Fragment implements StateLayout.OnReloadBtnClickListener {
    protected StateLayout stateLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StatusBarCompat.translucentStatusBar(getActivity());
        if(stateLayout==null){

            stateLayout = new StateLayout(getActivity());
            //设置successView
            stateLayout.setSuccessView(getSuccessView());
            //默认显示loading界面


            //设置重新加载按钮的点击监听
            stateLayout.setOnReloadClickListener(this);

            //加载数据
            loadData();
//			LogUtil.e(this, "stateLayout==null : "+(stateLayout==null));
        }else {

            //复用stateLayout报错：The specified child already has a parent.
            //You must call removeView() on the child's parent first.
            //它爹：NoSaveStateFrameLayout
//			LogUtil.e(this, "parent: "+stateLayout.getParent().getClass().getSimpleName());
            //当我们调用ViewPager.setAdapter的时候，FragmentManager会取出Fragment的View，再外面包裹一层布局，
            //就是NoSaveStateFrameLayout，然后又将NoSaveStateFrameLayout添加到ViewPager中。
            //当我们再次回到首页的时候，由于我们又返回了stateLayout，此时stateLayout已经有父View了，但是FragmentManager
            //不管，又试图在外层包裹NoSaveStateFrameLayout，此时就报错。
            ViewGroup parent = (ViewGroup) stateLayout.getParent();
            if(parent!=null){
                parent.removeView(stateLayout);
            }

            //但是以上的种种分析在4.4之后的版本都没有必要了，因为FragmentManager修复了此Bug
        }
        setHasOptionsMenu(true);
        return stateLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarCompat.translucentStatusBar(getActivity());
    }

    /**
     * 点击的时候重新加载数据
     */
    @Override
    public void onReloadBtnClick() {
        //向用户表示，哥正在加载数据
        loadData();
    }

    /**
     * 返回成功的View
     * @return
     */

    protected abstract View getSuccessView();

    /**
     * 子类加载数据
     */
    protected abstract void loadData();
    public void Jump_intent(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(getContext(), cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}
