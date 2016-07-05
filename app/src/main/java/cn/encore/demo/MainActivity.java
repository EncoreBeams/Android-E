package cn.encore.demo;

import android.view.View;

import cn.encore.framecommon.base.activity.EFrameBaseActivity;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;
import cn.encore.framecommon.log.jlog.JLog;

public class MainActivity extends EFrameBaseActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(View contentView) {
        JLog.i("initViews");
    }

    @Override
    public EFrameConfiguration getConfiguration(EFrameConfiguration.Builder builder) {
        return builder
                .setSwipeBack(false)
                .build();
    }
}
