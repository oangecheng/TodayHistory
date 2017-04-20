package orange.com.todayhistory.base;


import com.dou361.retrofit2.converter.fastjson.FastJsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import orange.com.todayhistory.api.ApiConstant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Orange on 2017/2/27.
 */

public class RetrofitManager {
    /**
     * 链接超时时间为5s
     **/
    private static final int DEFAULT_TIME_OUT = 5;

    private static final int READ_TIME_OUT = 10;

    private Retrofit retrofit;

    private RetrofitManager() {

        /**创建OkHttpClient**/
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder b = chain.request().newBuilder();
                        b.addHeader(ApiConstant.HISTORY_KEY, ApiConstant.APP_ID);
                        return chain.proceed(b.build());
                    }
                })
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();


        /**创建retrofit**/
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(client)
                .build();
    }

    /**
     * 获取RetrofitManager
     *
     * @return 返回 RetrofitManager对象
     */
    public static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    private static class SingletonHolder {

        private static final RetrofitManager INSTANCE = new RetrofitManager();

    }
}
