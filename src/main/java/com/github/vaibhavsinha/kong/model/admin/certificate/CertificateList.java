package com.github.vaibhavsinha.kong.model.admin.certificate;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class CertificateList extends AbstractEntityList {
    Long total;
    List<Certificate> data;

    public String getNext() {
        return null;
    }
}
