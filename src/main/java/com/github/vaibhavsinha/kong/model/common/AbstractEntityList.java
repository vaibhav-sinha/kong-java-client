package com.github.vaibhavsinha.kong.model.common;

import com.github.vaibhavsinha.kong.utils.UrlUtil;

/**
 * Created by vaibhav on 13/06/17.
 */
public abstract class AbstractEntityList {

    public String getNext() {
        return null;
    }

    public String getOffset() {
        if(getNext() == null) {
            return null;
        }
        else {
            return UrlUtil.splitQueryString(getNext()).get("offset");
        }
    }

}
