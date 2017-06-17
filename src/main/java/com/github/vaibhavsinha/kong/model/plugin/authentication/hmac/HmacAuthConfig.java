package com.github.vaibhavsinha.kong.model.plugin.authentication.hmac;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class HmacAuthConfig {

    @SerializedName("hide_credentials")
    Boolean hideCredentials;
    @SerializedName("anonymous")
    String anonymous;
    @SerializedName("clock_skew")
    Integer clockSkew;
}
