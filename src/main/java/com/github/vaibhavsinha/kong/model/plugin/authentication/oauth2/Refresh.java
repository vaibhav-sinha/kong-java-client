package com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 15/06/17.
 */
@Data
public class Refresh {
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("grant_type")
    private String grantType = "refresh_token";
}
