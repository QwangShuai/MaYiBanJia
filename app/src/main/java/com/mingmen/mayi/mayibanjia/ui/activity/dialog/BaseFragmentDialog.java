package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ViewUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.mingmen.mayi.mayibanjia.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author    lingchen
 * Email     838878458@qq.com
 * Time      2017/3/29
 * Function  dialog基类
 */
public abstract class BaseFragmentDialog extends DialogFragment {
    private final String TAG = this.getClass().getSimpleName();

    private Unbinder unbinder;

    /**
     * dialog配置类 用于指定动画、位置等
     *
     * @see #initConfiguration(Configuration)
     */
    private final Configuration configuration = new Configuration();


    public BaseFragmentDialog() {
    }

    /**
     * 获取选择器资源文件
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化完成
     */
    protected abstract void init();

    /**
     * 初始化配置
     *
     * @param configuration 默认配置
     */
    protected  void initConfiguration(Configuration configuration){

    };

    public boolean isRegisteredEventBus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfiguration(configuration);
        setStyle(STYLE_NO_TITLE, configuration.getDialogTheme());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void cancel() {
                if (onDialogCancel())
                    super.cancel();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(configuration.canceledOnTouchOutside);
            dialog.setCancelable(configuration.cancelable);
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                if (configuration.getHeight() != 0)
                    lp.height = configuration.getHeight();
                if (configuration.getWidth() != 0)
                    lp.width = configuration.getWidth();
                if (configuration.getMarginTop() != 0) {
                    lp.y = configuration.getMarginTop();
                }
                window.setAttributes(lp);
                if (configuration.getDialogAnimation() != -1) {
                    window.setWindowAnimations(configuration.getDialogAnimation());
                }
                if (configuration.isClearBehind)
                    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                window.setGravity(configuration.getGravity());
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isRegisteredEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
        unbinder= ButterKnife.bind(this,view);
       // if (systemBarEnabled())
//            initSystemBar();

        init();
    }
//
//    /**
//     * 状态栏颜色设置
//     */
//    private void initSystemBar() {
//        immersionBar = ImmersionBar.with(this, getDialog());
//        View view = this.findView(R.id.idr_StatusBar);
//        if (view != null) {
//            immersionBar.statusBarView(view);
//        }
//        immersionBar.keyboardEnable(true);
//        initImmersionBar(immersionBar);
//        immersionBar.init();
//    }

    /**
     * 是否开启 沉浸式
     */
    protected boolean systemBarEnabled() {
        return false;
    }

//    protected void initImmersionBar(ImmersionBar immersionBar) {

//    }

    /**
     * 查找View
     *
     * @param viewId 资源id
     * @param <T>
     */
    protected final <T extends View> T findView(int viewId) {
        return ButterKnife.findById(getView(), viewId);
    }

    /**
     * 绑定view的Click
     *
     * @param onClickListener 监听器
     * @param views           需要绑定的view
     */
    protected final void setOnClickListener(View.OnClickListener onClickListener, View... views) {
        if (onClickListener == null) return;
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 绑定view的Click
     *
     * @param onClickListener 监听器
     * @param viewIds         需要绑定的view
     */
    protected final void setOnClickListener(View.OnClickListener onClickListener, Integer... viewIds) {
        if (onClickListener == null) return;
        for (Integer view : viewIds) {
            if (view != 0) {
                findView(view).setOnClickListener(onClickListener);
            }
        }
    }

//    /**
//     * 添加防多次点击事件
//     *
//     * @param views
//     * @see #onAntiShakeClickAdapter(View)
//     */
//    protected final void addAntiShakeOnClickListener(View... views) {
//        for (View v : views) {
//            if (v != null) {
//                v.setOnClickListener(antiClick);
//            }
//        }
//    }

//
//    /**
//     * 添加防多次点击事件
//     *
//     * @param viewIds
//     * @see #onAntiShakeClickAdapter(View)
//     */
//    protected final void addAntiShakeOnClickListener(Integer... viewIds) {
//        for (Integer ids : viewIds) {
//            View view = findView(ids);
//            if (view != null) {
//                view.setOnClickListener(antiClick);
//            }
//        }
//    }

//
//    /**
//     * 防多次点击事件逻辑
//     *
//     * @param view
//     * @see #addAntiShakeOnClickListener(View...)
//     */
//    protected void onAntiShakeClickAdapter(View view) {
//
//    }

//    //点击事件，防多次点击
//    private AntiShakeOnClickListener antiClick = new AntiShakeOnClickListener() {
//        @Override
//        protected void onAntiShakeClick(View view) {
//            onAntiShakeClickAdapter(view);
//        }
//    };

    /**
     * 显示
     */
    public <T extends BaseFragmentDialog> T show(FragmentManager fragmentManager) {
        if (!isAdded()) {
            try {
                show(fragmentManager, TAG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) this;
    }


    /**
     * 监听满足 canceledOnTouchOutside 与 cancelable 之后 调用的方法
     *
     * @return true 将直接销毁  false  屏蔽当前操作
     */
    protected boolean onDialogCancel() {
        return true;
    }



    public static class Configuration {
        private int gravity = Gravity.CENTER;
        private int dialogAnimation = R.style.alphastyle;
        private int dialogTheme = R.style.BaseDialogTheme;
        private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int marginTop = -1;
        //是否清理背景变暗
        private boolean isClearBehind;
        private boolean canceledOnTouchOutside = true;
        private boolean cancelable = true;

        public Configuration() {
        }

        public Configuration(int gravity, int width, int height, int marginTop, int dialogAnimation, int dialogTheme) {
            this.gravity = gravity;
            this.width = width;
            this.height = height;
            this.marginTop = marginTop;
            this.dialogAnimation = dialogAnimation;
            this.dialogTheme = dialogTheme;
        }

        public Configuration(int gravity, int width, int height, int marginTop, int dialogAnimation) {
            this.gravity = gravity;
            this.width = width;
            this.height = height;
            this.marginTop = marginTop;
            this.dialogAnimation = dialogAnimation;
        }

        public Configuration(int gravity, int width, int height) {
            this.gravity = gravity;
            this.width = width;
            this.height = height;
        }

        public Configuration(int gravity, int dialogAnimation) {
            this.gravity = gravity;
            this.dialogAnimation = dialogAnimation;
        }

        public Configuration setClearBehind(boolean clearBehind) {
            isClearBehind = clearBehind;
            return this;
        }

        public Configuration setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Configuration setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Configuration(int gravity) {
            this.gravity = gravity;
        }

        public int getWidth() {
            return width;
        }

        public Configuration setWidth(int width) {
            this.width = width;
            return this;
        }

        public int getHeight() {
            return height;
        }

        public Configuration setHeight(int height) {
            this.height = height;
            return this;
        }

        public int getGravity() {
            return gravity;
        }

        public Configuration setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public int getDialogAnimation() {
            return dialogAnimation;
        }

        public Configuration setDialogAnimation(int dialogAnimation) {
            this.dialogAnimation = dialogAnimation;
            return this;
        }

        public int getDialogTheme() {
            return dialogTheme;
        }

        public Configuration setDialogTheme(int dialogTheme) {
            this.dialogTheme = dialogTheme;
            return this;
        }

        /**
         * 全屏
         */
        public Configuration fullScreen() {
            this.height = WindowManager.LayoutParams.MATCH_PARENT;
            this.width = WindowManager.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 宽度全屏
         */
        public Configuration fullWidth() {
            this.width = WindowManager.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 高度全屏
         */
        public Configuration fullHight() {
            this.height = WindowManager.LayoutParams.MATCH_PARENT;
            return this;
        }


        public int getMarginTop() {
            return marginTop;
        }

        public Configuration setMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }
    }


    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroyView() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
//        if (immersionBar != null)
//            immersionBar.destroy();
        super.onDestroyView();
        if(unbinder!=null)
            unbinder.unbind();
    }
    public int getAndroiodScreenHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        return  height;
    }
}
