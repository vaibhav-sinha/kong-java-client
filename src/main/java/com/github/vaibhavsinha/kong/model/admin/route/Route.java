package com.github.vaibhavsinha.kong.model.admin.route;

import com.github.vaibhavsinha.kong.model.IdObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by kgignatyev at 2019/02/04
 * <p>
 * "id": "173a6cee-90d1-40a7-89cf-0329eca780a6",
 * "created_at": 1422386534,
 * "updated_at": 1422386534,
 * "name": "my-route",
 * "protocols": ["http", "https"],
 * "methods": ["GET", "POST"],
 * "hosts": ["example.com", "foo.test"],
 * "paths": ["/foo", "/bar"],
 * "regex_priority": 0,
 * "strip_path": true,
 * "preserve_host": false,
 * "service": {"id":"f5a9c0ca-bdbb-490f-8928-2ca95836239a"}
 */
@Data
public class Route {

    @SerializedName("id")
    private String id;

    @SerializedName("created_at")
    private Long createdAt;

    @SerializedName("updated_at")
    private Long updatedAt;

    @SerializedName("name")
    private String name;

    @SerializedName("protocols")
    private List<String> protocols;

    @SerializedName("methods")
    private List<String> methods;

    @SerializedName("hosts")
//	private Object hosts; //sometimes array, sometimes map
    private List<String> hosts = null;

    @SerializedName("paths")
    private List<String> paths;

    @SerializedName("regex_priority")
    private Integer regex_priority = 0;

    @SerializedName("strip_path")
    private Boolean stripPath = true;


    @SerializedName("preserve_host")
    private Boolean preserveHost = false;


    @SerializedName("service")
    private IdObject service;

    /**
     * semi-optional	A list of SNIs that match this Route when using stream routing.
     * When using tcp or tls protocols, at least one of snis, sources, or destinations must be set.
     */
    @SerializedName("snis")
    private List<String> snis;

    /**
     * semi-optional
     */
    @SerializedName("sources")
    private List<String> sources;


    /**
     * semi-optional
     */
    @SerializedName("destinations")
    private List<String> destinations;

}
