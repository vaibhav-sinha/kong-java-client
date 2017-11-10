package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.model.admin.consumer.Consumer;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuthCredential;
import com.github.vaibhavsinha.kong.model.plugin.authentication.key.KeyAuthCredentialList;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by dvilela on 11/08/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitKeyAuthServiceTest extends BaseTest {

    private Consumer consumer;

    @Before
    public void createConsumer() throws Exception {
        Consumer c = new Consumer();
        c.setCustomId(UUID.randomUUID().toString());
        consumer = kongClient.getConsumerService().createConsumer(c);

        KeyAuthCredential credential = kongClient.getKeyAuthService().addCredentials(consumer.getId(), null);
        assertEquals(consumer.getId(), credential.getConsumerId());
        assertNotNull(consumer.getId(), credential.getKey());
    }

    @After
    public void deleteConsumer() throws Exception {
        kongClient.getConsumerService().deleteConsumer(consumer.getId());
    }

    @Test
    public void test01_addRepeatedCredentialTest() throws Exception {
        KeyAuthCredential credential1 = kongClient.getKeyAuthService().addCredentials(consumer.getId(), null);
        assertEquals(consumer.getId(), credential1.getConsumerId());
        assertNotNull(consumer.getId(), credential1.getKey());

        try {
            KeyAuthCredential credential2 = kongClient.getKeyAuthService().addCredentials(consumer.getId(),
                    credential1.getKey());
            fail("RetrofitKeyAuthService did not throw");
        } catch (KongClientException e) {
            assertEquals(409, e.getCode());
            assertEquals("Conflict", e.getError());
        }
    }

    @Test
    public void test02_listCredentialsTest() throws Exception {
        KeyAuthCredentialList list = kongClient.getKeyAuthService().listCredentials(consumer.getId(), null,
                null);
        KeyAuthCredential credential = list.getData().get(0);
        assertEquals(consumer.getId(), credential.getConsumerId());
    }

    @Test
    public void test03_deleteCredentialTest() throws Exception {
        KeyAuthCredentialList list = kongClient.getKeyAuthService().listCredentials(consumer.getId(), null,
                null);
        List<KeyAuthCredential> credentials = list.getData();
        for (KeyAuthCredential credential : credentials) {
            kongClient.getKeyAuthService().deleteCredential(credential.getConsumerId(), credential.getId());
        }
        KeyAuthCredentialList list2 = kongClient.getKeyAuthService().listCredentials(consumer.getId(), null,
                null);
        assertEquals(0L, (long) list2.getTotal());
    }

}
