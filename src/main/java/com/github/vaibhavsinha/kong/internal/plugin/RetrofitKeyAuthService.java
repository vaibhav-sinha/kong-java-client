package com.github.vaibhavsinha.kong.internal.plugin;

import com.github.vaibhavsinha.kong.model.plugin.authentication.basic.BasicAuth;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vaibhav on 15/06/17.
 */
public interface RetrofitKeyAuthService {

    @POST("/consumers/{id}/key-auth")
    Call<BasicAuth> addCredentials(@Path("id") String consumerIdOrUsername, @Body KeyAuth request);
}
