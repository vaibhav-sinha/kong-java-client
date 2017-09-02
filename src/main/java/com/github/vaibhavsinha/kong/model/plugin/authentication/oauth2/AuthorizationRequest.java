package com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 15/06/17.
 *
 * Updated by fanhua on 2017-08-07.
 */
@Data
public class AuthorizationRequest {

    @SerializedName("id")
    private String id;

    @SerializedName("client_id")
    private String clientId;

    /**
     * The response type, "code" or "token".
     * "code" for Authorization Code process, "token" for implicit grant process.
     * */
    @SerializedName("response_type")
    private String responseType;

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
}
