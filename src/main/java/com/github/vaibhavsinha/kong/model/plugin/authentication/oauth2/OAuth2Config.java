package com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class OAuth2Config {

    @SerializedName("scopes")
    List<String> scopes;

    @SerializedName("mandatory_scope")
    Boolean mandatoryScope;

    @SerializedName("token_expiration")
    Integer tokenExpiration;

    @SerializedName("enable_authorization_code")
    Boolean enableAuthorizationCode;

    @SerializedName("enable_client_credentials")
    Boolean enableClientCredentials;

    @SerializedName("enable_implicit_grant")
    Boolean enableImplicitGrant;

    @SerializedName("enable_password_grant")
    Boolean enablePasswordGrant;

    @SerializedName("hide_credentials")
    Boolean hideCredentials;

    @SerializedName("global_credentials")
    Boolean globalCredentials;

    @SerializedName("accept_http_if_already_terminated")
    Boolean acceptHttpIfAlreadyTerminated;

    @SerializedName("anonymous")
    String anonymous;

}
