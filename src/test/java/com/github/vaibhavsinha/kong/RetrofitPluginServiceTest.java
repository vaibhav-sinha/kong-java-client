package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitPluginServiceTest  extends BaseTest {

    private String PLUGIN_ID = "61e5b656-7b68-4761-aeae-d9c94a5782c8";
    private String PLUGIN_NAME = "jwt";

    private String API_NAME = "test";

    // -------------------------------------------------------------------------------

    @Test
    public void test01_CreatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setId(PLUGIN_ID);
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getPluginService().addPluginForApi(API_NAME, request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test02_GetPluginForApi() throws IOException {
        Plugin response = kongClient.getPluginService().getPluginForApi(API_NAME, PLUGIN_ID);
        print(response);
        Assert.assertEquals(PLUGIN_NAME, response.getName());
    }


    @Test(expected = KongClientException.class)
    public void test03_exceptionTestForApi() throws IOException {
        kongClient.getPluginService().getPluginForApi(API_NAME, "some-random-id");
    }


    @Test
    public void test04_UpdatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getPluginService().updatePluginForApi(API_NAME, PLUGIN_ID, request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    //    @Test
    public void test05_CreateOrUpdatePluginForApi() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);
        request.setId(PLUGIN_ID);
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getPluginService().createOrUpdatePluginForApi(API_NAME, request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test09_DeletePluginForApi() throws IOException {
        kongClient.getPluginService().deletePluginForApi(API_NAME, PLUGIN_ID);
    }

    @Test
    public void test10_ListPluginsForApi() throws IOException {
        List<Plugin> plugins = new ArrayList<>();
        PluginList pluginList = kongClient.getPluginService().listPluginsForApi(API_NAME, null, null, null, null, 1L, null);
        plugins.addAll(pluginList.getData());
        while (pluginList.getOffset() != null) {
            pluginList = kongClient.getPluginService().listPluginsForApi(API_NAME, null, null, null, null, 1L, pluginList.getOffset());
            plugins.addAll(pluginList.getData());
        }
        print(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }

    // -------------------------------------------------------------------------------

    @Test
    public void test11_CreatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setId(PLUGIN_ID);
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getPluginService().addPlugin(request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test12_GetPlugin() throws IOException {
        Plugin response = kongClient.getPluginService().getPlugin(PLUGIN_ID);
        print(response);
        Assert.assertEquals(PLUGIN_NAME, response.getName());
    }


    @Test(expected = KongClientException.class)
    public void test13_exceptionTest() throws IOException {
        kongClient.getPluginService().getPlugin("some-random-id");
    }


    @Test
    public void test14_UpdatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getPluginService().updatePlugin(PLUGIN_ID, request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

//    @Test
    public void test15_CreateOrUpdatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);
        request.setId(PLUGIN_ID);
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getPluginService().createOrUpdatePlugin(request);
        print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test19_DeletePlugin() throws IOException {
        kongClient.getPluginService().deletePlugin(PLUGIN_ID);
    }

    @Test
    public void test20_ListPlugins() throws IOException {
        List<Plugin> plugins = new ArrayList<>();
        PluginList pluginList = kongClient.getPluginService().listPlugins(null, null, null, null, 1L, null);
        plugins.addAll(pluginList.getData());
        while (pluginList.getOffset() != null) {
            pluginList = kongClient.getPluginService().listPlugins(null, null, null, null, 1L, pluginList.getOffset());
            plugins.addAll(pluginList.getData());
        }
        print(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }

}
