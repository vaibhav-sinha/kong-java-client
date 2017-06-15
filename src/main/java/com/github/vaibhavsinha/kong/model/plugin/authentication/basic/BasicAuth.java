package com.github.vaibhavsinha.kong.model.plugin.authentication.basic;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 15/06/17.
 */
@Data
public class BasicAuth {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("created_at")
    private Long createdAt;

    public BasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
