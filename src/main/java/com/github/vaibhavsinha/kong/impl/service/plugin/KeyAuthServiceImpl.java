package com.github.vaibhavsinha.kong.impl.service.plugin;

import com.github.vaibhavsinha.kong.api.plugin.KeyAuthService;
import com.github.vaibhavsinha.kong.internal.plugin.RetrofitKeyAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuth;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhav on 15/06/17.
 */
public class KeyAuthServiceImpl implements KeyAuthService {

    private RetrofitKeyAuthService retrofitKeyAuthService;

    public KeyAuthServiceImpl(String adminUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(adminUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitKeyAuthService = retrofit.create(RetrofitKeyAuthService.class);
    }

    @Override
    public void addCredentials(String consumerIdOrUsername, String key) {
        retrofitKeyAuthService.addCredentials(consumerIdOrUsername, new KeyAuth(key));
    }
}
