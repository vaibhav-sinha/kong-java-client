package com.github.vaibhavsinha.kong.model.admin.route;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by kgignatyev at 2019/02/04
 */
@Data
public class RouteList extends AbstractEntityList {
    Long total;
    String next;
    List<Route> data;
}
