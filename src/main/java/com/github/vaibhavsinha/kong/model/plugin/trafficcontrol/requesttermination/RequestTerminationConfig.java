package com.github.vaibhavsinha.kong.model.plugin.trafficcontrol.requesttermination;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 18/06/17.
 */
@Data
public class RequestTerminationConfig {
    @SerializedName("status_code")
    Integer statusCode;
    @SerializedName("message")
    String message;
    @SerializedName("body")
    String body;
    @SerializedName("content_type")
    String contentType;
}
