package com.github.vaibhavsinha.kong.internal.plugin;

import com.github.vaibhavsinha.kong.model.plugin.authentication.hmac.HmacAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vaibhav on 15/06/17.
 */
public interface RetrofitHmacAuthService {

    @POST("/consumers/{id}/hmac-auth")
    Call<HmacAuth> addCredentials(@Path("id") String consumerIdOrUsername, @Body HmacAuth request);
}
