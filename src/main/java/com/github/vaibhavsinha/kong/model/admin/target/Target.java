package com.github.vaibhavsinha.kong.model.admin.target;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 14/06/17.
 */
@Data
public class Target {

    @SerializedName("id")
    private String id;
    @SerializedName("target")
    private String target;
    @SerializedName("weight")
    private Long weight;
    @SerializedName("upstream_id")
    private String upstreamId;
    @SerializedName("created_at")
    private Long createdAt;
}
