package com.github.vaibhavsinha.kong.model.plugin.trafficcontrol.ratelimiting;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class RateLimitingConfig {

    @SerializedName("second")
    Integer second;
    @SerializedName("minute")
    Integer minute;
    @SerializedName("hour")
    Integer hour;
    @SerializedName("day")
    Integer day;
    @SerializedName("month")
    Integer month;
    @SerializedName("year")
    Integer year;
    @SerializedName("limit_by")
    LimitBy limitBy;
    @SerializedName("policy")
    Policy policy;
    @SerializedName("fault_tolerant")
    Boolean faultTolerant;
    @SerializedName("redis_host")
    String redisHost;
    @SerializedName("redis_port")
    Integer redisPort;
    @SerializedName("redis_password")
    String redisPassword;
    @SerializedName("redis_timeout")
    Integer redisTimeout;
    @SerializedName("redis_database")
    Integer redisDatabase;

    public enum Policy {
        local, cluster, redis
    }

    public enum LimitBy {
        consumer, credential, ip
    }
}
