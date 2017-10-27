package com.github.vaibhavsinha.kong.model.plugin.security.acl;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by dvilela on 10/22/17.
 */
@Data
public class AclList extends AbstractEntityList {
    Long total;
    List<Acl> data;
}
