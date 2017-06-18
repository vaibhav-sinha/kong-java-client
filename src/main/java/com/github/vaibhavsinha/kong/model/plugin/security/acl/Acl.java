package com.github.vaibhavsinha.kong.model.plugin.security.acl;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vaibhav on 18/06/17.
 */
@Data
@NoArgsConstructor
public class Acl {
    @SerializedName("id")
    private String id;
    @SerializedName("group")
    private String group;
    @SerializedName("consumer_id")
    private String consumerId;
    @SerializedName("created_at")
    private Long createdAt;

    public Acl(String group) {
        this.group = group;
    }
}
