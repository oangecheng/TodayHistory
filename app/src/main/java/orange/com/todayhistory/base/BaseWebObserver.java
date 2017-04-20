package orange.com.todayhistory.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Orange on 2017/4/15.
 */

public abstract class BaseWebObserver<T> implements Observer<BaseEntity<T>> {

    private final int SUCCESS_CODE = 0;
    private Context mContext;
    private Dialog mDialog;
    private Disposable mDisposable;

    public BaseWebObserver(Context context, Dialog dialog) {
        this.mContext = context;
        this.mDialog = dialog;

        //dialog取消事件
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                mDisposable.dispose();
            }
        });

    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(BaseEntity<T> result) {
        if (result.getErr_code() == SUCCESS_CODE) {
            T t = result.getResult();
            handleSuccess(t);
        } else {
            handleError(result.getErr_code(), result.getReason());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d("observer error", e.toString());
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        Toast.makeText(mContext, "网络异常，请重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {
        Log.d("observer complete", "onComplete");
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public abstract void handleSuccess(T t);

    void handleError(int code, String reason) {
        Toast.makeText(mContext, reason, Toast.LENGTH_SHORT).show();
    }
}
