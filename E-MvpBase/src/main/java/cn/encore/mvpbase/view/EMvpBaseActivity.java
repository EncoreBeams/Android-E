package cn.encore.mvpbase.view;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import cn.encore.framecommon.base.activity.EFrameBaseActivity;
import cn.encore.framecommon.base.configuration.EFrameConfiguration;
import cn.encore.framecommon.log.jlog.JLog;
import cn.encore.mvpbase.loader.PresenterFactory;
import cn.encore.mvpbase.loader.PresenterLoader;
import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.mvputils.TUtil;
import cn.encore.mvpbase.presenter.EBasePresenter;

/**
 * mvpBase
 * Created by：Encore
 * Created Time：16/7/4 18:00
 */
public abstract class EMvpBaseActivity<P extends EBasePresenter, M extends EBaseModel> extends EFrameBaseActivity implements LoaderManager.LoaderCallbacks<EBasePresenter> {

    private static final String TAG = "EMvpBaseActivity";

    private static final int LOADER_ID = 101;
    //p 层实例
    private EBasePresenter mPresenter;
    //model 层实例
    private EBaseModel mModel;
    //is init views
    private boolean mIsInitViews = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置不调 initViews 回调,交给当前类处理
        getConfigDelegate().setEFrameConfiguration(getConfiguration(new EFrameConfiguration.Builder().setCallInitViews(false)));

        super.onCreate(savedInstanceState);
        // 初始化代码
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        JLog.i(TAG, "onActivityStart");

        if (mModel == null) {
            //实例化 Model
            mModel = getModelInstance();
        }

        if (mPresenter != null)
            mPresenter.setVM(EMvpBaseActivity.this, mModel);

        //回调初始化
        if(!mIsInitViews) {
            initViews(getContentView());
            mIsInitViews = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    //通过 loader 处理 activity 销毁,旋转屏幕等情况, presenter 复用
    @Override
    public Loader<EBasePresenter> onCreateLoader(int id, Bundle args) {
        JLog.i(TAG, "onCreateLoader()");
        return new PresenterLoader<>(this, new PresenterFactory<EBasePresenter>() {
            @Override
            public EBasePresenter create() {
                JLog.i(TAG, "create()");
                //实例化 Presenter
                mPresenter = getPresenterInstance(); //泛型首个参数
                JLog.i(TAG, "" + mPresenter);
                return mPresenter;
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<EBasePresenter> loader, EBasePresenter data) {
        JLog.i(TAG, "on Load finished " + data);
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<EBasePresenter> loader) {
        JLog.i(TAG, "onLoaderReset " + mPresenter);
        mPresenter = null;
    }

    //获取 Presenter 实例
    private P getPresenterInstance() {
        return TUtil.getT(EMvpBaseActivity.this, 0); //泛型首个参数
    }

    //获取 Model 实例
    private M getModelInstance() {
        return TUtil.getT(EMvpBaseActivity.this, 1); //泛型第二个参数
    }

    protected P getPresenter() {
        if (mPresenter == null) return null;
        return (P) mPresenter;
    }

    protected M getModel() {
        if (mModel == null) return null;
        return (M) mModel;
    }
}
