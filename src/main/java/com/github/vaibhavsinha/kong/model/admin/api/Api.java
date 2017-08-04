package com.github.vaibhavsinha.kong.model.admin.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class Api {

	@SerializedName("created_at")
	private Long createdAt;

	@SerializedName("hosts")
	private Object hosts; //sometimes array, sometimes map
//	private List<String> hosts = null;

	@SerializedName("http_if_terminated")
	private Boolean httpIfTerminated;

	@SerializedName("https_only")
	private Boolean httpsOnly;

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("preserve_host")
	private Boolean preserveHost;

	@SerializedName("retries")
	private Integer retries;

	@SerializedName("strip_uri")
	private Boolean stripUri;

	@SerializedName("upstream_connect_timeout")
	private Integer upstreamConnectTimeout;

	@SerializedName("upstream_read_timeout")
	private Integer upstreamReadTimeout;

	@SerializedName("upstream_send_timeout")
	private Integer upstreamSendTimeout;

	@SerializedName("upstream_url")
	private String upstreamUrl;

	@SerializedName("uris")
	private List<String> uris;

	@SerializedName("methods")
    private List<String> methods;
}
