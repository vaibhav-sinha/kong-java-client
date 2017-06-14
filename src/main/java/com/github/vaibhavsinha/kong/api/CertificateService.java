package com.github.vaibhavsinha.kong.api;

import com.github.vaibhavsinha.kong.model.certificate.Certificate;
import com.github.vaibhavsinha.kong.model.certificate.CertificateList;

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
