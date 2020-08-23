package com.example.apitest;

import java.util.List;

public interface ResultCallBack {
    void onArticlesReceived(List<NewsModel> modelList);
    void onErrorReceived(String message);
}
