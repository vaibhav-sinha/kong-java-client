package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.*;
import com.github.vaibhavsinha.kong.internal.*;
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

    public KongClient(String adminUrl) {
        RetrofitServiceCreator creator = new RetrofitServiceCreator(adminUrl);
        consumerService = creator.create(ConsumerService.class, RetrofitConsumerService.class);
        apiService = creator.create(ApiService.class, RetrofitApiService.class);
        pluginService = creator.create(PluginService.class, RetrofitPluginService.class);
        certificateService = creator.create(CertificateService.class, RetrofitCertificateService.class);
        sniService = creator.create(SniService.class, RetrofitSniService.class);
        upstreamService = creator.create(UpstreamService.class, RetrofitUpstreamService.class);
        targetService = creator.create(TargetService.class, RetrofitTargetService.class);
    }

}
