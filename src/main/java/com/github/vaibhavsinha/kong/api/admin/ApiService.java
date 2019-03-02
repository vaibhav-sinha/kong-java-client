package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface ApiService {

    Api createApi(Api request);

    Api getApi(String nameOrId);

    Api updateApi(String nameOrId, Api request);

    /**
     * This interface has issue in Kong.
     * 1. Usually, when we put a API(which not exist in Kong) as a parameter, it should be added as a new one.
     * But it only take effect when API id is empty.
     * 2. When the API id input is not empty, it will consider to update the existing API. So, we can either
     * create a API (without id), or update a API (with id).
     * 3. When you try to create a API with name and id, it will become odd. Kong will give you the 200(ok) response,
     * but won't create the API that you wanted. (That's why we'd better not use this interface.
     *
     * */
    @Deprecated
    Api createOrUpdateApi(Api request);

    void deleteApi(String nameOrId);

    ApiList listApis(String id, String upstreamUrl, String name, Long retries, Long size, String offset);
    PluginList listApiPlugins(String id);
}
