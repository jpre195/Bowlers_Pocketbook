package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.PopupMenu;
import android.widget.ImageButton;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button logScoresBtn;
    private Button analyzeScoresBtn;
    private Button buildArsenalBtn;
    private Button findABallBtn;

    ImageButton activityMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logScoresBtn = (Button) findViewById(R.id.logScoresBtn);
        analyzeScoresBtn = (Button) findViewById(R.id.analyzeScoresBtn);
        buildArsenalBtn = (Button) findViewById(R.id.buildArsenalBtn);
        findABallBtn = (Button) findViewById(R.id.findABallBtn);

        activityMenuBtn = (ImageButton) findViewById(R.id.menu_button);

        activityMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu activityMenuPopup = new PopupMenu(MainActivity.this, v);
                activityMenuPopup.setOnMenuItemClickListener(MainActivity.this);
                activityMenuPopup.inflate(R.menu.activity_menu);
                activityMenuPopup.show();
            }
        });

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

        findABallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFindABallActivity();
            }
        });
    }

    public void openFindABallActivity() {
        Intent intent = new Intent(this, FindABallActivity.class);
        startActivity(intent);
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.goToHome:
                return false;
            case R.id.goToBuildArsenal:
                openBuildArsenalActivity();
                return true;
            case R.id.goToFindABall:
                openFindABallActivity();
                return true;
            case R.id.goToLogScores:
                openLogScoresActivity();
                return true;
            case R.id.goToAnalyzeScores:
                openAnalyzeScoresActivity();
                return true;
            default:
                return false;
        }
    }
}