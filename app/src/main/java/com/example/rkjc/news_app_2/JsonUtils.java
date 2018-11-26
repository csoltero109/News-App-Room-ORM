package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<NewsItem> parseNews(String JSONString) {
        List<NewsItem> list = new ArrayList<NewsItem>();
        try {
            JSONObject o = new JSONObject(JSONString);
            JSONArray a = o.getJSONArray("articles");
            for (int i = 0; i < a.length(); i++) {
                JSONObject json = a.getJSONObject(i);
                NewsItem n = new NewsItem(json.getString("author"),
                                            json.getString("title"),
                                            json.getString("description"),
                                            json.getString("url"),
                                            json.getString("urlToImage"),
                                            json.getString("publishedAt"));
                list.add(n);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return list;
    }
}


