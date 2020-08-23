package com.example.apitest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsApiServiceAdapter extends RecyclerView.Adapter<NewsApiServiceAdapter.NewsViewHolder> {

    private Context context;
    private List<NewsModel> newsModelList;

    public NewsApiServiceAdapter(Context context , List<NewsModel> newsModelList){
        this.context = context;
        this.newsModelList = newsModelList;

    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_news_list_layout , parent , false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_news_image;
        TextView tv_news_title;
        TextView tv_news_author;
        TextView tv_news_description;
        TextView tv_news_date;
        public NewsViewHolder(View itemView) {
            super(itemView);
            iv_news_image = itemView.findViewById(R.id.iv_news_image);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            tv_news_author = itemView.findViewById(R.id.tv_news_author);
            tv_news_description = itemView.findViewById(R.id.tv_news_description);
            tv_news_date = itemView.findViewById(R.id.tv_news_date);
        }
        private void bind(NewsModel newsModel){
//            List<NewsModel> list = new ArrayList<>();
//            for (int i = 0; i < newsModel.len; i++) {
//
//            }

            Picasso.get().load(Uri.parse(newsModel.getUrlToImage())).into(iv_news_image);
            tv_news_title.setText(newsModel.getTitle());
            tv_news_author.setText(newsModel.getAuthor());
            tv_news_description.setText(newsModel.getDescription());
            tv_news_date.setText(newsModel.getPublishedAt());
        }
    }



}
