package cn.encore.framecommon.base.configuration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 方便以后扩展
 * Created by：Encore
 * Created Time：16/4/23 14:36
 *
 */
public class ConfigFragmentDeleagetImpl extends ConfigBaseDeleagetImpl {

    public ConfigFragmentDeleagetImpl(Fragment fragment, ConfigSettingInterface configSettingInterface) {
        super(fragment, configSettingInterface);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createContentView(null);
    }
}
