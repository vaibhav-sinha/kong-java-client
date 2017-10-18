package com.github.vaibhavsinha.kong.api.plugin.authentication;

import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredential;
import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredentialList;

/**
 * Created by vaibhav on 16/06/17.
 *
 * Updated by dvilela on 17/10/17.
 */
public interface JwtService {
    JwtCredential addCredentials(String consumerIdOrUsername, JwtCredential request);
    void deleteCredentials(String consumerIdOrUsername, String id);
    JwtCredentialList listCredentials(String consumerIdOrUsername, Long size, String offset);
}
