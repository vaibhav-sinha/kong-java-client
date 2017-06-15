package com.github.vaibhavsinha.kong.model.admin.plugin;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class Plugin {
    @SerializedName("id")
    private String id;
    @SerializedName("api_id")
    private String apiId;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("name")
    private String name;
    @SerializedName("config")
    private Map<String, Object> config;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("created_at")
    private Long createdAt;
}
