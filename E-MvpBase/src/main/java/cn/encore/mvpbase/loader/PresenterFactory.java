package cn.encore.mvpbase.loader;

import cn.encore.mvpbase.presenter.EBasePresenter;

/**
 * Created by：Encore
 * Created Time：16/7/4 17:31
 */
public interface PresenterFactory<T extends EBasePresenter> {
    T create();
}
