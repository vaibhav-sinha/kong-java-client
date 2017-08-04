package com.github.vaibhavsinha.kong.impl.helper;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by vaibhav on 13/06/17.
 */
@Slf4j
public class RetrofitBodyExtractorInvocationHandler implements InvocationHandler {

    private Object proxied;

    public RetrofitBodyExtractorInvocationHandler(Object proxied) {
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
        if(response.body() == null) {
            log.debug("Response with empty body: " + response);
        }
        return response.body();
    }
}
