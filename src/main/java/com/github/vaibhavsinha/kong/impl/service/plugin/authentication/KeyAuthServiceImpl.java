package com.github.vaibhavsinha.kong.impl.service.plugin.authentication;

import com.github.vaibhavsinha.kong.api.plugin.authentication.KeyAuthService;
import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitKeyAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuthCredential;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by vaibhav on 15/06/17.
 *
 * Updated by fanhua on 2017-08-07.
 */
public class KeyAuthServiceImpl implements KeyAuthService {

    private RetrofitKeyAuthService retrofitKeyAuthService;

    public KeyAuthServiceImpl(RetrofitKeyAuthService retrofitKeyAuthService) {
        this.retrofitKeyAuthService = retrofitKeyAuthService;
    }

    @Override
    public void addCredentials(String consumerIdOrUsername, String key) {
        try {
            retrofitKeyAuthService.addCredentials(consumerIdOrUsername, new KeyAuthCredential(key)).execute();
        } catch (IOException e) {
            throw new KongClientException(e.getMessage());
        }
    }
}
