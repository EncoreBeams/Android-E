package cn.encore.rxjava;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by：Encore
 * Created Time：16/7/4 10:25
 */
public class RxBusManager {
    public RxBus mRxBus = null;
    private Map<String, Observable<?>> mObservables = null;
    private CompositeSubscription mCompositeSubscription = null;

    private synchronized void init(){
        if(mRxBus == null){
            mRxBus = RxBus.$();
        }

        if(mObservables == null){
            mObservables = new HashMap<>();// 管理观察源
        }

        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();// 管理订阅者者
        }
    }


    public void on(String eventName, Action1<Object> action1) {
        //init
        init();

        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                                           .subscribe(action1, new Action1<Throwable>() {
                                               @Override
                                               public void call(Throwable throwable) {
                                                   throwable.printStackTrace();
                                               }
                                           }));
    }

    public void add(Subscription m) {
        if(mCompositeSubscription == null){
            return;
        }
        mCompositeSubscription.add(m);
    }

    public void clear() {
        if(mCompositeSubscription == null || mObservables == null || mRxBus == null){
            return;
        }
        mCompositeSubscription.unsubscribe();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
    }

    /**
     * 发送消息
     * @param tag
     * @param content
     */
    public void post(Object tag, Object content) {
        if(mRxBus == null){
            init();
        }
        mRxBus.post(tag, content);
    }
}
