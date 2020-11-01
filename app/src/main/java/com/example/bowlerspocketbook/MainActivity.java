package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logScoresBtn;
    private Button analyzeScoresBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logScoresBtn = (Button) findViewById(R.id.logScoresBtn);
        analyzeScoresBtn = (Button) findViewById(R.id.analyzeScoresBtn);

        logScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogScoresActivity();
            }
        });

        analyzeScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnalyzeScoresActivity();
            }
        });
    }

    public void openAnalyzeScoresActivity() {
        Intent intent = new Intent(this, AnalyzeScoresActivity.class);
        startActivity(intent);
    }

    public void openLogScoresActivity() {
        Intent intent = new Intent(this, LogScoresActivity.class);
        startActivity(intent);
    }
}