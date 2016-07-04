package cn.encore.mvpbase.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import cn.encore.framecommon.base.fragment.EFrameBaseFragment;
import cn.encore.mvpbase.loader.PresenterFactory;
import cn.encore.mvpbase.loader.PresenterLoader;
import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.mvputils.TUtil;
import cn.encore.mvpbase.presenter.EBasePresenter;

/**
 * Created by：Encore
 * Created Time：16/7/4 18:38
 */
public abstract class EMvpBaseFragment <P extends EBasePresenter, M extends EBaseModel> extends EFrameBaseFragment implements LoaderManager.LoaderCallbacks<EBasePresenter> {

    private static int LOADER_ID = 102;
    //p 层实例
    private EBasePresenter mPresenter;
    //model 层实例
    private EBaseModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // 初始化代码
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
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
        return new PresenterLoader<>(getActivity(), new PresenterFactory<EBasePresenter>() {
            @Override
            public EBasePresenter create() {
                mPresenter = TUtil.getT(this, 0);
                mModel = TUtil.getT(this, 1);
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
