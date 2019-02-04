package com.github.vaibhavsinha.kong.model.admin.service;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by kgignatyev on 2019/02/04.
 */

@Data
public class Service {


    @SerializedName("id")
    private String id;
    
    @SerializedName("created_at")
    private Long createdAt;
    @SerializedName("updated_at")
    private Long updatedAt;        
            
    @SerializedName("name")
    private String name;
    @SerializedName("retries")
    private Integer retries;
    @SerializedName("protocol")
    private String protocol = "http";
    @SerializedName("host")
    private String host;
    @SerializedName("port")
    private Integer port = 80;
    @SerializedName("path")
    private String path = "/";
    @SerializedName("connect_timeout")
    private Integer connectTimeout = 60000;
    @SerializedName("write_timeout")
    private Integer writeTimeout = 60000;
    @SerializedName("read_timeout")
    private Integer readTimeout = 60000;
}
