package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.certificate.Certificate;
import com.github.vaibhavsinha.kong.model.admin.certificate.CertificateList;

/**
 * Created by vaibhav on 13/06/17.
 */
public interface CertificateService {
    Certificate createCertificate(Certificate request);
    Certificate getCertificate(String sniOrId);
    Certificate updateCertificate(String sniOrId, Certificate request);
    Certificate createOrUpdateCertificate(Certificate request);
    Certificate deleteCertificate(String sniOrId);
    CertificateList listCertificates();
}
