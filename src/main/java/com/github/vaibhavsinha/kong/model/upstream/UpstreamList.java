package com.github.vaibhavsinha.kong.model.upstream;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class UpstreamList extends AbstractEntityList {
    Long total;
    String next;
    List<Upstream> data;
}
