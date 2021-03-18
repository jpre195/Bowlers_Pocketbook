package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.io.*;

public class AnalyzeScoresMetricsActivity extends AppCompatActivity {

    TextView highGameTextView;
    TextView highSeriesTextView;
    TextView practiceAverageTextView;
    TextView leagueAverageTextView;
    TextView tournamentAverageTextView;

    ImageView mostUsedBallImageView;

    DatabaseHelper databaseHelper;
    ArrayList scores;

    ArrayList<Integer> scoresList = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_scores_metrics);

        highGameTextView = (TextView) findViewById(R.id.highGameActualTextView);
        highSeriesTextView = (TextView) findViewById(R.id.highSeriesActualTextView);
        practiceAverageTextView = (TextView) findViewById(R.id.practiceAverageActualTextView);
        leagueAverageTextView = (TextView) findViewById(R.id.leagueAverageActualTextView);
        tournamentAverageTextView = (TextView) findViewById(R.id.tournamentAverageActualTextView);

        mostUsedBallImageView = (ImageView) findViewById(R.id.mostUsedBall);

        //Get high game
        //Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(AnalyzeScoresMetricsActivity.this);

        //Add database values to ArrayList
        scores = databaseHelper.getAllScores();

        setHighGame(scores);
        setPracticeAverage(scores);
        setLeagueAverage(scores);
        setTournamentAverage(scores);
        setMostUsedBall(scores);
    }

    private void setPracticeAverage(ArrayList scores) {
        int numScores = scores.size() / 7;

        ArrayList<Integer> practiceScoresList = new ArrayList<Integer>();

        for (int i = 0; i < numScores; i++) {
            String event = (String) scores.get(7 * i);

            if (event.equals("Practice")) {
                practiceScoresList.add(Integer.parseInt((String) scores.get(7 * i + 2)));
                Log.d("Practice game added", (String) scores.get(7 * i + 2));
            }
        }

        Log.d("Practice game size", String.valueOf(practiceScoresList.size()));

        if (practiceScoresList.size() > 0) {

            int practiceSum = 0;

            for (int i = 0; i < practiceScoresList.size(); i++) {
                practiceSum += practiceScoresList.get(i);
            }

            int practiceAverage = practiceSum / practiceScoresList.size();

            practiceAverageTextView.setText(String.valueOf(practiceAverage));
        }
    }

    private void setLeagueAverage(ArrayList scores) {
        int numScores = scores.size() / 7;

        ArrayList<Integer> leagueScoresList = new ArrayList<Integer>();

        for (int i = 0; i < numScores; i++) {
            String event = (String) scores.get(7 * i);
            String test = "League";

            if (event.equals(test)) {
                leagueScoresList.add(Integer.parseInt((String) scores.get(7 * i + 2)));
            }
        }

        if (leagueScoresList.size() > 0) {

            int leagueSum = 0;

            for (int i = 0; i < leagueScoresList.size(); i++) {
                leagueSum += leagueScoresList.get(i);
            }

            int leagueAverage = leagueSum / leagueScoresList.size();

            leagueAverageTextView.setText(String.valueOf(leagueAverage));
        }
    }

    private void setTournamentAverage(ArrayList scores) {
        int numScores = scores.size() / 7;

        ArrayList<Integer> tournamentScoresList = new ArrayList<Integer>();

        for (int i = 0; i < numScores; i++) {
            String event = (String) scores.get(7 * i);

            if (event.equals("Tournament")) {
                tournamentScoresList.add(Integer.parseInt((String) scores.get(7 * i + 2)));
            }
        }

        if (tournamentScoresList.size() > 0) {

            int tournamentSum = 0;

            for (int i = 0; i < tournamentScoresList.size(); i++) {
                tournamentSum += tournamentScoresList.get(i);
            }

            int tournamentAverage = tournamentSum / tournamentScoresList.size();

            tournamentAverageTextView.setText(String.valueOf(tournamentAverage));
        }
    }

    private void setHighGame(ArrayList scores) {
        int numScores = scores.size() / 7;

        if (numScores > 0) {

            for (int i = 0; i < numScores; i++) {
                scoresList.add(Integer.parseInt((String) scores.get(7 * i + 2)));
            }

            List<Integer> scoresListSorted = new ArrayList<>(scoresList);

            Collections.sort(scoresListSorted);

            int highGame = scoresListSorted.get(scoresListSorted.size() - 1);

            highGameTextView.setText(String.valueOf(highGame));

        }
    }

    private void setMostUsedBall(ArrayList scores) {
        int numScores = scores.size() / 7;

        ArrayList<String> ballsUsed = new ArrayList<String>();

        if (numScores > 0) {

            for (int i = 0; i< numScores; i++) {

                ballsUsed.add((String) scores.get(7 * i + 1));

            }

            String mostUsedBall = getMostCommonElement(ballsUsed);

            String[] ballArray = mostUsedBall.split(" ", -1);
            String ball = "";

            for (int j = 0; j < ballArray.length; j++) {

                if (j == 0) {
                    ball += ballArray[j].toLowerCase();
                } else {
                    ball += "_" + ballArray[j].toLowerCase();
                }
            }

            int resId = getResources().getIdentifier(ball, "drawable", getPackageName());

            mostUsedBallImageView.setImageResource(resId);
        }
    }

    private String getMostCommonElement(ArrayList<String> list) {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                map.put(list.get(i), map.get(list.get(i)) + 1);
            } else {
                map.put(list.get(i), 1);
            }
        }

        int max = 0;
        String mostUsedBall = "";

        for (String key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                mostUsedBall = key;
            }
        }

        return mostUsedBall;

    }
}