package com.github.vaibhavsinha.kong.model.admin.plugin;

import com.github.vaibhavsinha.kong.model.admin.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class PluginList extends AbstractEntityList {
    Long total;
    String next;
    List<Plugin> data;
}
