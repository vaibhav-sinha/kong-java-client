package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.upstream.Upstream;
import com.github.vaibhavsinha.kong.model.upstream.UpstreamList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface UpstreamService {
    Upstream createUpstream(Upstream request);
    Upstream getUpstream(String nameOrId);
    Upstream updateUpstream(String nameOrId, Upstream request);
    Upstream createOrUpdateUpstream(Upstream request);
    Upstream deleteUpstream(String nameOrId);
    UpstreamList listUpstreams(String id, Integer slots, String name, Long size, String offset);
}
