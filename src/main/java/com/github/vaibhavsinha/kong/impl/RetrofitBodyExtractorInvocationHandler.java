package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import retrofit2.Call;
import retrofit2.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by vaibhav on 13/06/17.
 */
public class RetrofitBodyExtractorInvocationHandler implements InvocationHandler {

    private Object proxied;

    RetrofitBodyExtractorInvocationHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method method1 = proxied.getClass().getMethod(methodName, parameterTypes);
        Call call = (Call) method1.invoke(proxied, args);
        Response response = call.execute();
        if(!response.isSuccessful()) {
            throw new KongClientException(response.errorBody() != null ? response.errorBody().string() : String.valueOf(response.code()));
        }
        return response.body();
    }
}
