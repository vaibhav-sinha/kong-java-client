package com.github.vaibhavsinha.kong.api.plugin.security;

import com.github.vaibhavsinha.kong.model.plugin.security.Acl;

/**
 * Created by vaibhav on 18/06/17.
 */
public interface AclService {
    void associateConsumer(String usernameOrId, String group);
}
