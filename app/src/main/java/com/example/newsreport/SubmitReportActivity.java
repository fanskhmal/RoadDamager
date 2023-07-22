package com.example.newsreport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SubmitReportActivity extends AppCompatActivity {
    EditText etName, etEmail, etComments;
    RequestQueue queue;
    final String URL = "http://192.168.0.107/comments/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        queue = Volley.newRequestQueue(getApplicationContext());
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etComments = findViewById(R.id.etComments);

        Button button = findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
    }

    public void makeRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // The response variable contains the response from the API, if needed
                // You can add code here to handle the response from the server if required
                // For example, you can show a success message if the data is successfully inserted.
                Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
            }
        }, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", etName.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("comments", etComments.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle menu item clicks for MainActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_welcome:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.action_submit_report:
                // Already on SubmitReportActivity (MainActivity), do nothing
                return true;
            case R.id.action_view_news:
                startActivity(new Intent(this, ViewNewsActivity.class));
                return true;
            case R.id.action_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
