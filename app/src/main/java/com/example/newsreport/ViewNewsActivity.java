package com.example.newsreport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewNewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsItem> newsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news); // Fix the layout reference

        recyclerView = findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsItemList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsItemList);
        recyclerView.setAdapter(newsAdapter);

        // Fetch news data from the JSON Server
        fetchNewsData();
    }

    private void fetchNewsData() {
        String url = "http://YOURAPIADDRESS/news";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parse the JSON response using Gson
                        Gson gson = new Gson();
                        Type newsListType = new TypeToken<List<NewsItem>>() {}.getType();
                        List<NewsItem> newsItems = gson.fromJson(response.toString(), newsListType);

                        // Update the newsItemList with the fetched data and notify the adapter
                        newsItemList.clear();
                        newsItemList.addAll(newsItems);
                        newsAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error if the request fails
                        Toast.makeText(ViewNewsActivity.this, "Error fetching news data", Toast.LENGTH_SHORT).show();
                        Log.e("Volley Error", error.toString());
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    // Static method to handle news item click
    public static void onNewsItemClick(NewsItem newsItem) {
        // Implement your logic to handle the click event
        // For example, you can open the NewsDetailActivity and pass the NewsItem as an Intent extra
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_welcome:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.action_submit_report:
                startActivity(new Intent(this, SubmitReportActivity.class));
                return true;
            case R.id.action_view_news:
                // Already on View News, do nothing
                return true;
            case R.id.action_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
