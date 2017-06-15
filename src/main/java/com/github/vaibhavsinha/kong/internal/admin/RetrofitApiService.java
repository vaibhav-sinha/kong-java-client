package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitApiService {

    @POST("/apis/")
    Call<Api> createApi(@Body Api request);

    @GET("/apis/{id}")
    Call<Api> getApi(@Path("id") String nameOrId);

    @PATCH("/apis/{id}")
    Call<Api> updateApi(@Path("id") String nameOrId, @Body Api request);

    @PUT("/apis/")
    Call<Api> createOrUpdateApi(@Body Api request);

    @DELETE("/apis/{id}")
    Call<Api> deleteApi(@Path("id") String nameOrId);

    @GET("/apis/")
    Call<ApiList> listApis(@Query("id") String id, @Query("upstream_url") String upstreamUrl, @Query("name") String name, @Query("retries") Long retries,  @Query("size") Long size, @Query("offset") String offset);
}
