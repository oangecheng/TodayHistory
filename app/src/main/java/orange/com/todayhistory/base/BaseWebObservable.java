package orange.com.todayhistory.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 将一些重复的操作提出来，放到父类里每个接口都有重复代码
 * Created by Orange on 2017/2/27.
 */

public class BaseWebObservable {

    /**
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> observable(Observable<T> observable) {
        return observable.retry(1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
