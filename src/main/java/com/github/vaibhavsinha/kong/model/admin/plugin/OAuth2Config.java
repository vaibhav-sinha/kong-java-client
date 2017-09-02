package com.github.vaibhavsinha.kong.model.admin.plugin;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by fanhua on 2017-8-28
 */
@Data
public class OAuth2Config {

	@SerializedName("provision_key")
	private String provisionKey;

	@SerializedName("scopes")
	private List<String> scopes;

	@SerializedName("mandatory_scope")
	private boolean mandatoryScope;

	@SerializedName("token_expiration")
	private int tokenExpiration;

	@SerializedName("anonymous")
	private String anonymous;

	@SerializedName("accept_http_if_already_terminated")
	private boolean acceptHttpIfAlreadyTerminated;

	@SerializedName("enable_authorization_code")
	private boolean enableAuthorizationCode;

	@SerializedName("enable_implicit_grant")
	private boolean enableImplicitGrant;

	@SerializedName("enable_password_grant")
	private boolean enablePasswordGrant;

	@SerializedName("enable_client_credentials")
	private boolean enableClientCredentials;

	@SerializedName("global_credentials")
	private boolean globalCredentials;

	@SerializedName("hide_credentials")
	private boolean hideCredentials;

}
