package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.model.admin.consumer.Consumer;
import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredential;
import com.github.vaibhavsinha.kong.model.plugin.authentication.jwt.JwtCredentialList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by dvilela on 10/22/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitJwtServiceTest extends BaseTest {

    private static Consumer consumer;

    @BeforeClass
    public static void createConsumer() throws Exception {
        Consumer c = new Consumer();
        c.setCustomId(UUID.randomUUID().toString());
        consumer = kongClient.getConsumerService().createConsumer(c);
    }

    @AfterClass
    public static void deleteConsumer() throws Exception {
        kongClient.getConsumerService().deleteConsumer(consumer.getId());
    }

    @Test
    public void test01_addCredentialTest() throws Exception {
        JwtCredential credential = kongClient.getJwtService().addCredentials(consumer.getId(), new JwtCredential());
        assertEquals(consumer.getId(), credential.getConsumerId());
    }

    @Test
    public void test02_listCredentialsTest() throws Exception {
        JwtCredentialList list = kongClient.getJwtService().listCredentials(consumer.getId(), null, null);
        JwtCredential credential = list.getData().get(0);
        assertEquals(consumer.getId(), credential.getConsumerId());
    }

    @Test
    public void test03_deleteCredentialTest() throws Exception {
        JwtCredentialList list = kongClient.getJwtService().listCredentials(consumer.getId(), null, null);
        JwtCredential credential = list.getData().get(0);
        kongClient.getJwtService().deleteCredentials(consumer.getId(), credential.getId());
        JwtCredentialList list2 = kongClient.getJwtService().listCredentials(consumer.getId(), null, null);
        assertEquals(0L, (long) list2.getTotal());
    }

}
