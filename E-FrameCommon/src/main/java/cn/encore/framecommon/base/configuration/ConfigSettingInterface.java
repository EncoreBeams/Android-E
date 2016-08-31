package cn.encore.framecommon.base.configuration;

import android.view.View;
import android.widget.LinearLayout;

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

    /**
     * 在添加容器之前, 子类可以重写此方法,拿到parentView ,可以添加想要的View, 例如ToolBar 等, 会在内容View之上
     * @param parentView
     */
    public void onAddContainerViewBefore(LinearLayout parentView);

    /**
     * 在添加内容View之前,可以拿到内容View的实体对象, 可以对该对象进行一些操作 , 返回一个新的内容View
     * @param contentView
     * @return new ContentView
     */
    public View onAddContentViewBefor(View contentView);

}
