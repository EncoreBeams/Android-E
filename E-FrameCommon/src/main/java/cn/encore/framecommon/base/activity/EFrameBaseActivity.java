package cn.encore.framecommon.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import cn.encore.framecommon.base.configuration.ConfigActivityDeleagetImpl;
import cn.encore.framecommon.base.configuration.ConfigDelegate;
import cn.encore.framecommon.base.configuration.ConfigSettingInterface;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;

/**
 * Created by：Encore
 * Created Time：16/7/2 09:42
 */
public abstract class EFrameBaseActivity extends AppCompatActivity implements ConfigSettingInterface {
    //配置管理
    private ConfigActivityDeleagetImpl mDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onCreate
        getConfigDelegate().onCreate(savedInstanceState);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getConfigDelegate().onPostCreate(savedInstanceState);

    }

    /**
     * @return The {@link ConfigDelegate} being used by this Activity.
     */
    @NonNull
    public ConfigActivityDeleagetImpl getConfigDelegate() {
        if (mDelegate == null) {
            mDelegate = (ConfigActivityDeleagetImpl) ConfigDelegate.create(this, this);
        }
        return mDelegate;
    }


    /**
     * 重写此方法设置想要显示的View
     */
    @Override
    public View getContentView() {
        return null;
    }

    /**
     * 重写此方法可以进行对Frame层初始化设置
     *
     * @return return new EFrameConfiguration.Builder()
     * .setUseButterKnife(true)
     * .build();
     */
    @Override
    public EFrameConfiguration getConfiguration(EFrameConfiguration.Builder builder) {
        return builder.build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //onResume
        getConfigDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //onPause
        getConfigDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //onStop
        getConfigDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //onDestory
        getConfigDelegate().onDestroy();
        mDelegate = null;
    }

    /**
     * 在添加内容View之前,可以拿到内容View的实体对象, 可以对该对象进行一些操作 , 返回一个新的内容View
     * @param contentView
     * @return new ContentView
     */
    @Override
    public View onAddContentViewBefor(View contentView) {
        return contentView;
    }

    /**
     * 在添加容器之前, 子类可以重写此方法,拿到parentView ,可以添加想要的View, 例如ToolBar 等, 会在内容View之上
     * @param parentView
     */
    @Override
    public void onAddContainerViewBefore(LinearLayout parentView) {

    }

}
