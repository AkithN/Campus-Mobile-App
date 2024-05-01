package com.example.gmail9;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {


    CardView courseCard , mappingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView signTextView = findViewById(R.id.txtlogout);
        signTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, Login.class);
                startActivity(intent);
            }
        });
        TextView manageTextView = findViewById(R.id.txtmanage);
        manageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, AdminSigninActivity.class);
                startActivity(intent);
            }
        });

        courseCard = findViewById(R.id.courseCard);
        courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

        mappingCard = findViewById(R.id.mappingCard);
        mappingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, UserMapActivity.class);
                startActivity(intent);
            }
        });


    }
}

