package cn.encore.demo.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.encore.demo.bean.TabEntity;
import cn.encore.framecommon.log.jlog.JLog;
import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.presenter.EBasePresenter;

/**
 * Created by：Encore
 * Created Time：16/7/6 17:29
 */
public abstract class TabActivity<P extends EBasePresenter, M extends EBaseModel> extends BaseActivity<P, M> {
    private static final String TAG = "TabActivity";

    //获取 fragment 容器填充资源 id
    public abstract int getFragmentFillResId();

    //获取 tab layout
    public abstract CommonTabLayout getTabLayout();

    //获取 Tab 标题
    public abstract String[] getTabTitle();

    //获取Tab 图标资源
    public abstract int[] getTabIconSelectIds();

    //获取未选中图片资源
    public abstract int[] getTabIconUnSelectIds();

    //获取需要填充的 Fragment
    public abstract Class[] getFragments();

    //填充 fragment 容器
    private int mFillResId = -1;
    //TabLayout
    private CommonTabLayout mTabLayout;
    //标题
    private String[] mTitles = null;
    //未选中图片资源
    private int[] mIconUnselectIds = null;
    //选中图片资源
    private int[] mIconSelectIds = null;
    //fragments
    private Class[] mFragments = null;

    //最后选中的fragment
    private BaseFragment mLastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFillResId = getFragmentFillResId();
        if(mFillResId == -1){
            throw new RuntimeException("please input container res id");
        }

        mTabLayout = getTabLayout();
        if(mTabLayout == null){
            throw new RuntimeException("CommonTabLayout can not be null!");
        }

        mTitles = getTabTitle();
        if(mTabLayout == null){
            throw new RuntimeException("tab Title can not be null!");
        }
        mIconSelectIds = getTabIconSelectIds();
        if(mTabLayout == null){
            throw new RuntimeException("tab select icon can not be null!");
        }
        mIconUnselectIds = getTabIconUnSelectIds();
        if(mTabLayout == null){
            throw new RuntimeException("tab unSelect icon can not be null!");
        }

        mFragments = getFragments();
        if(mFragments == null){
            throw new RuntimeException("fragments can not be null!");
        }

        initTabs();
    }


    /**
     * 初始化 Tab
     */
    private void initTabs() {
        JLog.i("init Tab");
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        //设置数据
        mTabLayout.setTabData(mTabEntities);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                JLog.d(TAG, "onTabSelect ->" + position);
                //tab change
                onTabSelectChange(position);
                //切换 tab
                switchTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //切换到首个 Tab
        switchTab(getFristTabPostion());
    }

    //切换 Tab
    private void switchTab(int position){
        if (isFinishing() || mFragments == null) {
            return;
        }
        String Tag = String.valueOf(position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment newFragment = ((BaseFragment) getSupportFragmentManager().findFragmentByTag(Tag));


        Class clazz = mFragments[position];

        boolean isNew = false;
        if (newFragment == null) {
            isNew = true;
            try {
                //生成Fragment
                newFragment = (BaseFragment) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (newFragment != mLastFragment) {
            if (mLastFragment != null) {
                ft.detach(mLastFragment);
            }
            if (isNew) {
                ft.add(mFillResId, newFragment, Tag);
            } else {
                ft.attach(newFragment);
            }
            mLastFragment = newFragment;
        }

        ft.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
    }


    /**
     * 重写此方法可坚定 Tab 切换回调
     * @param position
     */
    protected void onTabSelectChange(int position){

    }

    /**
     * 获取首次显示 Tab 的 position
     * @return
     */
    protected int getFristTabPostion(){
        return 0;
    }
}
