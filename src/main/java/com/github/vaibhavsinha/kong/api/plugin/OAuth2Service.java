package com.github.vaibhavsinha.kong.api.plugin;

import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.*;

import java.util.Map;

/**
 * Created by vaibhav on 15/06/17.
 */
public interface OAuth2Service {
    Application createApplication(String consumerId, Application request);
    Token createToken(Token request);
    ApplicationList listApplications(String clientId);
    Map<String, Object> authorize(Authorization request);
    Token refreshToken(Refresh request);
}
