package com.example.apitest;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsApiService {
    private static final String URL_ADDRESS = "http://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=7cd58c8fbc7243418d186a4fccd441b4";
    private Context context;

    public NewsApiService(Context context) {
        this.context = context;
    }

    public void getArticles(final ResultCallBack resultCallBack) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                URL_ADDRESS,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<NewsModel> newsModelsList = new ArrayList<>();
                        try {
                            JSONArray articlesJsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < articlesJsonArray.length(); i++) {
                                JSONObject articlesJsonObject = articlesJsonArray.getJSONObject(i);
                                NewsModel newsModel = new NewsModel();
                                newsModel.setAuthor(articlesJsonObject.getString("author"));
                                newsModel.setTitle(articlesJsonObject.getString("title"));
                                newsModel.setDescription(articlesJsonObject.getString("description"));
                                newsModel.setUrl(articlesJsonObject.getString("url"));
                                newsModel.setUrlToImage(articlesJsonObject.getString("urlToImage"));
                                newsModel.setPublishedAt(articlesJsonObject.getString("publishedAt"));
                                newsModel.setContent(articlesJsonObject.getString("content"));
                                newsModelsList.add(newsModel);
                            }
                        } catch (JSONException e) {
                            Log.e("articlesJsonArrayLoop", "Error in articles loop");
                        }
                        resultCallBack.onArticlesReceived(newsModelsList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultCallBack.onErrorReceived("Error in loading information");
                    }
                }
        );
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }
}
