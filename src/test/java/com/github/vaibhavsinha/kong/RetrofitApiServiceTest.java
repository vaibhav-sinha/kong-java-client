package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 *
 * Updated by fanhua on 2017-08-04.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitApiServiceTest extends BaseTest {

    private String API_NAME_V1 = "Test_V1_Api";
//    private String API_ID_V1 = "f813a66b-bac6-4951-831b-f04d53ae0bf0"; // not exist
    private String API_ID_V1 = "3a9fa5b9-5f99-4ab8-a949-d676becd30b3";

    private String API_NAME_V2 = "Test_V2_Api";
    private String API_NAME_V2_NEW = "Test.V2.Api";
    private String API_ID_V2 = "f813a66b-bac6-4952-831b-f04d53ae0bf0";

    private String API_UPSTREAM_URL = "http://httpbin.org";
    private String[] API_HOSTS = new String[] {"example.com"};
    private String[] API_URIS = new String[] {"/v1/example", "/v2/example"};


    // -----------------------------------------------------------------------

    @Test
    public void test01_CreateApi() throws IOException {
        Api request = new Api();
        request.setId(API_ID_V2);
        request.setName(API_NAME_V2);
        request.setUpstreamUrl(API_UPSTREAM_URL);
//        request.setHosts(Arrays.asList(API_HOSTS));
        request.setUris(Arrays.asList(API_URIS));

        Api response = kongClient.getApiService().createApi(request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_GetApi() throws IOException {
        Api response = kongClient.getApiService().getApi(API_NAME_V2);
        printJson(response);
        Assert.assertEquals(API_NAME_V2, response.getName());
    }

    @Test(expected = KongClientException.class)
    public void test03_exceptionTest() throws IOException {
        kongClient.getApiService().getApi("some.random.id");
    }

    @Test
    public void test04_testUpdateApi() throws IOException {
        Api request = new Api();
        request.setName(API_NAME_V2_NEW);

        Api response = kongClient.getApiService().updateApi(API_ID_V2, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }


    //    @Test
    public void test05_CreateOrUpdateApi() throws IOException {
        //Test by HTTP PUT method....
        // API name is required, otherwise you will get exception.
        // if API id is not set, then Kong will add API by name.
        // if API id is set, the Kong will update API by id and name.
        // if API id is set, but the API is actually not exist, then Kong will response 200(OK), but the API won't be created!!!
        Api request = new Api();
        request.setName(API_NAME_V1);
        //        request.setId(API_ID_V1);
        request.setUpstreamUrl(API_UPSTREAM_URL);
        request.setUris(Arrays.asList(API_URIS));
        request.setCreatedAt(System.currentTimeMillis());
        Api response = kongClient.getApiService().createOrUpdateApi(request);
        Assert.assertNotNull(response);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test09_testDeleteApi() throws IOException {
        kongClient.getApiService().deleteApi(API_ID_V2);
    }


    @Test
    public void test10_ListApis() throws IOException {
        List<Api> apis = new ArrayList<>();
        ApiList apiList = kongClient.getApiService().listApis(null, null, null, null, 1L, null);
        apis.addAll(apiList.getData());
        while (apiList.getOffset() != null) {
            apiList = kongClient.getApiService().listApis(null, null, null, null, 1L, apiList.getOffset());
            apis.addAll(apiList.getData());
        }
        printJson(apis);
        Assert.assertNotEquals(apis.size(), 0);
    }



}
