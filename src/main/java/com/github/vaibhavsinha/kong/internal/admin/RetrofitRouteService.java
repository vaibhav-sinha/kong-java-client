package com.github.vaibhavsinha.kong.internal.admin;


import com.github.vaibhavsinha.kong.model.admin.route.Route;
import com.github.vaibhavsinha.kong.model.admin.route.RouteList;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitRouteService {


    @POST("routes/")
    Call<Route> createRoute(@Body Route request);

    @GET("routes/{id}")
    Call<Route> getRoute(@Path("id") String nameOrId);

    @PATCH("routes/{id}")
    Call<Route> updateRoute(@Path("id") String nameOrId, @Body Route request);


    @DELETE("routes/{id}")
    Call<Void> deleteRoute(@Path("id") String nameOrId);

    @GET("routes/")
    Call<RouteList> listRoutes( @Query("offset") String offset);


    @GET("services/{id}/routes")
    Call<RouteList> listServiceRoutes(@Path("id") String nameOrId);

    @GET("plugins/{id}/routes")
    Call<RouteList> listPluginRoutes(@Path("id") String nameOrId);
}
