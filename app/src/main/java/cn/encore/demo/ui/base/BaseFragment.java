package cn.encore.demo.ui.base;

import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.presenter.EBasePresenter;
import cn.encore.mvpbase.view.EMvpBaseFragment;

/**
 * Created by：Encore
 * Created Time：16/7/6 16:16
 */
public abstract class BaseFragment<P extends EBasePresenter, M extends EBaseModel> extends EMvpBaseFragment<P,M> {
}
