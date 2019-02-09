package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import com.github.vaibhavsinha.kong.model.admin.service.ServiceList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgignatyev on 2019/02/04.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitServiceServiceTest extends BaseTest {

//    private String SERVICE_NAME_V1 = "Test_V1_Service";
//    private String SERVICE_ID_V1 = "f813a66b-bac6-4951-831b-f04d53ae0bf0"; // not exist
//    private String SERVICE_ID_V1 = "3a9fa5b9-5f99-4ab8-a949-d676becd30b3";

    private String SERVICE_NAME_V2 = "Test_V2_Service";
    private String SERVICE_NAME_V2_NEW = "Test.V2.Service";
    String SERVICE_ID_V2 = "f813a66b-bac6-4952-831b-f04d53ae0bf0";

    private String SERVICE_PATH = "/httpbin";
    private String SERVICE_HOST = "httpbin.com";
    private String[] SERVICE_URIS = new String[] {"/v1/example", "/v2/example"};


    // -----------------------------------------------------------------------

    @Test
    public void test01_CreateService() throws IOException {
        Service request = new Service();
        request.setId(SERVICE_ID_V2);
        request.setName(SERVICE_NAME_V2);
        request.setPath(SERVICE_PATH);
//        request.setHosts(Arrays.asList(SERVICE_HOSTS));
        request.setHost(SERVICE_HOST);

        Service response = kongClient.getServiceService().createService(request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_GetService() throws IOException {
        Service response = kongClient.getServiceService().getService(SERVICE_NAME_V2);
        printJson(response);
        Assert.assertEquals(SERVICE_NAME_V2, response.getName());
    }

    @Test(expected = KongClientException.class)
    public void test03_exceptionTest() throws IOException {
        kongClient.getServiceService().getService("some.random.id");
    }

    @Test
    public void test04_testUpdateService() throws IOException {
        Service request = new Service();
        request.setName(SERVICE_NAME_V2_NEW);
        Service response = kongClient.getServiceService().updateService(SERVICE_ID_V2, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }


    @Test
    public void test10_ListServices() throws IOException {
        List<Service> services = new ArrayList<>();
        ServiceList servicesList = kongClient.getServiceService().listServices(null);
        services.addAll(servicesList.getData());
        while (servicesList.getOffset() != null) {
            servicesList = kongClient.getServiceService().listServices( servicesList.getOffset());
            services.addAll(servicesList.getData());
        }
        printJson(services);
        Assert.assertNotEquals(services.size(), 0);
    }

    @Test
    public void test11_testDeleteService() throws IOException {
        kongClient.getServiceService().deleteService(SERVICE_ID_V2);
    }

}
