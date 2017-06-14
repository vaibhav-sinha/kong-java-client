package com.github.vaibhavsinha.kong.internal;

import com.github.vaibhavsinha.kong.model.upstream.Upstream;
import com.github.vaibhavsinha.kong.model.upstream.UpstreamList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitUpstreamService {

    @POST("/upstreams/")
    Call<Upstream> createUpstream(@Body Upstream request);

    @GET("/upstreams/{id}")
    Call<Upstream> getUpstream(@Path("id") String nameOrId);

    @PATCH("/upstreams/{id}")
    Call<Upstream> updateUpstream(@Path("id") String nameOrId, @Body Upstream request);

    @PUT("/upstreams/")
    Call<Upstream> createOrUpdateUpstream(@Body Upstream request);

    @DELETE("/upstreams/{id}")
    Call<Upstream> deleteUpstream(@Path("id") String nameOrId);

    @GET("/upstreams/")
    Call<UpstreamList> listUpstreams(@Query("id") String id, @Query("slots") Integer slots, @Query("name") String name, @Query("size") Long size, @Query("offset") String offset);

}
