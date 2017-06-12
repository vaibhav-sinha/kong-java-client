package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.ConsumerService;
import com.github.vaibhavsinha.kong.internal.RetrofitConsumerService;
import lombok.Data;

/**
 * Created by vaibhav on 12/06/17.
 */
@Data
public class KongClient {

    private ConsumerService consumerService;

    public KongClient(String adminUrl) {
        RetrofitServiceCreator creator = new RetrofitServiceCreator(adminUrl);
        consumerService = creator.create(ConsumerService.class, RetrofitConsumerService.class);
    }

}
