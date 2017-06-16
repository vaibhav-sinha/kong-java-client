package com.github.vaibhavsinha.kong.impl.service.plugin;

import com.github.vaibhavsinha.kong.api.plugin.authentication.BasicAuthService;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitBasicAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.basic.BasicAuthCredential;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhav on 15/06/17.
 */
public class BasicAuthServiceImpl implements BasicAuthService {

    private RetrofitBasicAuthService retrofitBasicAuthService;

    public BasicAuthServiceImpl(String adminUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(adminUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitBasicAuthService = retrofit.create(RetrofitBasicAuthService.class);
    }

    @Override
    public void addCredentials(String consumerIdOrUsername, String username, String password) {
        retrofitBasicAuthService.addCredentials(consumerIdOrUsername, new BasicAuthCredential(username, password));
    }
}
