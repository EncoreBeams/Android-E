package cn.encore.framecommon.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.encore.framecommon.base.configuration.ConfigDelegate;
import cn.encore.framecommon.base.configuration.ConfigFragmentDeleagetImpl;
import cn.encore.framecommon.base.configuration.ConfigSettingInterface;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;

/**
 * Created by：Encore
 * Created Time：16/7/2 09:43
 */
public abstract class EFrameBaseFragment extends Fragment implements ConfigSettingInterface {
    //配置管理
    private ConfigFragmentDeleagetImpl mDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getConfigDelegate().onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getConfigDelegate().onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * @return The {@link ConfigDelegate} being used by this Activity.
     */
    @NonNull
    public ConfigFragmentDeleagetImpl getConfigDelegate() {
        if (mDelegate == null) {
            mDelegate = (ConfigFragmentDeleagetImpl) ConfigDelegate.create(this, this);
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
     * @return
     */
    @Override
    public EFrameConfiguration getConfiguration(EFrameConfiguration.Builder builder) {
        return builder.build();
    }


    /**
     * onKeyDown
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();
        getConfigDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //onPause
        getConfigDelegate().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        //onStop
        getConfigDelegate().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //onDestory
        getConfigDelegate().onDestroy();
        mDelegate = null;
    }

}
