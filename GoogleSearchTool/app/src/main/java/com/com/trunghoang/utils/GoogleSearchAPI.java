package com.com.trunghoang.utils;

import com.google.gson.Gson;
import com.trunghoang.model.GoogleData;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by trunghoang on 7/9/15.
 */
public class GoogleSearchAPI {
    public static GoogleData search(String query, String charset, int start, int block) {
        try {
            String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

            URL url;
            url = new URL(address + URLEncoder.encode(query, charset) + "&start=" + start + "&rsz=" + block);
            InputStreamReader reader = new InputStreamReader(url.openStream(), charset);
            GoogleData results = new Gson().fromJson(reader, GoogleData.class);
            return results;
        }catch (Exception e) {

        }
        return null;
    }
}
