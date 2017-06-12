package com.github.vaibhavsinha.kong.impl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Proxy;

/**
 * Created by vaibhav on 13/06/17.
 */
class RetrofitServiceCreator {

    private Retrofit retrofit;

    RetrofitServiceCreator(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @SuppressWarnings("unchecked")
    <T> T create(Class<T> serviceInterface, Class<?> retrofitServiceInterface) {
        Object proxied = retrofit.create(retrofitServiceInterface);
        return (T) Proxy.newProxyInstance(
                RetrofitServiceCreator.class.getClassLoader(),
                new Class[] { serviceInterface },
                new RetrofitBodyExtractorInvocationHandler(proxied));
    }
}
