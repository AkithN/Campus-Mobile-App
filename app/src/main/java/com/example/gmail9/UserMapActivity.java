package com.example.gmail9;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class UserMapActivity extends AppCompatActivity {

    private EditText edituserTextCurrentLocation;
    private EditText edituserTextDestination;
    private Button buttonuserShowMap;
    private WebView userwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_map);

        edituserTextCurrentLocation = findViewById(R.id.edituserTextCurrentLocation);
        edituserTextDestination = findViewById(R.id.edituserTextDestination);
        buttonuserShowMap = findViewById(R.id.buttonuserShowMap);
        userwebView = findViewById(R.id.userwebView);

        // Set up the WebView
        userwebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = userwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Button click listener to show map
        buttonuserShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLocation = edituserTextCurrentLocation.getText().toString();
                String destination = edituserTextDestination.getText().toString();

                // Load Google Maps website with user's input
                userwebView.loadUrl("https://www.google.com/maps/dir/" + currentLocation + "/" + destination);
            }
        });

        // Button click listener to go back
        Button backButton = findViewById(R.id.buttonuserback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Back to DashBoard
                Intent intent = new Intent(UserMapActivity.this, UserActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (userwebView.canGoBack()) {
            userwebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
