package com.github.vaibhavsinha.kong.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


/**
 * Https Util.
 * Refer to : https://github.com/hongyangAndroid/okhttputils
 *
 * 设置可访问所有的https网站
 *     HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(null, null, null);
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *         .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
 *         //其他配置
 *         .build();
 *
 * 设置具体的证书
 *     HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(证书的inputstream, null, null);
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *         .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager))
 *          //其他配置
 *         .build();
 *
 * 双向认证
 *     HttpsUtil.getSslSocketFactory(
 *         证书的inputstream,
 *         本地证书的inputstream,
 *         本地证书的密码)
 *
 * */
public class HttpsUtil {

	private static final String HTTPS_SSL_TYPE_TLS = "TLS";
	private static final String CERTIFICATE_TYPE_X509 = "X.509";
	private static final String LOCAL_KEY_STORE_TYPE_BKS = "BKS";

	public static class SSLParams {
		public SSLSocketFactory sSLSocketFactory;
		public X509TrustManager trustManager;
	}

	public static SSLParams getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password) {
		SSLParams sslParams = new SSLParams();
		try {
			TrustManager[] trustManagers = prepareTrustManager(certificates);
			KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
			SSLContext sslContext = SSLContext.getInstance(HTTPS_SSL_TYPE_TLS);
			X509TrustManager trustManager = null;
			if (trustManagers != null) {
				trustManager = new MyTrustManager(chooseTrustManager(trustManagers));
			} else {
				trustManager = new UnSafeTrustManager();
			}
			sslContext.init(keyManagers, new TrustManager[] { trustManager }, null);
			sslParams.sSLSocketFactory = sslContext.getSocketFactory();
			sslParams.trustManager = trustManager;
			return sslParams;
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError(e);
		} catch (KeyManagementException e) {
			throw new AssertionError(e);
		} catch (KeyStoreException e) {
			throw new AssertionError(e);
		}
	}

	private class UnSafeHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private static class UnSafeTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[] {};
		}
	}

	private static TrustManager[] prepareTrustManager(InputStream... certificates) {
		if (certificates == null || certificates.length <= 0)
			return null;
		try {

			CertificateFactory certificateFactory = CertificateFactory.getInstance(CERTIFICATE_TYPE_X509);
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null);
			int index = 0;
			for (InputStream certificate : certificates) {
				String certificateAlias = Integer.toString(index++);
				keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
				try {
					if (certificate != null)
						certificate.close();
				} catch (IOException e) {
					//ignore
				}
			}
			TrustManagerFactory trustManagerFactory = null;

			trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(keyStore);

			TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

			return trustManagers;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
		try {
			if (bksFile == null || password == null)
				return null;

			KeyStore clientKeyStore = KeyStore.getInstance(LOCAL_KEY_STORE_TYPE_BKS);
			clientKeyStore.load(bksFile, password.toCharArray());
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(clientKeyStore, password.toCharArray());
			return keyManagerFactory.getKeyManagers();

		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
		for (TrustManager trustManager : trustManagers) {
			if (trustManager instanceof X509TrustManager) {
				return (X509TrustManager) trustManager;
			}
		}
		return null;
	}

	private static class MyTrustManager implements X509TrustManager {
		private X509TrustManager defaultTrustManager;
		private X509TrustManager localTrustManager;

		public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException {
			TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			var4.init((KeyStore) null);
			defaultTrustManager = chooseTrustManager(var4.getTrustManagers());
			this.localTrustManager = localTrustManager;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			try {
				defaultTrustManager.checkServerTrusted(chain, authType);
			} catch (CertificateException ce) {
				localTrustManager.checkServerTrusted(chain, authType);
			}
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
}
