package com.github.vaibhavsinha.kong.utils;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fanhua on 2017-07-28.
 */
public class UrlUtil {

	public static Map<String, String> splitQueryString(String urlString) {
		try {
			String queryString = "";
			if( urlString.toLowerCase().startsWith("http")){
				URL url = new URL(urlString);
				queryString = url.getQuery();
			}else{  //sometimes kong does not return url, but URI only
				URI uri = new URI(urlString);
				queryString = uri.getQuery();
			}
			return parseQueryString( queryString);

		}
		catch (URISyntaxException | MalformedURLException | UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Could not parse URL: " + urlString, e);
		}
	}


	public static Map<String, String> splitFragmentString(String urlString) {
		try {
			URL url = new URL(urlString);
			String fragmentString = url.getRef();
			return parseQueryString( fragmentString);
		}
		catch (MalformedURLException | UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Could not parse URL: " + urlString, e);
		}
	}

	private static Map<String, String>  parseQueryString( String fragmentString) throws UnsupportedEncodingException {
		Map<String, String> query_pairs = new LinkedHashMap<>();
		if (fragmentString != null) {
			String[] pairs = fragmentString.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
		}
		return query_pairs;
	}


	public static void main(String[] args) throws Exception {

		String url1 = "http://kong.test.com/simulate/getCode?access_token=4ddb06a7c9c44ea1a1f3043ee8de9938&expires_in=7200";

		String url2 = "http://kong.test.com/simulate/getCode#access_token=4ddb06a7c9c44ea1a1f3043ee8de9938&expires_in=7200";

		System.out.println(new Gson().toJson(splitQueryString(url1)));

		System.out.println(new Gson().toJson(splitFragmentString(url2)));
	}
}
