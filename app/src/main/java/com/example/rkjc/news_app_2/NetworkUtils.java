package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static final String BASE_URL = "https://newsapi.org/";
    public static final String QUERY_PARAMETER = "apiKey";
    public static final String PARAM_SORT = "sortBy";
    public static final String SOURCE = "source";
    public static final String API_KEY = "5bf33d24a120418e9800d7cdbcdd9cdd";

    public static URL buildUrl() {
        Uri.Builder builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter(SOURCE, "the-next-web")
                .appendQueryParameter(PARAM_SORT, "latest")
                .appendQueryParameter(QUERY_PARAMETER, API_KEY);


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else{
                return null;
            }
        }finally{
            urlConnection.disconnect();
        }
    }

}
