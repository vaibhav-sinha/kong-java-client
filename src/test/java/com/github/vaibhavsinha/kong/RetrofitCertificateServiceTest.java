package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.impl.KongClient;
import com.github.vaibhavsinha.kong.model.admin.certificate.Certificate;
import com.github.vaibhavsinha.kong.model.admin.certificate.CertificateList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 12/06/17.
 */
public class RetrofitCertificateServiceTest extends BaseTest {



//    @Test
    public void testCreateCertificate() throws IOException {
        Certificate request = new Certificate();
        request.setCert("jwt");
        request.setKey("abc");

        Certificate response = kongClient.getCertificateService().createCertificate(request);
        System.out.print(response);
        Assert.assertEquals(request.getCert(), response.getCert());
    }

//    @Test
    public void testGetCertificate() throws IOException {
        Certificate response = kongClient.getCertificateService().getCertificate("2e9c5805-ea4e-4d38-ba7c-5e878d38489c");
        System.out.print(response);
        Assert.assertEquals("jwt", response.getCert());
    }

//    @Test
    public void testListCertificates() throws IOException {
        List<Certificate> certificates = new ArrayList<>();
        CertificateList certificateList = kongClient.getCertificateService().listCertificates();
        certificates.addAll(certificateList.getData());
        while (certificateList.getOffset() != null) {
            certificateList = kongClient.getCertificateService().listCertificates();
            certificates.addAll(certificateList.getData());
        }
        System.out.println(certificates);
        Assert.assertNotEquals(certificates.size(), 0);
    }

//    @Test(expected = KongClientException.class)
    public void exceptionTest() throws IOException {
        kongClient.getCertificateService().getCertificate("some-random-id");
    }

//    @Test
    public void testUpdateCertificate() throws IOException {
        Certificate request = new Certificate();
        request.setCert("jwt2");

        Certificate response = kongClient.getCertificateService().updateCertificate("2e9c5805-ea4e-4d38-ba7c-5e878d38489c", request);
        System.out.print(response);
        Assert.assertEquals(request.getCert(), response.getCert());
    }

//    @Test
    public void testCreateOrUpdateCertificate() throws IOException {
        Certificate request = new Certificate();
        request.setCert("jwt");
        request.setKey("jwt");
        request.setId("2e9c5805-ea4e-4d38-ba7c-5e878d38489c");
        request.setCreatedAt(new Date().getTime());

        Certificate response = kongClient.getCertificateService().createOrUpdateCertificate(request);
        System.out.print(response);
        Assert.assertEquals(request.getCert(), response.getCert());
    }

//    @Test
    public void testDeleteCertificate() throws IOException {
        kongClient.getCertificateService().deleteCertificate("2e9c5805-ea4e-4d38-ba7c-5e878d38489c");
    }

}
