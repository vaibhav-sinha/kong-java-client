package com.github.vaibhavsinha.kong.model.plugin.authentication.jwt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vaibhav on 16/06/17.
 *
 * Updated by dvilela on 17/10/17.
 */
@Data
@NoArgsConstructor
public class JwtCredential {

    @SerializedName("rsa_public_key")
    private String rsaPublicKey;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("id")
    private String id;
    @SerializedName("created_at")
    private Long createdAt;
    @SerializedName("key")
    private String key;
    @SerializedName("algorithm")
    private String algorithm;
    @SerializedName("secret")
    private String secret;
}
