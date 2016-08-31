package cn.encore.mvpbase.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.encore.framecommon.base.fragment.EFrameBaseFragment;
import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.mvputils.TUtil;
import cn.encore.mvpbase.presenter.EBasePresenter;

/**
 * Created by：Encore
 * Created Time：16/7/4 18:38
 */
public abstract class EMvpBaseFragment<P extends EBasePresenter, M extends EBaseModel> extends EFrameBaseFragment {
    private static final String TAG = "EMvpBaseFragment";
    private static int LOADER_ID = 102;
    //p 层实例
    private EBasePresenter mPresenter;
    //model 层实例
    private EBaseModel mModel;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (mModel == null) {
            mModel = getModelInstance();
        }
        if (mPresenter == null) {
            mPresenter = getPresenterInstance();
        }
        if (mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }


    //获取 Presenter 实例
    private P getPresenterInstance() {
        return TUtil.getT(EMvpBaseFragment.this, 0); //泛型首个参数
    }

    //获取 Model 实例
    private M getModelInstance() {

        return TUtil.getT(EMvpBaseFragment.this, 1); //泛型第二个参数
    }

    protected P getPresenter() {
        if (mPresenter == null) return null;
        return (P) mPresenter;
    }

    protected M getModel() {
        if (mModel == null) return null;
        return (M) mModel;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
