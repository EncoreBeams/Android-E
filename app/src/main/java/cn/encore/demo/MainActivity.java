package cn.encore.demo;

import android.view.View;

import cn.encore.framecommon.base.activity.EFrameBaseActivity;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;

public class MainActivity extends EFrameBaseActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(View contentView) {
    }

    @Override
    public EFrameConfiguration getConfiguration() {
        return new EFrameConfiguration.Builder()
                .setSwipeBack(false) //首页设置禁止滑动退出
                .build();
    }
}
