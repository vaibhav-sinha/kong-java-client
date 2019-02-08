package com.github.vaibhavsinha.kong.internal.plugin.authentication;

import java.util.Map;

import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.Application;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.ApplicationList;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.AuthorizationRequest;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.GrantTokenRequest;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.Token;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.TokenList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fanhua on 2017-08-07.
 */
public interface RetrofitOAuth2ManageService {


    // App Management ---------------------------------------------------------------------------------------------------

    /**
     * Create OAuth2 application for the consumer
     * */
    @POST("/consumers/{consumer_id}/oauth2/")
    Call<Application> createConsumerApplication(@Path("consumer_id") String consumerId, @Body Application request);

    @GET("/consumers/{consumer_id}/oauth2/{id}")
    Call<Application> getConsumerApplication(@Path("consumer_id") String consumerId, @Path("id") String applicationId);

    @PATCH("/consumers/{consumer_id}/oauth2/{id}")
    Call<Application> updateConsumerApplication(@Path("consumer_id") String consumerId, @Path("id") String applicationId, @Body Application request);

    @DELETE("/consumers/{consumer_id}/oauth2/{id}")
    Call<Void> deleteConsumerApplication(@Path("consumer_id") String consumerId, @Path("id") String applicationId);


    /**
     * Get OAuth2 application list of the consumer, only consumerId accepted...
     * */
    @GET("/consumers/{consumer_id}/oauth2")
    Call<ApplicationList> listConsumerApplications(@Path("consumer_id") String consumerId);


    /**
     * List Client OAuth2 applications, by id / name / client_id / client_secret / consumer_id (and or)
     * */
    @GET("/oauth2")
    Call<ApplicationList> listClientApplications(@Query("id") String applicationId, @Query("name") String applicatonName, @Query("client_id") String clientId,
			@Query("client_secret") String clientSecret, @Query("consumer_id") String consumerId);


    // Token Management ---------------------------------------------------------------------------------------------------


    @POST("/oauth2_tokens")
    Call<Token> createToken(@Body Token request);


    @GET("/oauth2_tokens/{id}")
    Call<Token> getToken(@Path("id") String tokenId);

    @PATCH("/oauth2_tokens/{id}")
    Call<Token> updateToken(@Path("id") String tokenId, @Body Token request);

    @DELETE("/oauth2_tokens/{id}")
    Call<Token> deleteToken(@Path("id") String tokenId);


    @POST("/oauth2_tokens")
    Call<TokenList> listTokens();



}
