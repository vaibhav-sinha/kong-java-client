package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.model.IdObject;
import com.github.vaibhavsinha.kong.model.admin.route.Route;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

/**
 * Created by kgignatyev on 2019/02/04.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitSRouteServiceTest extends BaseTest {


    private String SERVICE_NAME= "httpbin.Service";
    private String SERVICE_ID = "f813a66b-bac6-4952-831b-f04d53ae0b88";

    private String SERVICE_PATH = "/httpbin";
    private String SERVICE_HOST = "httpbin.com";

    public static final String ROUTE_ID = "f111a66b-bac6-4952-831b-f04d53ae0b11";
    public static final String ROUTE_NAME = "httpbin";

    @Test
    public void test01_CreateService() throws IOException {
        Service request = new Service();
        request.setId(SERVICE_ID);
        request.setName(SERVICE_NAME);
        request.setPath(SERVICE_PATH);
        request.setHost(SERVICE_HOST);
        Service response = kongClient.getServiceService().createService(request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_CreateRoute() throws IOException {

        Route request = new Route();
        IdObject serviceId = new IdObject();
        serviceId.setId( SERVICE_ID );

        request.setService(serviceId);
        request.setId( ROUTE_ID);
        request.setName(ROUTE_NAME);
        request.setMethods(BaseTest.asList("GET","POST"));
        request.setHosts(BaseTest.asList("somehost.net"));
        request.setPaths(BaseTest.asList("/httpbin"));

        Route response = kongClient.getRouteService().createRoute(request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

//    @Test
//    public void test11_testDeleteServiceAndRoutes() throws IOException {
//        kongClient.getRouteService().deleteRoute(ROUTE_ID);
//        kongClient.getServiceService().deleteService(SERVICE_ID);
//    }

}
