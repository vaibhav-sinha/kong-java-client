package com.github.vaibhavsinha.kong.model.sni;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class Sni {
    @SerializedName("ssl_certificate_id")
    private String sslCertificateId;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private Long createdAt;
}
