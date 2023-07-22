package com.example.newsreport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvFullname;
    TextView tvEmail;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvFullname = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnSignOut = findViewById(R.id.btnSignOut);

        // Set the text in TextViews from the GoogleSignInAccount
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String fullName = account.getDisplayName();
            String email = account.getEmail();

            tvFullname.setText(fullName);
            tvEmail.setText(email);
        }

        // Load the profile picture using Glide (Moved this code after getting the GoogleSignInAccount)
        ImageView profilePicture = findViewById(R.id.profile_picture);
        String profilePictureUrl = account != null && account.getPhotoUrl() != null ? account.getPhotoUrl().toString() : null;
        Log.d("ProfilePicture", "Profile Picture URL: " + profilePictureUrl); // Add this line for logging
        if (profilePictureUrl != null) {
            Glide.with(this)
                    .load(profilePictureUrl)
                    .placeholder(R.drawable.default_profile_picture) // Placeholder image while loading
                    .error(R.drawable.default_profile_picture) // Image to show in case of error
                    .into(profilePicture);
        }

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        // Sign out from Google Sign-In
        GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
                .addOnCompleteListener(task -> {
                    Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_submit_report:
                startActivity(new Intent(this, SubmitReportActivity.class));
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
