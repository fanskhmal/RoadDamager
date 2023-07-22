package com.example.newsreport; // Make sure to use your correct package name

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Get the NewsItem object from the intent
        Intent intent = getIntent();
        NewsItem newsItem = (NewsItem) intent.getSerializableExtra("newsItem");

        // Display the details of the selected news item
        if (newsItem != null) {
            TextView tvTitle = findViewById(R.id.tvNewsTitle);
            TextView tvDescription = findViewById(R.id.tvNewsDescription);
            TextView tvDate = findViewById(R.id.tvNewsDate);

            tvTitle.setText(newsItem.getTitle());
            tvDescription.setText(newsItem.getDescription());
            tvDate.setText(newsItem.getDate());
        }
    }
}
