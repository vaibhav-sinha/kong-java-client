package com.github.vaibhavsinha.kong.model.plugin.authentication.jwt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class JwtConfig {

    @SerializedName("key_claim_name")
    String keyClaimName;
    @SerializedName("anonymous")
    String anonymous;
    @SerializedName("claims_to_verify")
    List<String> claimsToVerify;
    @SerializedName("uri_param_names")
    List<String> uriParamNames;
    @SerializedName("secret_is_base64")
    Boolean secretIsBase64;
}
