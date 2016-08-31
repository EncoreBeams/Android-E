package cn.encore.framecommon.base.configuration;

import android.view.View;

/**
 * Created by：Encore
 * Created Time：16/4/23 10:35
 *
 */
public interface ConfigSettingInterface {


    /**
     * 获取子类显示View资源Id, 如果想直接显示一个View,则返回 -1
     *
     * @return layout res id
     */
    public int getContentViewResId();

    /**
     * 重写此方法设置想要显示的View
     * @return
     */
    public View getContentView();

    /**
     * 初始化视图,在Activity onCreate
     *
     * @param contentView
     */
    public void onViewReady(View contentView);

    /**
     * 获取设置管理器
     * @return
     */
    public EFrameConfiguration getConfiguration(EFrameConfiguration.Builder builder);
}
