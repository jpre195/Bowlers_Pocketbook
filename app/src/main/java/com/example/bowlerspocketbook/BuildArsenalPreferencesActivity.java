package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import androidx.appcompat.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;

public class BuildArsenalPreferencesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    Spinner playStyleSpinner;
    Spinner releasePositionSpinner;
    TextView toolbarTitle;

    ImageButton activityMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_arsenal_preferences);

        activityMenuBtn = (ImageButton) findViewById(R.id.menu_button);

        activityMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu activityMenuPopup = new PopupMenu(BuildArsenalPreferencesActivity.this, v);
                activityMenuPopup.setOnMenuItemClickListener(BuildArsenalPreferencesActivity.this);
                activityMenuPopup.inflate(R.menu.activity_menu);
                activityMenuPopup.show();
            }
        });

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Build Arsenal");

        playStyleSpinner = findViewById(R.id.playStyleSpinner);
        ArrayAdapter<CharSequence> playStyleAdapter = ArrayAdapter.createFromResource(this, R.array.play_style_array, android.R.layout.simple_spinner_item);
        playStyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playStyleSpinner.setAdapter(playStyleAdapter);
        playStyleSpinner.setOnItemSelectedListener(this);

        releasePositionSpinner = findViewById(R.id.releasePositionSpinner);
        ArrayAdapter<CharSequence> releasePositionAdapter = ArrayAdapter.createFromResource(this, R.array.release_position_array, android.R.layout.simple_spinner_item);
        releasePositionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        releasePositionSpinner.setAdapter(releasePositionAdapter);
        releasePositionSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openFindABallActivity() {
        Intent intent = new Intent(this, FindABallActivity.class);
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

    public void openHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.goToHome:
                openHomeActivity();
                return true;
            case R.id.goToBuildArsenal:
                return false;
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