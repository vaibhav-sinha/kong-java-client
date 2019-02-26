package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.consumer.Consumer;
import com.github.vaibhavsinha.kong.model.admin.consumer.ConsumerList;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitConsumerServiceTest extends BaseTest {

    private String CONSUMER_ID = "12faf661-3529-40c6-98e0-5a54894ad22f";
    private String CONSUMER_USERNAME = "";
    private String CONSUMER_CUSTOM_ID = "1234-5678-9012";

    @Test
    public void test01_CreateConsumer() throws IOException {
        Consumer request = new Consumer();
        request.setId(CONSUMER_ID);
        request.setCustomId(CONSUMER_CUSTOM_ID);

        Consumer response = kongClient.getConsumerService().createConsumer(request);
        printJson(response);
        Assert.assertEquals(request.getCustomId(), response.getCustomId());
    }

    @Test
    public void test020_GetConsumer() throws IOException {
        Consumer response = kongClient.getConsumerService().getConsumer(CONSUMER_ID);
        printJson(response);
        Assert.assertEquals(CONSUMER_ID, response.getId());
    }

    @Test
    public void test021_ListConsumers() throws IOException {
        List<Consumer> consumers = new ArrayList<>();
        ConsumerList consumerList = kongClient.getConsumerService().listConsumers(null, null, null, 1L, null);
        consumers.addAll(consumerList.getData());
        while (consumerList.getOffset() != null) {
            consumerList = kongClient.getConsumerService().listConsumers(null, null, null, 1L, consumerList.getOffset());
            consumers.addAll(consumerList.getData());
        }
        printJson(consumers);
        Assert.assertNotEquals(consumers.size(), 0);
    }


    @Test(expected = KongClientException.class)
    public void test03_exceptionTest() throws IOException {
        kongClient.getConsumerService().getConsumer("some-random-id");
    }

    @Test
    public void test04_UpdateConsumer() throws IOException {
        Consumer request = new Consumer();
        request.setCustomId("1234-5678-9012-3456");

        Consumer response = kongClient.getConsumerService().updateConsumer(CONSUMER_ID, request);
        printJson(response);
        Assert.assertEquals(request.getCustomId(), response.getCustomId());
    }

    //    @Test
    public void test05_CreateOrUpdateConsumer() throws IOException {
        Consumer request = new Consumer();
        request.setCustomId(CONSUMER_CUSTOM_ID);
        request.setId(CONSUMER_ID);
//        request.setUsername(CONSUMER_USERNAME);
        request.setCreatedAt(123456789L);

        Consumer response = kongClient.getConsumerService().createOrUpdateConsumer(request);
        printJson(response);
        Assert.assertEquals(request.getCustomId(), response.getCustomId());
    }

    @Test
    public void test09_DeleteConsumer() throws IOException {
        kongClient.getConsumerService().deleteConsumer(CONSUMER_ID);
    }


}
