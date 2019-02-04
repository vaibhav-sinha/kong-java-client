package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import com.github.vaibhavsinha.kong.model.admin.service.ServiceList;

/**
 * Created by kgignatyev on 02/04/19.
 */
public interface ServiceService {

    Service createService(Service request);

    Service getService(String nameOrId);

    Service updateService(String nameOrId, Service request);

//    Service createOrUpdateService(Service request);

    void deleteService(String nameOrId);

    ServiceList listServices( String offset);
    PluginList listServicePlugins(String id);

}
