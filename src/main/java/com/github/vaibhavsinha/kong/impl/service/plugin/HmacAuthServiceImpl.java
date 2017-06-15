package com.github.vaibhavsinha.kong.impl.service.plugin;

import com.github.vaibhavsinha.kong.api.plugin.HmacAuthService;
import com.github.vaibhavsinha.kong.internal.plugin.RetrofitHmacAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.hmac.HmacAuth;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaibhav on 15/06/17.
 */
public class HmacAuthServiceImpl implements HmacAuthService {

    private RetrofitHmacAuthService retrofitHmacAuthService;

    public HmacAuthServiceImpl(String adminUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(adminUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitHmacAuthService = retrofit.create(RetrofitHmacAuthService.class);
    }

    @Override
    public void addCredentials(String consumerIdOrUsername, String username, String secret) {
        retrofitHmacAuthService.addCredentials(consumerIdOrUsername, new HmacAuth(username, secret));
    }
}
