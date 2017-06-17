package com.github.vaibhavsinha.kong.model.plugin.authentication.key;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class KeyAuthConfig {

    @SerializedName("hide_credentials")
    Boolean hideCredentials;
    @SerializedName("anonymous")
    String anonymous;
    @SerializedName("key_names")
    List<String> keyNames;
    @SerializedName("key_in_body")
    Boolean keyInBody;
}
