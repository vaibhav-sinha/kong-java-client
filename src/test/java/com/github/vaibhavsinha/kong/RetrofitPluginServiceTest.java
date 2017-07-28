package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
public class RetrofitPluginServiceTest  extends BaseTest {


    @Test
    public void testCreatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName("jwt");

        Plugin response = kongClient.getPluginService().addPlugin(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testGetPlugin() throws IOException {
        Plugin response = kongClient.getPluginService().getPlugin("fb8a3a99-5896-448e-a365-221032ce6307");
        System.out.print(response);
        Assert.assertEquals("jwt", response.getName());
    }

    @Test
    public void testListPlugins() throws IOException {
        List<Plugin> plugins = new ArrayList<>();
        PluginList pluginList = kongClient.getPluginService().listPlugins(null, null, null, null, 1L, null);
        plugins.addAll(pluginList.getData());
        while (pluginList.getOffset() != null) {
            pluginList = kongClient.getPluginService().listPlugins(null, null, null, null, 1L, pluginList.getOffset());
            plugins.addAll(pluginList.getData());
        }
        System.out.println(plugins);
        Assert.assertNotEquals(plugins.size(), 0);
    }

    @Test(expected = KongClientException.class)
    public void exceptionTest() throws IOException {
        kongClient.getPluginService().getPlugin("some-random-id");
    }

    @Test
    public void testUpdatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName("jwt");

        Plugin response = kongClient.getPluginService().updatePlugin("fb8a3a99-5896-448e-a365-221032ce6307", request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testCreateOrUpdatePlugin() throws IOException {
        Plugin request = new Plugin();
        request.setName("jwt");
        request.setId("fb8a3a99-5896-448e-a365-221032ce6307");
        request.setCreatedAt(new Date().getTime());

        Plugin response = kongClient.getPluginService().createOrUpdatePlugin(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testDeletePlugin() throws IOException {
        kongClient.getPluginService().deletePlugin("fb8a3a99-5896-448e-a365-221032ce6307");
    }

    @Test
    public void testListEnabledPlugins() throws IOException {
        EnabledPlugins response = kongClient.getPluginService().listEnabledPlugins();
        System.out.print(response);
        Assert.assertNotEquals(response.getEnabledPlugins().size(), 0);
    }

}
