package com.github.vaibhavsinha.kong.model.admin.sni;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class SniList extends AbstractEntityList {
    Long total;
    List<Sni> data;

    public String getNext() {
        return null;
    }
}
