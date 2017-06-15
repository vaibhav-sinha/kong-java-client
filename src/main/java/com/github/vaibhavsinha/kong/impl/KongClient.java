package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.admin.*;
import com.github.vaibhavsinha.kong.api.plugin.BasicAuthService;
import com.github.vaibhavsinha.kong.api.plugin.HmacAuthService;
import com.github.vaibhavsinha.kong.api.plugin.KeyAuthService;
import com.github.vaibhavsinha.kong.impl.helper.RetrofitServiceCreator;
import com.github.vaibhavsinha.kong.impl.service.plugin.BasicAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.HmacAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.KeyAuthServiceImpl;
import com.github.vaibhavsinha.kong.internal.admin.*;
import lombok.Data;

/**
 * Created by vaibhav on 12/06/17.
 */
@Data
public class KongClient {

    private ConsumerService consumerService;
    private ApiService apiService;
    private PluginService pluginService;
    private CertificateService certificateService;
    private SniService sniService;
    private UpstreamService upstreamService;
    private TargetService targetService;

    private BasicAuthService basicAuthService;
    private KeyAuthService keyAuthService;
    private HmacAuthService hmacAuthService;

    public KongClient(String adminUrl) {
        RetrofitServiceCreator creator = new RetrofitServiceCreator(adminUrl);

        consumerService = creator.create(ConsumerService.class, RetrofitConsumerService.class);
        apiService = creator.create(ApiService.class, RetrofitApiService.class);
        pluginService = creator.create(PluginService.class, RetrofitPluginService.class);
        certificateService = creator.create(CertificateService.class, RetrofitCertificateService.class);
        sniService = creator.create(SniService.class, RetrofitSniService.class);
        upstreamService = creator.create(UpstreamService.class, RetrofitUpstreamService.class);
        targetService = creator.create(TargetService.class, RetrofitTargetService.class);

        basicAuthService = new BasicAuthServiceImpl(adminUrl);
        keyAuthService = new KeyAuthServiceImpl(adminUrl);
        hmacAuthService = new HmacAuthServiceImpl(adminUrl);
    }

}
