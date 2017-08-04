package com.github.vaibhavsinha.kong.model.admin.consumer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 12/06/17.
 */
@Data
public class Consumer {

    String id;

    @SerializedName("custom_id")
    String customId;

    @SerializedName("created_at")
    Long createdAt;

    String username;
}
