package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.api.Api;
import com.github.vaibhavsinha.kong.model.api.ApiList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface ApiService {
    Api createApi(Api request);
    Api getApi(String nameOrId);
    Api updateApi(String nameOrId, Api request);
    Api createOrUpdateApi(Api request);
    Api deleteApi(String usernameOrId);
    ApiList listApis(String id, String upstreamUrl, String name, Long retries, Long size, String offset);
}
