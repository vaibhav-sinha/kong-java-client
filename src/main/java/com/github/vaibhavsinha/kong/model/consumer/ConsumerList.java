package com.github.vaibhavsinha.kong.model.consumer;

import com.github.vaibhavsinha.kong.exception.KongClientException;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vaibhav on 13/06/17.
 */
@Data
public class ConsumerList {
    Long total;
    String next;
    List<Consumer> data;

    public String getOffset() {
        if(next == null) {
            return null;
        }
        else {
            return splitQuery(next).get("offset");
        }
    }

    private Map<String, String> splitQuery(String urlString) {
        try {
            URL url = new URL(urlString);
            Map<String, String> query_pairs = new LinkedHashMap<>();
            String query = url.getQuery();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return query_pairs;
        }
        catch (MalformedURLException | UnsupportedEncodingException e) {
            throw new KongClientException("Could not parse URL " + next);
        }
    }
}
