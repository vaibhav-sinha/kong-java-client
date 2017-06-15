package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.admin.api.Api;
import com.github.vaibhavsinha.kong.model.admin.api.ApiList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
public class RetrofitApiServiceTest {

    private KongClient kongClient;

    @Before
    public void before() {
        kongClient = new KongClient("http://localhost:8001");
    }

    @Test
    public void testCreateApi() throws IOException {
        Api request = new Api();
        request.setName("V2_Api");
        request.setUpstreamUrl("http://localhost:8080");
        request.setHosts(Collections.singletonList("example.com"));

        Api response = kongClient.getApiService().createApi(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testGetApi() throws IOException {
        Api response = kongClient.getApiService().getApi("V2_Api");
        System.out.print(response);
        Assert.assertEquals("V2_Api", response.getName());
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
        request.setName("V2_Api_new");

        Api response = kongClient.getApiService().updateApi("f813a66b-bac6-4959-831b-f04d53ae0bf0", request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testCreateOrUpdateApi() throws IOException {
        Api request = new Api();
        request.setName("V1_Api");
        request.setId("f813a66b-bac6-4959-831b-f04d53ae0bf0");
        request.setUpstreamUrl("http://localhost:8080");
        request.setCreatedAt(123456789L);

        Api response = kongClient.getApiService().createOrUpdateApi(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testDeleteApi() throws IOException {
        kongClient.getApiService().deleteApi("V1_Api");
    }

}
