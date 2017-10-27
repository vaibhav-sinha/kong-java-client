package com.github.vaibhavsinha.kong.model.plugin.authentication.jwt;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 16/06/17.
 */
@Data
public class JwtCredentialList extends AbstractEntityList {
    Long total;
    List<JwtCredential> data;
}
