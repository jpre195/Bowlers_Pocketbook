package com.example.bowlerspocketbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;

public class LogScoresActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Initialize variables
    Spinner spinner;
    Spinner ballUsedSpinner;
//    EditText ballEditText;
    EditText scoreEditText;
    EditText gameEditText;
//    ListView scoreListView;
    Button logScoresBtn;
    Button analyzeScoresBtn;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    List<BowlingBall> bowlingBalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_scores);

        spinner = findViewById(R.id.eventTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //This reads in the bowling ball data
        bowlingBalls = new ArrayList<>();
        readBowlingBallData();
        
        //getResources().openRawResource(R.raw.BowlingBallsProcessed);
        ballUsedSpinner = findViewById(R.id.ballUsedSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<BowlingBall> ball_adapter = new ArrayAdapter<BowlingBall>(this, android.R.layout.simple_spinner_item, bowlingBalls);
        ballUsedSpinner.setAdapter(ball_adapter);
        ballUsedSpinner.setOnItemSelectedListener(this);

//        ballEditText = findViewById(R.id.ballUsedEditText);
        scoreEditText = findViewById(R.id.scoreEditText);
        gameEditText = findViewById(R.id.gameNumberEditText);
//        scoreListView = findViewById(R.id.scoresListView);
        logScoresBtn = findViewById(R.id.logScoresBtn);
        analyzeScoresBtn = findViewById(R.id.analyzeScoresBtn2);

        //Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(LogScoresActivity.this);

        //Add database values to ArrayList
        arrayList = databaseHelper.getAllScores();

        //Initialize ArrayAdapter
        arrayAdapter = new ArrayAdapter(LogScoresActivity.this, android.R.layout.simple_list_item_1, arrayList);

//        //Set arrayAdapter to ListView
//        scoreListView.setAdapter(arrayAdapter);

        logScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get values
                String eventType = spinner.getSelectedItem().toString();
//                String ball = ballEditText.getText().toString();
                String ball = ballUsedSpinner.getSelectedItem().toString();
                int score = Integer.parseInt(scoreEditText.getText().toString());
                int game = Integer.parseInt(gameEditText.getText().toString());

                if (!eventType.isEmpty() & !ball.isEmpty() & !scoreEditText.getText().toString().isEmpty() & !gameEditText.getText().toString().isEmpty()) {
                    databaseHelper.addGame(eventType, ball, score, game);

//                    ballEditText.setText("");
                    scoreEditText.setText("");
                    gameEditText.setText("");

                    //Display Success message
                    Toast.makeText(getApplicationContext(), "Score logged...", Toast.LENGTH_SHORT).show();

//                    arrayList.clear();
//                    arrayList.addAll(databaseHelper.getAllScores());
//
//                    //Refresh ListView
//                    arrayAdapter.notifyDataSetChanged();
//                    scoreListView.invalidateViews();
//                    scoreListView.refreshDrawableState();
                }
            }
        });

        analyzeScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnalyzeScoresActivity();
            }
        });
    }

    private void readBowlingBallData() {

        InputStream is = getResources().openRawResource(R.raw.bowling_balls_processed);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";

        try {
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");

                //Read the data
                BowlingBall currBall = new BowlingBall();
                currBall.setBallName(tokens[0]);
                currBall.setBrand(tokens[1]);
                currBall.setCore(tokens[2]);
                currBall.setCoverstock(tokens[3]);
                currBall.setDiff(Float.parseFloat(tokens[4]));
                currBall.setRg(Float.parseFloat(tokens[5]));
                currBall.setHook(Float.parseFloat(tokens[6]));
                bowlingBalls.add(currBall);

            }

        } catch(IOException e) {
                Log.wtf("LogScoresActivity", "Error reading data file on line " + line, e);
                e.printStackTrace();
            }
        }

    public void openAnalyzeScoresActivity() {
        Intent intent = new Intent(this, AnalyzeScoresActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}