package cn.encore.demo.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;

import butterknife.Bind;
import cn.encore.demo.R;
import cn.encore.demo.ui.base.TabActivity;
import cn.encore.demo.ui.home.HomeFragment;
import cn.encore.demo.ui.list.ListFragment;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;

public class MainActivity extends TabActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    @Bind(R.id.flContainer)
    FrameLayout mFlContainer;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewReady(View contentView) {
    }

    @Override
    public int getFragmentFillResId() {
        return mFlContainer.getId();
    }

    @Override
    public CommonTabLayout getTabLayout() {
        return mTabLayout;
    }

    @Override
    public String[] getTabTitle() {
        String[] mTitles = {"首页", "列表示例", "待定", "待定"};
        return mTitles;
    }

    @Override
    public int[] getTabIconSelectIds() {
        int[] mIconSelectIds = {
                R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
                R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
        return mIconSelectIds;
    }

    @Override
    public int[] getTabIconUnSelectIds() {
        int[] mIconUnselectIds = {
                R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
                R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
        return mIconUnselectIds;
    }

    @Override
    public Class[] getFragments() {
        Class[] clazzs = new Class[]{
                HomeFragment.class,
                ListFragment.class,
                HomeFragment.class,
                HomeFragment.class};
        return clazzs;
    }


    @Override
    public EFrameConfiguration getConfiguration(EFrameConfiguration.Builder builder) {
        return builder
                .setSwipeBack(false)
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
