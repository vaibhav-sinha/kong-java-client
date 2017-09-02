package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 *
 * Updated by fanhua on 2017-08-04.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitPluginServiceTest  extends BaseTest {


    private String PLUGIN_ID = "61e5b656-7b68-4761-aeae-d9c94a5782c8";
    private String PLUGIN_NAME = "jwt";

    private String API_ID = "3a9fa5b9-5f99-4ab8-a949-d676becd30b3";

    @Test
    public void test11_CreatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setId(PLUGIN_ID);
        request.setApiId(API_ID); // make sure you put the valid API_ID here, if you don't put API_ID, the the plugin will take effect on all APIs
        request.setName(PLUGIN_NAME);

        Plugin response = kongClient.getPluginService().addPlugin(request);
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void test12_GetPlugin() throws IOException {
        Plugin response = kongClient.getPluginService().getPlugin(PLUGIN_ID);
        printJson(response);
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
        printJson(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

//    @Test
    public void test15_CreateOrUpdatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName(PLUGIN_NAME);
        request.setId(PLUGIN_ID);
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getPluginService().createOrUpdatePlugin(request);
        printJson(response);
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
        printJson(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }

}
