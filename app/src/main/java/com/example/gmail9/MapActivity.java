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

public class MapActivity extends AppCompatActivity {

    private EditText editTextCurrentLocation;
    private EditText editTextDestination;
    private Button buttonShowMap;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        editTextCurrentLocation = findViewById(R.id.editTextCurrentLocation);
        editTextDestination = findViewById(R.id.editTextDestination);
        buttonShowMap = findViewById(R.id.buttonShowMap);
        webView = findViewById(R.id.webView);

//        // Set up the Spinner with destination branches
//        String[] branches = {"Matara", "Kandy", "Negombo", "Colombo", "Jaffna", "Kegalle", "Galle", "Gampaha", "Piliyandala", "Kottawa"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branches);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerDestination.setAdapter(adapter);


        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        buttonShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLocation = editTextCurrentLocation.getText().toString();
                String destination = editTextDestination.getText().toString();

                // Load Google Maps website with user's input
                webView.loadUrl("https://www.google.com/maps/dir/" + currentLocation + "/" + destination);
            }
        });

        Button backButton = findViewById(R.id.buttonback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Back to DashBoard
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}