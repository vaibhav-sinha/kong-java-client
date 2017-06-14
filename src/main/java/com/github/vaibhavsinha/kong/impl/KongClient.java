package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.ApiService;
import com.github.vaibhavsinha.kong.api.CertificateService;
import com.github.vaibhavsinha.kong.api.ConsumerService;
import com.github.vaibhavsinha.kong.api.PluginService;
import com.github.vaibhavsinha.kong.internal.RetrofitApiService;
import com.github.vaibhavsinha.kong.internal.RetrofitCertificateService;
import com.github.vaibhavsinha.kong.internal.RetrofitConsumerService;
import com.github.vaibhavsinha.kong.internal.RetrofitPluginService;
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

    public KongClient(String adminUrl) {
        RetrofitServiceCreator creator = new RetrofitServiceCreator(adminUrl);
        consumerService = creator.create(ConsumerService.class, RetrofitConsumerService.class);
        apiService = creator.create(ApiService.class, RetrofitApiService.class);
        pluginService = creator.create(PluginService.class, RetrofitPluginService.class);
        certificateService = creator.create(CertificateService.class, RetrofitCertificateService.class);
    }

}
