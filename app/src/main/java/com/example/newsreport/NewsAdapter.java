package com.example.newsreport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsItem> newsList;

    public NewsAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsList.get(position);
        holder.tvNewsTitle.setText(newsItem.getTitle());
        holder.tvNewsDate.setText(newsItem.getDate());
        holder.tvNewsDescription.setText(newsItem.getDescription());

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewNewsActivity.onNewsItemClick(newsItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewsTitle;
        TextView tvNewsDate; // Add this line to include the date TextView
        TextView tvNewsDescription;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsDate = itemView.findViewById(R.id.tvNewsDate); // Initialize the date TextView
            tvNewsDescription = itemView.findViewById(R.id.tvNewsDescription);
        }
    }
}
