package com.example.gmail9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminSigninActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);



        usernameEditText = findViewById(R.id.usernameET);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInBtn);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                    // Credentials are correct, navigate to AdminActivity
                    Toast.makeText(AdminSigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    navigateToAdminActivity();
                } else {
                    // Credentials are incorrect, display an error message
                    Toast.makeText(AdminSigninActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void navigateToAdminActivity() {
        Intent intent = new Intent(AdminSigninActivity.this, AdminActivity.class);
        startActivity(intent);
        finish(); // Optionally, you can finish the current activity to prevent the user from navigating back to it using the back button
    }
}