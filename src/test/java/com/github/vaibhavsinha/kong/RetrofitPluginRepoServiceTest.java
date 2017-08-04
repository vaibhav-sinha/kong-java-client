package com.github.vaibhavsinha.kong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.vaibhavsinha.kong.model.admin.plugin.EnabledPlugins;
import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;

public class RetrofitPluginRepoServiceTest extends BaseTest {


    @Test
    public void retrieveEnabledPlugins() throws IOException {
        EnabledPlugins response = kongClient.getPluginRepoService().retrieveEnabledPlugins();
        print(response);
    }

    @Test
    public void retrievePluginSchema() throws IOException {
        Object response = kongClient.getPluginRepoService().retrievePluginSchema("oauth2");
        print(response);
    }


}
