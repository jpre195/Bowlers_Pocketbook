package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logScoresBtn;
    private Button analyzeScoresBtn;
    private Button buildArsenalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logScoresBtn = (Button) findViewById(R.id.logScoresBtn);
        analyzeScoresBtn = (Button) findViewById(R.id.analyzeScoresBtn);
        buildArsenalBtn = (Button) findViewById(R.id.buildArsenalBtn);

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

        buildArsenalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuildArsenalActivity();
            }
        });
    }

    public void openBuildArsenalActivity() {
        Intent intent = new Intent(this, BuildArsenalPreferencesActivity.class);
        startActivity(intent);
    }

    public void openAnalyzeScoresActivity() {
        Intent intent = new Intent(this, AnalyzeScoresMain.class);
        startActivity(intent);
    }

    public void openLogScoresActivity() {
        Intent intent = new Intent(this, LogScoresActivity.class);
        startActivity(intent);
    }
}