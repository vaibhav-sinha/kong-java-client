package com.github.vaibhavsinha.kong.impl.service.plugin.authentication;

import com.github.vaibhavsinha.kong.api.plugin.authentication.KeyAuthService;
import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitKeyAuthService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuthCredential;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuthCredentialList;

import java.io.IOException;

/**
 * Created by vaibhav on 15/06/17.
 *
 * Updated by fanhua on 2017-08-07.
 *
 * Updated by dvilela on 17/10/17.
 */
public class KeyAuthServiceImpl implements KeyAuthService {

    private RetrofitKeyAuthService retrofitKeyAuthService;

    public KeyAuthServiceImpl(RetrofitKeyAuthService retrofitKeyAuthService) {
        this.retrofitKeyAuthService = retrofitKeyAuthService;
    }

    @Override
    public KeyAuthCredential addCredentials(String consumerIdOrUsername, String key) {
        try {
            return retrofitKeyAuthService.addCredentials(consumerIdOrUsername, new KeyAuthCredential(key)).execute()
                    .body();
        } catch (IOException e) {
            throw new KongClientException(e.getMessage());
        }
    }

    @Override
    public KeyAuthCredentialList listCredentials(String consumerIdOrUsername, Long size, String offset) {
        try {
            return retrofitKeyAuthService.listCredentials(consumerIdOrUsername, size, offset).execute().body();
        } catch (IOException e) {
            throw new KongClientException(e.getMessage());
        }
    }

    @Override
    public void deleteCredential(String consumerIdOrUsername, String id) {
        try {
            retrofitKeyAuthService.deleteCredential(consumerIdOrUsername, id).execute();
        } catch (IOException e) {
            throw new KongClientException(e.getMessage());
        }
    }
}
