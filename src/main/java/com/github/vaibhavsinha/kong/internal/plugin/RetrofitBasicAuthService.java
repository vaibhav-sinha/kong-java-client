package com.github.vaibhavsinha.kong.internal.plugin;

import com.github.vaibhavsinha.kong.model.plugin.authentication.basic.BasicAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vaibhav on 15/06/17.
 */
public interface RetrofitBasicAuthService {

    @POST("/consumers/{id}/basic-auth")
    Call<BasicAuth> addCredentials(@Path("id") String consumerIdOrUsername, @Body BasicAuth request);
}
