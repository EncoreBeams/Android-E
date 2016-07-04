package cn.encore.framecommon.base.configuration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jude.swipbackhelper.SwipeBackHelper;

import cn.encore.framecommon.manager.AppActivityManager;

/**
 * Created by：Encore
 * Created Time：16/4/23 14:36
 *
 */
public class ConfigActivityDeleagetImpl extends ConfigBaseDeleagetImpl {

    private Activity mActivity;

    public ConfigActivityDeleagetImpl(Activity activity, ConfigSettingInterface configSettingInterface) {
        super(activity, configSettingInterface);
        mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //app 堆栈管理
        AppActivityManager.getAppManager().addActivity(mActivity);

        if(mEFrameConfiguration.isSwipeBack()) {
            //滑动退出管理
            SwipeBackHelper.onCreate(mActivity);
            //设置滑动退出默认参数
            SwipeBackHelper.getCurrentPage(mActivity)
                    .setSwipeBackEnable(true)
                    .setSwipeEdgePercent(0.05f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                    .setSwipeSensitivity(0.3f) //手势敏感度
                    .setSwipeRelateEnable(true) //是否开启联动滑动退出
                    .setSwipeRelateOffset(200);
        }

        //创建容器View
        View contentView = createContentView();
        mActivity.setContentView(contentView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mEFrameConfiguration.isSwipeBack()) {
            //swiper back
            SwipeBackHelper.onDestroy(mActivity);
        }
        //app manager
        //app 堆栈管理
        AppActivityManager.getAppManager().finishActivity(mActivity);

        mActivity = null;
    }
}
