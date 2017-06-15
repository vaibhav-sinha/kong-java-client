package com.github.vaibhavsinha.kong.model.admin.certificate;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 14/06/17.
 */
@Data
public class Certificate {

    @SerializedName("id")
    private String id;
    @SerializedName("cert")
    private String cert;
    @SerializedName("key")
    private String key;
    @SerializedName("snis")
    private List<String> snis;
    @SerializedName("created_at")
    private Long createdAt;
}
