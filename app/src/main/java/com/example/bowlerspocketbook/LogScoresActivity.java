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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LogScoresActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Initialize variables
    Spinner spinner;
    Spinner ballUsedSpinner;
    EditText ballEditText;
    EditText scoreEditText;
    EditText gameEditText;
//    ListView scoreListView;
    Button logScoresBtn;
    Button analyzeScoresBtn;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_scores);

        spinner = findViewById(R.id.eventTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //TODO: Read in BowlingBallProcessed.csv file. See YouTube video
        readBowlingBallData();

        List<BowlingBall> bowlingBalls = new ArrayList<>();
        
        //getResources().openRawResource(R.raw.BowlingBallsProcessed);
        ballUsedSpinner = findViewById(R.id.ballUsedSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ballUsedSpinner.setAdapter(adapter);
        ballUsedSpinner.setOnItemSelectedListener(this);

        ballEditText = findViewById(R.id.ballUsedEditText);
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
                String ball = ballEditText.getText().toString();
                int score = Integer.parseInt(scoreEditText.getText().toString());
                int game = Integer.parseInt(gameEditText.getText().toString());

                if (!eventType.isEmpty() & !ball.isEmpty() & !scoreEditText.getText().toString().isEmpty() & !gameEditText.getText().toString().isEmpty()) {
                    databaseHelper.addGame(eventType, ball, score, game);

                    ballEditText.setText("");
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
        BowlingBall currBall;
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