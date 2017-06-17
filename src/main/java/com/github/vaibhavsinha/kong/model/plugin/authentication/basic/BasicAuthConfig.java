package com.github.vaibhavsinha.kong.model.plugin.authentication.basic;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class BasicAuthConfig {

    @SerializedName("hide_credentials")
    Boolean hideCredentials;
    @SerializedName("anonymous")
    String anonymous;
}
