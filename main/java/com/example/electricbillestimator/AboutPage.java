package com.example.electricbillestimator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);

        Toolbar toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button (back arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide the title text
        }

        // Set text for ID number, program, and summary
        TextView idNumberTextView = findViewById(R.id.idNumberTextView);
        idNumberTextView.setText("ID Number: 2022917171");

        TextView classTextView = findViewById(R.id.classTextView);
        classTextView.setText("Class: RCS2405B");

        TextView programTextView = findViewById(R.id.groupTextView);
        programTextView.setText("Programme: CS240");

        TextView summaryTextView = findViewById(R.id.summaryTextView);
        summaryTextView.setText("Watt Wise is designed to help users " +
                "calculate and manage their electricity usage " +
                "and expenses efficiently. It simplifies the " +
                "process of estimating monthly electric bills by allowing " +
                "users to input their electricity consumption data and other relevant parameters.");

        // Find GitHub button
        Button githubButton = findViewById(R.id.githubButton);

        // Set click listener for GitHub button
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open GitHub URL in browser
                openUrlInBrowser("https://github.com/MayaUzma/ict620");
            }
        });

        // Find YouTube button
        Button youtubeButton = findViewById(R.id.youtubeButton);

        // Set click listener for YouTube button
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open YouTube URL in browser
                openUrlInBrowser("https://www.youtube.com/channel/yourchannel");
            }
        });
    }


    // Method to open a URL in the browser
    private void openUrlInBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close this activity and return to MainActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
