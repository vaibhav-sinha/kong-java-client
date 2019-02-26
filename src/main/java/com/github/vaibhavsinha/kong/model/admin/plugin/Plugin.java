package com.github.vaibhavsinha.kong.model.admin.plugin;

import com.github.vaibhavsinha.kong.model.IdObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Created by vaibhav on 13/06/17.
 * <p>
 * You can add a plugin in four different ways:
 * For every API and Consumer. Don't set api_id and consumer_id.
 * For every API and a specific Consumer. Only set consumer_id.
 * For every Consumer and a specific API. Only set api_id.
 * For a specific Consumer and API. Set both api_id and consumer_id.
 * Note that not all plugins allow to specify consumer_id. Check the plugin documentation.
 */
@Data
public class Plugin {

    @SerializedName("id")
    private String id;

    @SerializedName("route")
    private IdObject route;

    @SerializedName("consumer")
    private IdObject consumer;

    @SerializedName("service")
    private IdObject service;

    @SerializedName("run_on")
    private String runOn = "first"; //second, all

    @SerializedName("name")
    private String name;    //must

    @SerializedName("config")
    private Object config;  //must

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("created_at")
    private Long createdAt;
}
