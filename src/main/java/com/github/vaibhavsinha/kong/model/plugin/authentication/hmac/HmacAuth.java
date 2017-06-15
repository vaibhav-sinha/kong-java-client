package com.github.vaibhavsinha.kong.model.plugin.authentication.hmac;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 15/06/17.
 */
@Data
public class HmacAuth {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("secret")
    private String secret;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("created_at")
    private Long createdAt;

    public HmacAuth(String username, String secret) {
        this.username = username;
        this.secret = secret;
    }
}
