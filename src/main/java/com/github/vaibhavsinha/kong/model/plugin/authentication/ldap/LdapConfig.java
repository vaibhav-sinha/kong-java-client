package com.github.vaibhavsinha.kong.model.plugin.authentication.ldap;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 17/06/17.
 */
@Data
public class LdapConfig {

    @SerializedName("hide_credentials")
    Boolean hideCredentials;
    @SerializedName("anonymous")
    String anonymous;
    @SerializedName("ldap_host")
    String ldapHost;
    @SerializedName("ldap_port")
    Integer ldapPort;
    @SerializedName("start_tls")
    Boolean startTls;
    @SerializedName("base_dn")
    String baseDn;
    @SerializedName("verify_ldap_host")
    Boolean verifyLdapHost;
    @SerializedName("attribute")
    String attribute;
    @SerializedName("cache_ttl")
    Integer cacheTtl;
    @SerializedName("timeout")
    Integer timeout;
    @SerializedName("keepalive")
    Integer keepalive;
}
