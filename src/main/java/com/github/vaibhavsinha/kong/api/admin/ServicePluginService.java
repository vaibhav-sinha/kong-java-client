package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

/**
 *
 * You can add a plugin to a service
 * Note that not all plugins allow to specify consumer_id. Check the plugin documentation.
 */
public interface ServicePluginService {


    Plugin addPluginForService(String serviceId, Plugin plugin);

    Plugin getPluginForService(String serviceId, String pluginId);

    Plugin updatePluginForService(String serviceId, String pluginNameOrId, Plugin request);

    @Deprecated
    Plugin createOrUpdatePluginForService(String serviceId, Plugin plugin);

    void deletePluginForService(String serviceId, String pluginNameOrId);

    PluginList listPluginsForService(String serviceId, String pluginNameOrId, String svcId, String consumerId, String name, Long size, String offset);

}
