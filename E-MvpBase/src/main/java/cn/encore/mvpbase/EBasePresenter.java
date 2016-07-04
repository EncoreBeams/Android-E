package cn.encore.mvpbase;

import android.content.Context;

/**
 * Created by：Encore
 * Created Time：16/7/4 09:56
 */
public abstract class EBasePresenter<E,T> {
    public Context context;
    //model
    public E mModel;
    //view
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
    }
}
