package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.*;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineData;

public class AnalyzeScoresGraphsActivity extends AppCompatActivity {

    LineChart leagueLineChart;

    DatabaseHelper databaseHelper;
    ArrayList scores;

    ArrayList<Integer> scoresList = new ArrayList<Integer>();
    List<Entry> entries = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_scores_graphs);

        leagueLineChart = (LineChart) findViewById(R.id.leagueLineChart);

        databaseHelper = new DatabaseHelper(AnalyzeScoresGraphsActivity.this);

        //Add database values to ArrayList
        scores = databaseHelper.getAllScores();

        int numScores = scores.size() / 7;

        for (int i = 0; i < numScores; i++) {

            entries.add(new Entry(i, Integer.parseInt((String) scores.get(7 * i + 2))));

        }

        LineDataSet dataSet = new LineDataSet(entries, "League Scores");
        LineData lineData = new LineData(dataSet);

        leagueLineChart.setData(lineData);
        leagueLineChart.invalidate();

    }

}