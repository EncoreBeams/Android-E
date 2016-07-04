package cn.encore.mvpbase.view;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import cn.encore.framecommon.base.activity.EFrameBaseActivity;
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

    private static final int LOADER_ID = 101;
    //p 层实例
    private EBasePresenter mPresenter;
    //model 层实例
    private EBaseModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 初始化代码
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            if(this instanceof EBaseView) {
                mPresenter.setVM(this, mModel);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
    //通过 loader 处理 activity 销毁,旋转屏幕等情况, presenter 复用
    @Override
    public Loader<EBasePresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, new PresenterFactory<EBasePresenter>() {
            @Override
            public EBasePresenter create() {
                if(mPresenter == null){
                    mPresenter = TUtil.getT(this, 0);
                }
                if(mModel == null) {
                    mModel = TUtil.getT(this, 1);
                }
                return mPresenter;
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<EBasePresenter> loader, EBasePresenter data) {
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<EBasePresenter> loader) {
        mPresenter = null;
    }


}
