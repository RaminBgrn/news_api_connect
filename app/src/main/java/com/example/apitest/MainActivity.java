package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NewsApiService newsApiService = new NewsApiService(this);
        btn_download = findViewById(R.id.btn_download_news);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsApiService.getArticles(new ResultCallBack() {
                    @Override
                    public void onArticlesReceived(List<NewsModel> modelList) {
                        if (modelList.size() > 0) {
                          startActivity(new Intent(MainActivity.this , NewsListVisitActivity.class));
                            //  Toast.makeText(MainActivity.this, "News was downloaded", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}