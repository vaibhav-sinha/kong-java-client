package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.admin.upstream.Upstream;
import com.github.vaibhavsinha.kong.model.admin.upstream.UpstreamList;
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
public class RetrofitUpstreamServiceTest {

    private KongClient kongClient;

    @Before
    public void before() {
        kongClient = new KongClient("http://localhost:8001");
    }

    @Test
    public void testCreateUpstream() throws IOException {
        Upstream request = new Upstream();
        request.setName("local.com");

        Upstream response = kongClient.getUpstreamService().createUpstream(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testGetUpstream() throws IOException {
        Upstream response = kongClient.getUpstreamService().getUpstream("0ba0f245-0fda-43a1-a96f-ac33e1b4cf41");
        System.out.print(response);
        Assert.assertEquals("local.com", response.getName());
    }

    @Test
    public void testListUpstreams() throws IOException {
        List<Upstream> upstreams = new ArrayList<>();
        UpstreamList upstreamList = kongClient.getUpstreamService().listUpstreams(null, null, null, 1L, null);
        upstreams.addAll(upstreamList.getData());
        while (upstreamList.getOffset() != null) {
            upstreamList = kongClient.getUpstreamService().listUpstreams(null, null, null, 1L, upstreamList.getOffset());
            upstreams.addAll(upstreamList.getData());
        }
        System.out.println(upstreams);
        Assert.assertNotEquals(upstreams.size(), 0);
    }

    @Test(expected = KongClientException.class)
    public void exceptionTest() throws IOException {
        kongClient.getUpstreamService().getUpstream("some-random-id");
    }

    @Test
    public void testUpdateUpstream() throws IOException {
        Upstream request = new Upstream();
        request.setName("local.com");

        Upstream response = kongClient.getUpstreamService().updateUpstream("0ba0f245-0fda-43a1-a96f-ac33e1b4cf41", request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testCreateOrUpdateUpstream() throws IOException {
        Upstream request = new Upstream();
        request.setName("local.com");
        request.setId("0ba0f245-0fda-43a1-a96f-ac33e1b4cf41");
        request.setCreatedAt(new Date().getTime());

        Upstream response = kongClient.getUpstreamService().createOrUpdateUpstream(request);
        System.out.print(response);
        Assert.assertEquals(request.getName(), response.getName());
    }

    @Test
    public void testDeleteUpstream() throws IOException {
        kongClient.getUpstreamService().deleteUpstream("0ba0f245-0fda-43a1-a96f-ac33e1b4cf41");
    }

}
