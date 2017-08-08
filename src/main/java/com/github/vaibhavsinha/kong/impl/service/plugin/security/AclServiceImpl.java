package com.github.vaibhavsinha.kong.impl.service.plugin.security;

import com.github.vaibhavsinha.kong.api.plugin.security.AclService;
import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.internal.plugin.security.RetrofitAclService;
import com.github.vaibhavsinha.kong.model.plugin.security.acl.Acl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * Created by vaibhav on 18/06/17.
 *
 * Updated by fanhua on 2017-08-07.
 */
public class AclServiceImpl implements AclService {

    private RetrofitAclService retrofitAclService;

    public AclServiceImpl(RetrofitAclService retrofitAclService) {
        this.retrofitAclService = retrofitAclService;
    }
    @Override
    public void associateConsumer(String usernameOrId, String group) {
        try {
            retrofitAclService.associateConsumer(usernameOrId, new Acl(group)).execute();
        } catch (IOException e) {
            throw new KongClientException(e.getMessage());
        }
    }
}
