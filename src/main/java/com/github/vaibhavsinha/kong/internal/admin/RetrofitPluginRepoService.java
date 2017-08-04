package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPluginRepoService {

    @GET("/plugins/enabled")
    Call<EnabledPlugins> retrieveEnabledPlugins();


    @GET("/plugins/schema/{plugin}")
    Call<Object> retrievePluginSchema(@Path("plugin") String pluginName);
}
