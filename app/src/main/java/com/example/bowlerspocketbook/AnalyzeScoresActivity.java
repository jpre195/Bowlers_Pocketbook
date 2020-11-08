package com.example.bowlerspocketbook;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class AnalyzeScoresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;

    //TODO change these so get data from database
    int[] scoresList = {290, 279, 256};
    String[] eventTypeList = {"League", "League", "League"};
    int[] gameList = {1, 2, 3};
    int[] ballImages = {R.drawable.omega_crux, R.drawable.omega_crux, R.drawable.omega_crux};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_scores);

        recyclerView = findViewById(R.id.scoresRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        programAdapter = new AnalyzeScoresAdapter(this, eventTypeList, ballImages, gameList, scoresList);
        recyclerView.setAdapter(programAdapter);

    }
}