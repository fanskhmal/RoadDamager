package com.example.newsreport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Other initialization code for AboutUsActivity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle menu item clicks for AboutUsActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_welcome:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.action_submit_report:
                startActivity(new Intent(this, SubmitReportActivity.class));
                return true;
            case R.id.action_view_news:
                startActivity(new Intent(this, ViewNewsActivity.class));
                return true;
            case R.id.action_about_us:
                // Already on AboutUsActivity, do nothing
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
