package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.target.Target;
import com.github.vaibhavsinha.kong.model.target.TargetList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface TargetService {
    Target createTarget(String upstreamNameOrId, Target request);
    Target deleteTarget(String upstreamNameOrId, String target);
    TargetList listTargets(String upstreamNameOrId, String id, Integer weight, String target, Long size, String offset);
    TargetList listActiveTargets(String upstreamNameOrId);
}
