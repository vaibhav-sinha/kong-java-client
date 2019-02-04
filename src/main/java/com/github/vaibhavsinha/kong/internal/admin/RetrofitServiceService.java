package com.github.vaibhavsinha.kong.internal.admin;


import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import com.github.vaibhavsinha.kong.model.admin.service.ServiceList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by kgignatyev on 2019/02/04.
 */
public interface RetrofitServiceService {

    @POST("services/")
    Call<Service> createService(@Body Service request);

    @GET("services/{id}")
    Call<Service> getService(@Path("id") String nameOrId);

    @PATCH("services/{id}")
    Call<Service> updateService(@Path("id") String nameOrId, @Body Service request);

    @Deprecated
    @PUT("services/")
    Call<Service> createOrUpdateService(@Body Service request);

    @DELETE("services/{id}")
    Call<Void> deleteService(@Path("id") String nameOrId);

    @GET("services/")
    Call<ServiceList> listServices( @Query("offset") String offset);


    @GET("services/{id}/plugins")
    Call<PluginList> listServicePlugins(@Path("id") String nameOrId);
}
