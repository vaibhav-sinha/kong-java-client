package com.github.vaibhavsinha.kong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class RetrofitApiPluginServiceTest extends BaseTest {

    private String PLUGIN_ID = "61e5b656-7b68-4761-aeae-d9c94a5782c9";
    private String PLUGIN_NAME = "jwt";

    private String API_NAME = "test";

    // -------------------------------------------------------------------------------

    @Test
    public void test01_CreatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setId(PLUGIN_ID);
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getApiPluginService().addPluginForApi(API_NAME, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_GetPluginForApi() throws IOException {
        Plugin response = kongClient.getApiPluginService().getPluginForApi(API_NAME, PLUGIN_ID);
        printJson(response);
        Assert.assertEquals(PLUGIN_NAME, response.getName());
    }


    @Test(expected = KongClientException.class)
    public void test03_exceptionTestForApi() throws IOException {
        kongClient.getApiPluginService().getPluginForApi(API_NAME, "some-random-id");
    }


    @Test
    public void test04_UpdatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getApiPluginService().updatePluginForApi(API_NAME, PLUGIN_ID, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    //    @Test
    public void test05_CreateOrUpdatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);
        request.setId(PLUGIN_ID);
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getApiPluginService().createOrUpdatePluginForApi(API_NAME, request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test09_DeletePluginForApi() throws IOException {
        kongClient.getApiPluginService().deletePluginForApi(API_NAME, PLUGIN_ID);
    }

    @Test
    public void test10_ListPluginsForApi() throws IOException {
        List<Plugin> plugins = new ArrayList<>();
        PluginList pluginList = kongClient.getApiPluginService().listPluginsForApi(API_NAME, null, null, null, null, 1L, null);
        plugins.addAll(pluginList.getData());
        while (pluginList.getOffset() != null) {
            pluginList = kongClient.getApiPluginService().listPluginsForApi(API_NAME, null, null, null, null, 1L, pluginList.getOffset());
            plugins.addAll(pluginList.getData());
        }
        printJson(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }


}
