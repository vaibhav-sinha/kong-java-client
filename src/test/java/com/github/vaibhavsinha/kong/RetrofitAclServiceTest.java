package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.model.admin.consumer.Consumer;
import com.github.vaibhavsinha.kong.model.plugin.security.acl.Acl;
import com.github.vaibhavsinha.kong.model.plugin.security.acl.AclList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by dvilela on 10/22/17.
 */
public class RetrofitAclServiceTest extends BaseTest {

    private Consumer consumer;

    @Before
    public void createConsumer() throws Exception {
        consumer = new Consumer();
        String id = UUID.randomUUID().toString();
        consumer.setCustomId(id);

        consumer = kongClient.getConsumerService().createConsumer(consumer);
    }

    @After
    public void deleteConsumer() throws Exception {
        kongClient.getConsumerService().deleteConsumer(consumer.getId());
    }

    @Test
    public void testAssociateAndListAcls() throws Exception {
        kongClient.getAclService().associateConsumer(consumer.getId(), "default");

        AclList list = kongClient.getAclService().listAcls(consumer.getId(), 1L, null);

        Acl acl = list.getData().get(0);
        assertEquals(consumer.getId(), acl.getConsumerId());
        assertEquals("default", acl.getGroup());
    }

}
