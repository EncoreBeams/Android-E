package cn.encore.demo.ui.base;

import android.os.Bundle;

import cn.encore.mvpbase.model.EBaseModel;
import cn.encore.mvpbase.presenter.EBasePresenter;
import cn.encore.mvpbase.view.EMvpBaseActivity;

/**
 * BaseActivity 继承MvpBase,如果子类传递了泛型,即可采用 Mvp 模式,否则可以不用,当普通 Activity 模式使用
 * <p/>
 * Created by：Encore
 * Created Time：16/7/5 09:40
 */
public abstract class BaseActivity<P extends EBasePresenter, M extends EBaseModel> extends EMvpBaseActivity<P, M> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
