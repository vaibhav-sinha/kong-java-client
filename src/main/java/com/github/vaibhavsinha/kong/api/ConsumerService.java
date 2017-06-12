package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.consumer.Consumer;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface ConsumerService {
    Consumer createConsumer(Consumer request);
    Consumer getConsumer(String usernameOrId);
    Consumer updateConsumer(String usernameOrId, Consumer request);
    Consumer createOrUpdateConsumer(Consumer request);
    Consumer deleteConsumer(String usernameOrId);
}
