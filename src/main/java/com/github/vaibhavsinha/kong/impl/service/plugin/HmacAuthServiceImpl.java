package com.github.vaibhavsinha.kong.impl.service.plugin;

import com.github.vaibhavsinha.kong.api.plugin.authentication.HmacAuthService;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitHmacAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.hmac.HmacAuthCredential;
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
        retrofitHmacAuthService.addCredentials(consumerIdOrUsername, new HmacAuthCredential(username, secret));
    }
}
