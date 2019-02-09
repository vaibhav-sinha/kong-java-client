package com.github.vaibhavsinha.kong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.vaibhavsinha.kong.model.admin.plugin.OAuth2Config;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

/**
 * Created by fanhua on 2017-08-05.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitServiceSPluginServiceTest extends BaseTest {

    private String PLUGIN_ID = "3d3ee453-161c-449b-a468-42f06b7c0dc5";
    private String PLUGIN_NAME = "oauth2";
    private OAuth2Config OAUTH2_CONFIG = new OAuth2Config();
    private String OAUTH2_PROVISION_KEY = "1f2b8d4baadb4b6f93c82b1599cad575";


    private RetrofitServiceServiceTest serviceServiceTest = new RetrofitServiceServiceTest();
    private String SERVICE_ID = serviceServiceTest.SERVICE_ID_V2;

    // -------------------------------------------------------------------------------

    @Test
    public void test00_CreateService() throws IOException {
        serviceServiceTest.before();
        serviceServiceTest.test01_CreateService();
    }

    @Test
    public void test01_CreatePluginForService() throws IOException {
        Plugin request = new Plugin();
        request.setId(PLUGIN_ID);
        request.setName(PLUGIN_NAME);

        OAUTH2_CONFIG.setProvisionKey(OAUTH2_PROVISION_KEY);
        OAUTH2_CONFIG.setEnableAuthorizationCode(true);
        OAUTH2_CONFIG.setEnableImplicitGrant(true);
        OAUTH2_CONFIG.setEnablePasswordGrant(true);
        OAUTH2_CONFIG.setEnableClientCredentials(true);
        OAUTH2_CONFIG.setTokenExpiration(7200);
        request.setConfig(OAUTH2_CONFIG);

        Plugin response = kongClient.getServicePluginService().addPluginForService(SERVICE_ID, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_GetPluginForService() throws IOException {
        Plugin response = kongClient.getServicePluginService().getPluginForService(SERVICE_ID, PLUGIN_ID);
        printJson(response);
        Assert.assertEquals(PLUGIN_NAME, response.getName());
    }


    @Test(expected = KongClientException.class)
    public void test03_exceptionTestForService() throws IOException {
        kongClient.getServicePluginService().getPluginForService(SERVICE_ID, "some-random-id");
    }


    @Test
    public void test04_UpdatePluginForService() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getServicePluginService().updatePluginForService(SERVICE_ID, PLUGIN_ID, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    //    @Test
    public void test05_CreateOrUpdatePluginForService() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);
        request.setId(PLUGIN_ID);
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getServicePluginService().createOrUpdatePluginForService(SERVICE_ID, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test09_ListPluginsForService() throws IOException {
        List<Plugin> plugins = new ArrayList<>();
        PluginList pluginList = kongClient.getServicePluginService().listPluginsForService(SERVICE_ID, null, null, null, null, 1L, null);
        plugins.addAll(pluginList.getData());
        while (pluginList.getOffset() != null) {
            pluginList = kongClient.getServicePluginService().listPluginsForService(SERVICE_ID, null, null, null, null, 1L, pluginList.getOffset());
            plugins.addAll(pluginList.getData());
        }
        printJson(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }

    @Test
    public void test10_DeletePluginForService() throws IOException {
        kongClient.getServicePluginService().deletePluginForService(SERVICE_ID, PLUGIN_ID);
    }



    @Test
    public void test11_DeleteService() throws IOException {
        serviceServiceTest.before();
        serviceServiceTest.test11_testDeleteService();
    }

}
