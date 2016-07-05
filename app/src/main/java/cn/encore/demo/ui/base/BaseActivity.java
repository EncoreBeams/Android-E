package cn.encore.demo.ui.base;

import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.presenter.EBasePresenter;
import cn.encore.mvpbase.view.EMvpBaseActivity;

/**
 * Created by：Encore
 * Created Time：16/7/5 09:40
 * baseActivity.
 */
public abstract class BaseActivity<P extends EBasePresenter, M extends EBaseModel> extends EMvpBaseActivity {
}
