package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;

public interface PluginRepoService {

	EnabledPlugins retrieveEnabledPlugins();

	Object retrievePluginSchema(String pluginName);
}
