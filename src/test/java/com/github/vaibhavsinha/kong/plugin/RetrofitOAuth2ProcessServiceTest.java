package com.github.vaibhavsinha.kong.plugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.AuthorizationRequest;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.GrantTokenRequest;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.Token;

import com.github.vaibhavsinha.kong.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitOAuth2ProcessServiceTest extends BaseTest {

    private static String API_URI = "v2/example"; // which actually "/v2/example", without the slash

    private static String API_PROVISION_KEY = "1f2b8d4baadb4b6f93c82b1599cad575";

    // ------------------------------------------------------------------------------------

    private static String appClientId = "dc80e4ebf33445faafad96c1b4701d48";
    private static String appClientSecret = "22435791881e40dc97fd05f28eb86488";

    private static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    // Authorization Code Process ---------------------------------------------------------------------------------------------------

    @Test
    public void test01_authorize_ac() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        AuthorizationRequest request = new AuthorizationRequest();
        request.setClientId(appClientId);
        request.setResponseType("code");
        request.setProvisionKey(API_PROVISION_KEY);     //must
        request.setAuthenticatedUserid("testUserId");   //must

        Map<String, Object> response = kongClient.getOAuth2ProcessService().authorize(API_URI, request);

        printJson(response);

        String redirectUrl = (String) response.get("redirect_uri");
        cache.put("code", redirectUrl.substring(redirectUrl.indexOf("?code=") + 6));
        printJson(cache);
    }

    @Test
    public void test02_grantToken_ac() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        String code = (String) cache.get("code");

        GrantTokenRequest request = new GrantTokenRequest();
        request.setClientId(appClientId);
        request.setClientSecret(appClientSecret);     //must
        request.setGrantType("authorization_code");
        request.setCode(code);

        Token token = kongClient.getOAuth2ProcessService().grantToken(API_URI, request);
        cache.put("token", token);
        printJson(cache);
    }

    @Test
    public void test03_grantToken_refresh() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        Token token = (Token) cache.get("token");

        GrantTokenRequest request = new GrantTokenRequest();
        request.setClientId(appClientId);
        request.setClientSecret(appClientSecret);
        request.setGrantType("refresh_token");
        request.setRefreshToken(token.getRefreshToken());

        Token newToken = kongClient.getOAuth2ProcessService().grantToken(API_URI, request);
        cache.put("token", token);
        printJson(cache);
    }

    // Implicit Grant Process ---------------------------------------------------------------------------------------------------

    @Test
    public void test11_authorize_ig() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        AuthorizationRequest request = new AuthorizationRequest();
        request.setClientId(appClientId);
        request.setResponseType("token");
        request.setProvisionKey(API_PROVISION_KEY);     //must
        request.setAuthenticatedUserid("testUserId");   //must

        Map<String, Object> response = kongClient.getOAuth2ProcessService().authorize(API_URI, request);

        printJson(response);

        String redirectUrl = (String) response.get("redirect_uri");
        //cache.put("code", redirectUrl.substring(redirectUrl.indexOf("#code=") + 6));
        //printJson(cache);

        printString(redirectUrl);
    }

    // Password Credentials Process ---------------------------------------------------------------------------------------------------

    @Test
    public void test21_grantToken_pc() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        GrantTokenRequest request = new GrantTokenRequest();
        request.setClientId(appClientId);
        request.setClientSecret(appClientSecret);     //must
        request.setGrantType("password");
        request.setProvisionKey(API_PROVISION_KEY);     //must
        request.setAuthenticatedUserid("testUserId");   //must

        Token token = kongClient.getOAuth2ProcessService().grantToken(API_URI, request);
        cache.put("token", token);
        printJson(cache);
    }

    // Client Credentials Process ---------------------------------------------------------------------------------------------------

    @Test
    public void test31_grantToken_cc() {

        printString(" ================ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ================ ");

        GrantTokenRequest request = new GrantTokenRequest();
        request.setClientId(appClientId);
        request.setClientSecret(appClientSecret);     //must
        request.setGrantType("client_credentials");
        request.setProvisionKey(API_PROVISION_KEY);     //must
        request.setAuthenticatedUserid("testUserId");   //must

        Token token = kongClient.getOAuth2ProcessService().grantToken(API_URI, request);
        cache.put("token", token);
        printJson(cache);
    }
}
