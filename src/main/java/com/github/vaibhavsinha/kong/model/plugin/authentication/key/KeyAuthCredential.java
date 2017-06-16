package com.github.vaibhavsinha.kong.model.plugin.authentication.key;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vaibhav on 15/06/17.
 */
@Data
@NoArgsConstructor
public class KeyAuthCredential {

    @SerializedName("id")
    private String id;
    @SerializedName("key")
    private String key;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("created_at")
    private Long createdAt;

    public KeyAuthCredential(String key) {
        this.key = key;
    }
}
