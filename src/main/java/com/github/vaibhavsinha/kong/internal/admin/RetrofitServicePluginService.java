package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fanhua on 2017-08-05.
 */
public interface RetrofitServicePluginService {


    @POST("/services/{service}/plugins")
    Call<Plugin> addPluginForService(@Path("service") String serviceId, @Body Plugin plugin);

    @GET("/services/{service}/plugins/{id}")
    Call<Plugin> getPluginForService(@Path("service") String serviceId, @Path("id") String pluginId);

    @PATCH("/services/{service}/plugins/{id}")
    Call<Plugin> updatePluginForService(@Path("service") String serviceId, @Path("id") String pluginNameOrId, @Body Plugin request);

    @PUT("/services/{service}/plugins")
    Call<Plugin> createOrUpdatePluginForService(@Path("service") String serviceId, @Body Plugin plugin);

    @DELETE("/services/{service}/plugins/{id}")
    Call<Void> deletePluginForService(@Path("service") String serviceId, @Path("id") String pluginNameOrId);

    @GET("/services/{service}/plugins/")
    Call<PluginList> listPluginsForService(@Path("service") String serviceId, @Query("id") String pluginNameOrId, @Query("service_id") String svcId,
			@Query("consumer_id") String consumerId, @Query("name") String name, @Query("size") Long size, @Query("offset") String offset);



}
