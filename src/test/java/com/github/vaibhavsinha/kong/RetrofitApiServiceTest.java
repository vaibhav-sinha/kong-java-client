package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
public class RetrofitApiServiceTest extends BaseTest {

    private String API_NAME_V1 = "V1_Api";
//    private String API_ID_V1 = "f813a66b-bac6-4951-831b-f04d53ae0bf0"; // not exist
    private String API_ID_V1 = "3a9fa5b9-5f99-4ab8-a949-d676becd30b3";

    private String API_NAME_V2 = "Test_V2_Api";
    private String API_NAME_V2_NEW = "Test_V2_Api_new";
    private String API_ID_V2 = "f813a66b-bac6-4952-831b-f04d53ae0bf0";

    private String API_UPSTREAM_URL = "http://httpbin.org";
    private String[] API_HOSTS = new String[] {"example.com"};
    private String[] API_URIS = new String[] {"/exampleV1"};


    // -----------------------------------------------------------------------

    @Test
    public void testCreateApi() throws IOException {
        Api request = new Api();
        request.setId(API_ID_V2);
        request.setName(API_NAME_V2);
        request.setUpstreamUrl(API_UPSTREAM_URL);
        request.setHosts(Arrays.asList(API_HOSTS));

        Api response = kongClient.getApiService().createApi(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testGetApi() throws IOException {
        Api response = kongClient.getApiService().getApi(API_NAME_V2);
        System.out.print(response);
        Assert.assertEquals(API_NAME_V2, response.getName());
    }

    @Test
    public void testListApis() throws IOException {
        List<Api> apis = new ArrayList<>();
        ApiList apiList = kongClient.getApiService().listApis(null, null, null, null, 1L, null);
        apis.addAll(apiList.getData());
        while (apiList.getOffset() != null) {
            apiList = kongClient.getApiService().listApis(null, null, null, null, 1L, apiList.getOffset());
            apis.addAll(apiList.getData());
        }
        System.out.println(apis);
        Assert.assertNotEquals(apis.size(), 0);
    }

    @Test(expected = KongClientException.class)
    public void exceptionTest() throws IOException {
        kongClient.getApiService().getApi("some-random-id");
    }

    @Test
    public void testUpdateApi() throws IOException {
        Api request = new Api();
        request.setName(API_NAME_V2_NEW);

        Api response = kongClient.getApiService().updateApi(API_ID_V2, request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    // -----------------------------------------------------------------------

    @Test
    public void testCreateOrUpdateApi() throws IOException {
        //Test by HTTP PUT method....
        // API name is required, otherwise you will get exception.
        // if API id is not set, then Kong will add API by name.
        // if API id is set, the Kong will update API by id and name.
        // if API id is set, but the API is actually not exist, then Kong will become odd. update API by id
        Api request = new Api();
        request.setName(API_NAME_V1);
        request.setId(API_ID_V1);
        request.setUpstreamUrl(API_UPSTREAM_URL);
        request.setUris(Arrays.asList(API_URIS));
        request.setCreatedAt(System.currentTimeMillis());
        Api response = kongClient.getApiService().createOrUpdateApi(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testDeleteApi() throws IOException {
        kongClient.getApiService().deleteApi(API_NAME_V1);
    }

}
