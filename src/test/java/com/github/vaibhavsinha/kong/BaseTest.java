package com.github.vaibhavsinha.kong;

import com.github.vaibhavsinha.kong.impl.KongClient;
import org.junit.Before;


public class BaseTest {

	public static final String KONG_ADMIN_URL = "http://kong.test.com:8001";
	public static final String KONG_API_URL = "https://kong.test.com:8443";

	protected KongClient kongClient;

	@Before
	public void before() {
		kongClient = new KongClient(KONG_ADMIN_URL);
	}

}
