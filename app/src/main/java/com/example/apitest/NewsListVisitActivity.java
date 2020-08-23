package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NewsListVisitActivity extends AppCompatActivity implements ResultCallBack {
    RecyclerView rv_news_list;
    //List<NewsModel> newsModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list_visit);
        rv_news_list = findViewById(R.id.rv_news_list);
        rv_news_list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL , false));
        NewsApiService newsApiService = new NewsApiService(this);
        newsApiService.getArticles(this);
    }

    @Override
    public void onArticlesReceived(List<NewsModel> modelList) {
        NewsApiServiceAdapter adapter = new NewsApiServiceAdapter(this, modelList);
        rv_news_list.setAdapter(adapter);
    }

    @Override
    public void onErrorReceived(String message) {

    }
}