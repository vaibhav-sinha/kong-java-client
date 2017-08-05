package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.consumer.Consumer;
import com.github.vaibhavsinha.kong.model.admin.consumer.ConsumerList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitConsumerService {

    @POST("consumers/")
    Call<Consumer> createConsumer(@Body Consumer request);

    @GET("consumers/{id}")
    Call<Consumer> getConsumer(@Path("id") String usernameOrId);

    @PATCH("consumers/{id}")
    Call<Consumer> updateConsumer(@Path("id") String usernameOrId, @Body Consumer request);

    @PUT("consumers/")
    Call<Consumer> createOrUpdateConsumer(@Body Consumer request);

    @DELETE("consumers/{id}")
    Call<Void> deleteConsumer(@Path("id") String usernameOrId);

    @GET("consumers/")
    Call<ConsumerList> listConsumers(@Query("id") String id, @Query("custom_id") String customId, @Query("username") String username, @Query("size") Long size, @Query("offset") String offset);
}
