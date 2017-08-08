package com.github.vaibhavsinha.kong.impl.helper;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.github.vaibhavsinha.kong.utils.HttpsUtil;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Proxy;

/**
 * Created by vaibhav on 13/06/17.
 *
 * Update by fanhua on 2017-07-28.
 */
public class RetrofitServiceCreator {

    private Retrofit retrofit;


    // -------------------------------------------------------------------

	public RetrofitServiceCreator(String baseUrl) {

		retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(initOkHttpClient(baseUrl.toLowerCase().startsWith("https"))) // support https
				.addConverterFactory(CustomGsonConverterFactory.create()) // replace GsonConverterFactory
				// .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // add rxJava1 support
				// .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // add rxJava2 support
				.build();

	}

    // -------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> serviceInterface, Class<?> retrofitServiceInterface) {
        Object proxied = retrofit.create(retrofitServiceInterface);
        return (T) Proxy.newProxyInstance(
                RetrofitServiceCreator.class.getClassLoader(),
                new Class[] { serviceInterface },
                new RetrofitBodyExtractorInvocationHandler(proxied));
    }

    public <T> T createRetrofitService(Class<T> retrofitServiceInterface) {
        return retrofit.create(retrofitServiceInterface);
    }

    // -------------------------------------------------------------------

    private OkHttpClient initOkHttpClient(boolean supportHttps) {

        if(supportHttps) {
            HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(null, null, null);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
            return okHttpClient;
        }

        return new OkHttpClient.Builder().build();
    }
}
