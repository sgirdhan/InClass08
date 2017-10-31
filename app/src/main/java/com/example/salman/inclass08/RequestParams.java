/*
// In Class 08
// Sharan Girdhani  - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by sharangirdhani on 10/30/17.
 */

public class RequestParams implements Serializable {
    String baseUrl, method;
    HashMap<String, String> params = new HashMap<>();

    public RequestParams(String method, String baseUrl) {
        this.baseUrl = baseUrl;
        this.method = method;
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();

        for (String key : params.keySet()) {
            try {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                if(sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key+"="+value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String getEncodedUrl() {
        return this.baseUrl + "?" + getEncodedParams();
    }

    public HttpURLConnection setupConnection() throws IOException {
        if(method.equals("GET")) {
            URL url = new URL(getEncodedUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con;
        } else { // POST
            URL url = new URL(this.baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(getEncodedParams());
            writer.flush();
            return con;
        }
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "baseUrl='" + baseUrl + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
