package com.github.vaibhavsinha.kong;

import java.io.IOException;

import org.junit.Test;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;

/**
 * Created by fanhua on 2017-08-05.
 */
public class RetrofitPluginRepoServiceTest extends BaseTest {


    @Test
    public void retrieveEnabledPlugins() throws IOException {
        EnabledPlugins response = kongClient.getPluginRepoService().retrieveEnabledPlugins();
        printJson(response);
    }

    @Test
    public void retrievePluginSchema() throws IOException {
        Object response = kongClient.getPluginRepoService().retrievePluginSchema("oauth2");
        printJson(response);
    }


}
