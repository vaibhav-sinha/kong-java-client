package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vaibhav on 13/06/17.
 * <p>
 * You can add a plugin in four different ways:
 * For every API and Consumer. Don't set api_id and consumer_id.
 * For every API and a specific Consumer. Only set consumer_id.
 * For every Consumer and a specific API. Only set api_id.
 * For a specific Consumer and API. Set both api_id and consumer_id.
 * Note that not all plugins allow to specify consumer_id. Check the plugin documentation.
 */
public interface PluginService {


    Plugin addPlugin(Plugin request);

    Plugin getPlugin(String nameOrId);

    Plugin updatePlugin(String nameOrId, Plugin request);

    
    Plugin createOrUpdatePlugin( String id, Plugin request);

    void deletePlugin(String nameOrId);

    PluginList listServicePlugins(String id, Long size, String offset);
    
    PluginList listRoutePlugins(String id, Long size, String offset);
    
    PluginList listConsumerPlugins(String id, Long size, String offset);

    PluginList listPlugins(String id, String apiId, String consumerId, String name, Long size, String offset);
}
