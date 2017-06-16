package com.github.vaibhavsinha.kong.model.plugin.authentication.jwt;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;

import java.util.List;

/**
 * Created by vaibhav on 16/06/17.
 */
public class JwtCredentialList extends AbstractEntityList {
    Long total;
    List<JwtCredential> data;
}
