package orange.com.todayhistory.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import orange.com.todayhistory.base.BaseEntity;
import orange.com.todayhistory.base.BaseWebObservable;
import orange.com.todayhistory.base.RetrofitManager;
import orange.com.todayhistory.history.bean.HistoryBean;
import orange.com.todayhistory.utils.WebUtils;


/**
 * Created by Orange on 2017/2/25.
 */

public class ApiLoader extends BaseWebObservable {


    private IApiService service;
    private ProgressDialog mDialog;
    private boolean showLoading = true;
    private Context mContext;


    public ApiLoader(Context context, ProgressDialog dialog) {
        this.mContext = context;
        this.mDialog = dialog;
        service = RetrofitManager.getInstance().create(IApiService.class);
    }

    /**
     * 获取历史上的今天
     *
     * @param date 日期，输入格式为  month/day
     * @return List<HistoryBean></>
     */


    public Observable<BaseEntity<List<HistoryBean>>> getHistory(String date) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiConstant.HISTORY_KEY, ApiConstant.APP_ID);
        map.put(ApiConstant.HISTORY_DATE, date);

        showProgressDialog();
        return observable(service.searchHistory(map));
    }


    private void showProgressDialog() {
        if (WebUtils.isNetworkConnected(mContext)) {
            if (showLoading) {
                if (mDialog != null && !mDialog.isShowing()) {
                    mDialog.show();
                }
            } else {
                Toast.makeText(mContext, "网络连接异常，请检查网络", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }


}
