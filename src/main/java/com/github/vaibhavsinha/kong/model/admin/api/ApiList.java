package com.github.vaibhavsinha.kong.model.admin.api;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Deprecated
@Data
public class ApiList extends AbstractEntityList {
    Long total;
    String next;
    List<Api> data;
}
