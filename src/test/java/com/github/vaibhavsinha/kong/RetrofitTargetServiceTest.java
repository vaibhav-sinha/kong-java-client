package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.target.Target;
import com.github.vaibhavsinha.kong.model.target.TargetList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
public class RetrofitTargetServiceTest {

    private KongClient kongClient;

    @Before
    public void before() {
        kongClient = new KongClient("http://localhost:8001");
    }

    @Test
    public void testCreateTarget() throws IOException {
        Target request = new Target();
        request.setTarget("1.2.3.4:80");

        Target response = kongClient.getTargetService().createTarget("local.com", request);
        System.out.print(response);
        Assert.assertEquals(request.getTarget(), response.getTarget());
    }

    @Test
    public void testListTargets() throws IOException {
        List<Target> targets = new ArrayList<>();
        TargetList targetList = kongClient.getTargetService().listTargets("local.com", null, null, null, 1L, null);
        targets.addAll(targetList.getData());
        while (targetList.getOffset() != null) {
            targetList = kongClient.getTargetService().listTargets("local.com", null, null, null, 1L, targetList.getOffset());
            targets.addAll(targetList.getData());
        }
        System.out.println(targets);
        Assert.assertNotEquals(targets.size(), 0);
    }

    @Test
    public void testListActiveTargets() throws IOException {
        List<Target> targets = new ArrayList<>();
        TargetList targetList = kongClient.getTargetService().listActiveTargets("local.com");
        targets.addAll(targetList.getData());
        while (targetList.getOffset() != null) {
            targetList = kongClient.getTargetService().listActiveTargets("listActiveTargets.com");
            targets.addAll(targetList.getData());
        }
        System.out.println(targets);
        Assert.assertNotEquals(targets.size(), 0);
    }

    @Test
    public void testDeleteTarget() throws IOException {
        kongClient.getTargetService().deleteTarget("local.com", "1.2.3.4:80");
    }

}
