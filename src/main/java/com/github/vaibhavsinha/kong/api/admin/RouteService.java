package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import com.github.vaibhavsinha.kong.model.admin.route.Route;
import com.github.vaibhavsinha.kong.model.admin.route.RouteList;

public interface RouteService {

    Route createRoute(Route request);

    Route getRoute(String nameOrId);

    Route updateRoute(String nameOrId, Route request);

    void deleteRoute(String nameOrId);

    RouteList listRoutes( String offset);

    RouteList listServiceRoutes(String serviceNameOrId);

    RouteList listPluginRoutes(String pluginNameOrId);

}
