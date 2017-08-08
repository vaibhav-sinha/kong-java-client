package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.target.Target;
import com.github.vaibhavsinha.kong.model.admin.target.TargetList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface RetrofitTargetService {

    @POST("upstreams/{id}/targets")
    Call<Target> createTarget(@Path("id") String upstreamNameOrId, @Body Target request);

    @DELETE("upstreams/{id}/targets/{target}")
    Call<Void> deleteTarget(@Path("id") String upstreamNameOrId, @Path("target") String target);

    @GET("upstreams/{id}/targets")
    Call<TargetList> listTargets(@Path("id") String upstreamNameOrId, @Query("id") String id, @Query("weight") Integer weight, @Query("target") String target, @Query("size") Long size, @Query("offset") String offset);

    @GET("upstreams/{id}/targets/active")
    Call<TargetList> listActiveTargets(@Path("id") String upstreamNameOrId);
}
