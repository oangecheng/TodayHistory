package orange.com.todayhistory.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import orange.com.todayhistory.base.BaseEntity;
import orange.com.todayhistory.history.bean.HistoryBean;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


/**
 * Created by Orange on 2017/2/25.
 */

public interface IApiService {

    /**
     * 查询历史上的今天接口
     *
     * @param map post的数据，键值对等
     * @return
     */

    @POST(ApiConstant.SEARCH_EVENT)
    Observable<BaseEntity<List<HistoryBean>>> searchHistory(@QueryMap Map<String, String> map);


}
