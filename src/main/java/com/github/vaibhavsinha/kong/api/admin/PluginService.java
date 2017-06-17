package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface PluginService {
    Plugin addPlugin(Plugin request);
    Plugin getPlugin(String nameOrId);
    Plugin updatePlugin(String nameOrId, Plugin request);
    Plugin createOrUpdatePlugin(Plugin request);
    Plugin deletePlugin(String nameOrId);
    PluginList listPlugins(String id, String apiId, String consumerId, String name, Long size, String offset);
    EnabledPlugins listEnabledPlugins();
}
