package com.github.vaibhavsinha.kong.internal.plugin.authentication;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by vaibhav on 15/06/17.
 */
public interface RetrofitOAuth2Service {

    @POST("/consumers/{id}/oauth2")
    Call<Application> createApplication(@Path("id") String consumerId, @Body Application request);

    @POST("/oauth2_tokens")
    Call<Token> createToken(@Body Token request);

    @GET("/oauth2")
    Call<ApplicationList> listApplications(@Query("client_id") String clientId);

    @Deprecated
    @POST("/oauth2/authorize")
    Call<Map<String, Object>> authorize(@Body Authorization request);


    @Deprecated
    @POST("/oauth2/token")
    Call<Token> refreshToken(@Body Refresh request);
}
