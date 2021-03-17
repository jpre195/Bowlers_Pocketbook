package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
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
//        Log.d("numScores", "Test 1");
        int numScores = scores.size() / 7;
//        Log.d("numScores", "Test 2");

        for (int j = 0; j < numScores; j++) {
            scoresList.add(Integer.parseInt((String) scores.get(7 * j + 2)));
        }

        Collections.reverse(scoresList);

        for (int i = 0; i < numScores; i++) {

            entries.add(new Entry(i + 1, scoresList.get(i)));

        }

//        for (int i = numScores - 1; i >= 0; i--) {
//
//            entries.add(new Entry(i, Integer.parseInt((String) scores.get(7 * i + 2))));
//
//        }

        LineDataSet dataSet = new LineDataSet(entries, "League Scores");
//        dataSet.setDrawCubic(true);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setLineWidth(5);
        dataSet.setValueTextSize(16f);
        LineData lineData = new LineData(dataSet);

        leagueLineChart.setData(lineData);
        leagueLineChart.setAutoScaleMinMaxEnabled(true);
        leagueLineChart.setNoDataText("No games logged...");
        leagueLineChart.setBackgroundColor(16777215);
        leagueLineChart.setDrawBorders(true);
        leagueLineChart.setBorderColor(0);
        leagueLineChart.getAxisRight().setDrawGridLines(false);
        leagueLineChart.getAxisRight().setEnabled(false);
        leagueLineChart.getAxisLeft().setTextSize(24f);
        leagueLineChart.getAxisLeft().setDrawGridLines(false);
        leagueLineChart.getAxisLeft().setGranularity(1.0f);
        leagueLineChart.getAxisLeft().setGranularityEnabled(true);
        leagueLineChart.getXAxis().setDrawGridLines(false);
        leagueLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        leagueLineChart.getXAxis().setTextSize(24f);
        leagueLineChart.getXAxis().setGranularity(1.0f);
        leagueLineChart.getXAxis().setGranularityEnabled(true);
        leagueLineChart.getDescription().setEnabled(false);
        leagueLineChart.getLegend().setEnabled(false);
        leagueLineChart.setExtraOffsets(10, 10, 10, 10);
        leagueLineChart.invalidate();

    }

}