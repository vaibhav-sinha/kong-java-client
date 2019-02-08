package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.admin.*;
import com.github.vaibhavsinha.kong.api.plugin.authentication.*;
import com.github.vaibhavsinha.kong.api.plugin.security.AclService;
import com.github.vaibhavsinha.kong.impl.helper.RetrofitServiceCreator;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.BasicAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.HmacAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.JwtAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.KeyAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.security.AclServiceImpl;
import com.github.vaibhavsinha.kong.internal.admin.*;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.*;
import com.github.vaibhavsinha.kong.internal.plugin.security.RetrofitAclService;
import lombok.Data;

/**
 * Created by kgignatyev on 2019/02/04.
 *
 */
@Data
public class KongClient {

    private ConsumerService consumerService;


    private ApiPluginService apiPluginService;
    private RouteService routeService;

    private PluginService pluginService;
    private PluginRepoService pluginRepoService;
    private CertificateService certificateService;
    private SniService sniService;
    private UpstreamService upstreamService;
    private TargetService targetService;

    private BasicAuthService basicAuthService;
    private KeyAuthService keyAuthService;
    private HmacAuthService hmacAuthService;
    private JwtService jwtService;
    private ServiceService serviceService;

    private OAuth2ProcessService oAuth2ProcessService;
    private OAuth2ManageService oAuth2ManageService;

    private AclService aclService;


    public KongClient(String adminUrl) {
        this(adminUrl, null, false);
    }


    public KongClient(String adminUrl, String proxyUrl, boolean needOAuth2Support) {

        if (adminUrl == null || adminUrl.isEmpty()) {
            throw new IllegalArgumentException("The adminUrl cannot be null or empty!");
        }

        if (needOAuth2Support) {
            if (proxyUrl == null || proxyUrl.isEmpty()) {
                throw new IllegalArgumentException("The proxyUrl cannot be null or empty!");
            }
            if( ! Boolean.parseBoolean( System.getenv().getOrDefault("KONG_DISABLE_HTTPS_CHECK", "false") )) {
                if (!proxyUrl.startsWith("https://")) {
                    throw new IllegalArgumentException("The proxyUrl must use https if you need OAuth2 support!");
                }
            }
        }


        RetrofitServiceCreator retrofitServiceCreatorForAdminUrl = new RetrofitServiceCreator(adminUrl);

        {
            consumerService = retrofitServiceCreatorForAdminUrl.create(ConsumerService.class, RetrofitConsumerService.class);


            apiPluginService = retrofitServiceCreatorForAdminUrl.create(ApiPluginService.class, RetrofitApiPluginService.class);
            routeService = retrofitServiceCreatorForAdminUrl.create(RouteService.class, RetrofitRouteService.class);

            pluginService = retrofitServiceCreatorForAdminUrl.create(PluginService.class, RetrofitPluginService.class);
            pluginRepoService = retrofitServiceCreatorForAdminUrl.create(PluginRepoService.class, RetrofitPluginRepoService.class);

            certificateService = retrofitServiceCreatorForAdminUrl.create(CertificateService.class, RetrofitCertificateService.class);
            sniService = retrofitServiceCreatorForAdminUrl.create(SniService.class, RetrofitSniService.class);
            upstreamService = retrofitServiceCreatorForAdminUrl.create(UpstreamService.class, RetrofitUpstreamService.class);
            targetService = retrofitServiceCreatorForAdminUrl.create(TargetService.class, RetrofitTargetService.class);
            serviceService = retrofitServiceCreatorForAdminUrl.create(ServiceService.class,RetrofitServiceService.class);
        }

        {
            basicAuthService = new BasicAuthServiceImpl(retrofitServiceCreatorForAdminUrl.createRetrofitService(RetrofitBasicAuthService.class));
            keyAuthService = new KeyAuthServiceImpl(retrofitServiceCreatorForAdminUrl.createRetrofitService(RetrofitKeyAuthService.class));
            hmacAuthService = new HmacAuthServiceImpl(retrofitServiceCreatorForAdminUrl.createRetrofitService(RetrofitHmacAuthService.class));
            jwtService = new JwtAuthServiceImpl(retrofitServiceCreatorForAdminUrl.createRetrofitService(RetrofitJwtService.class));

            aclService = new AclServiceImpl(retrofitServiceCreatorForAdminUrl.createRetrofitService(RetrofitAclService.class));
        }

        if(needOAuth2Support) {

            RetrofitServiceCreator retrofitServiceCreatorForProxyUrl = new RetrofitServiceCreator(proxyUrl);

            //oauth2 process is on proxy port
            oAuth2ProcessService = retrofitServiceCreatorForProxyUrl.create(OAuth2ProcessService.class, RetrofitOAuth2ProcessService.class);

            //oauth2 manage is on admin port
            oAuth2ManageService = retrofitServiceCreatorForAdminUrl.create(OAuth2ManageService.class, RetrofitOAuth2ManageService.class);
        }

    }


}
