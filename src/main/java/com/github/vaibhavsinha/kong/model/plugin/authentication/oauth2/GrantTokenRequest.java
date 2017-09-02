package com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by fanhua on 2017-08-07.
 */
@Data
public class GrantTokenRequest {

    @SerializedName("id")
    private String id;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("client_secret")
    private String clientSecret;

    /**
     * The grant type: "authorization_code", "password", "client_credentials", or "refresh_token".
     *   "authorization_code" for Authorization Code process, the response will contain both access_token & refresh_token.
     *   "password" for Password Credentials process, the response will contain both access_token & refresh_token.
     *   "client_credentials" for Client Credentials process, the response will contain access_token only.
     *   "refresh_token" for Refresh Token process, the response will contain both access_token & refresh_token.
     * */
    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("created_at")
    private Long createdAt;

    @SerializedName("provision_key")
    private String provisionKey;

    @SerializedName("scope")
    private String scope;

    @SerializedName("authenticated_userid")
    private String authenticatedUserid;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    /**
     * Only used in "Authorization Code" process
     * */
    @SerializedName("code")
    private String code;

    /**
     * Only used in "Refresh Token" process
     * */
    @SerializedName("refresh_token")
    private String refreshToken;

}
