package cn.encore.mvpbase.presenter;

import android.content.Context;

/**
 * Created by：Encore
 * Created Time：16/7/4 09:56
 */
public abstract class EBasePresenter<M, V>  {
    public Context context;
    //model
    public M mModel;
    //view
    public V mView;

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    /**
     * onPresenterStart
     */
    public abstract void onStart();

    /**
     * onDestory
     */
    public void onDestroy() {

    }

    /**
     * 获取 View
     * @return
     */
    public V getView(){
        return mView;
    }

    /**
     * 获取 Model
     * @return
     */
    public M getModel(){
        return mModel;
    }
}
