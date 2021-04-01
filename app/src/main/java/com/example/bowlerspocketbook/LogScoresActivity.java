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
import android.widget.DatePicker;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.util.Log;
import android.content.Context;
import androidx.appcompat.widget.PopupMenu;
import android.widget.ImageButton;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Calendar;
import java.io.*;
import java.nio.charset.Charset;

public class LogScoresActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    //Initialize variables
    Spinner spinner;
    Spinner ballUsedSpinner;
//    EditText ballEditText;
    EditText scoreEditText;
    EditText gameEditText;
    EditText gameDateEditText;
//    ListView scoreListView;
    Button logScoresBtn;
    Button analyzeScoresBtn;
    Button resetScoresBtn;
    DatePickerDialog gameDate;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    List<BowlingBall> bowlingBalls;

    ImageButton activityMenuBtn;

    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_scores);

        activityMenuBtn = (ImageButton) findViewById(R.id.menu_button);

        activityMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu activityMenuPopup = new PopupMenu(LogScoresActivity.this, v);
                activityMenuPopup.setOnMenuItemClickListener(LogScoresActivity.this);
                activityMenuPopup.inflate(R.menu.activity_menu);
                activityMenuPopup.show();
            }
        });

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Log Scores");

        spinner = findViewById(R.id.eventTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        gameDate = (DatePicker)findViewById(R.id.gameDatePicker);

//        databaseHelper.onCreate();

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
        gameDateEditText = findViewById(R.id.gameDateEditText);
//        scoreListView = findViewById(R.id.scoresListView);
        logScoresBtn = findViewById(R.id.logScoresBtn);
        analyzeScoresBtn = findViewById(R.id.analyzeScoresBtn2);
        resetScoresBtn = findViewById(R.id.resetScoresBtn);

        //Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(LogScoresActivity.this);

        //Add database values to ArrayList
        arrayList = databaseHelper.getAllScores();

        //Initialize ArrayAdapter
        arrayAdapter = new ArrayAdapter(LogScoresActivity.this, android.R.layout.simple_list_item_1, arrayList);

        gameDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                gameDate = new DatePickerDialog(LogScoresActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                gameDateEditText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                gameDate.show();

            }
        });

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
//                String date = gameDate.getDayOfMonth() + "/" + (gameDate.getMonth() + 1) + "/" + gameDate.getYear();

                String[] ballArray = ball.split(" ", -1);

                //Remove ball brand from ballArray
                String currBrand;

                int brandLength = 0;

                Context context = getApplicationContext();
                String[] brandsArray = context.getResources().getStringArray(R.array.ball_brands_array);

                for (int i = 0; i < brandsArray.length; i++) {
                    currBrand = brandsArray[i];
                    if (ballArray[0].equals(currBrand)) {
                        brandLength = 1;
                    } else if ((ballArray[0] + ' ' + ballArray[1]).equals(brandsArray[i])) {
                        brandLength = 2;
                    }
                }

                String ballCleaned = "";

                for (int i = brandLength; i < ballArray.length; i++) {

                    if (i == brandLength) {
                        ballCleaned += ballArray[i];
                    } else {
                        ballCleaned += ' ' + ballArray[i];
                    }
                }

                if (!gameDateEditText.getText().toString().isEmpty() & !eventType.isEmpty() & !ball.isEmpty() & !scoreEditText.getText().toString().isEmpty() & !gameEditText.getText().toString().isEmpty()) {
                    String[] gameDateArray = gameDateEditText.getText().toString().split("/", -1);
                    int month = Integer.parseInt(gameDateArray[0]);
                    int day = Integer.parseInt(gameDateArray[1]);
                    int year = Integer.parseInt(gameDateArray[2]);

                    databaseHelper.addGame(eventType, ballCleaned, score, game, day, month, year);
//                    databaseHelper.addGame(eventType, ballCleaned, score, game);

//                    ballEditText.setText("");
                    scoreEditText.setText("");
                    gameEditText.setText("");

                    //Display Success message
                    Toast.makeText(getApplicationContext(), "Score logged!", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

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

        resetScoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScores();
            }
        });
    }

    public void resetScores() {
        databaseHelper.resetScores();

        Toast.makeText(getApplicationContext(), "Scores reset", Toast.LENGTH_SHORT).show();
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

    public void openBuildArsenalActivity() {
        Intent intent = new Intent(this, BuildArsenalPreferencesActivity.class);
        startActivity(intent);
    }

    public void openAnalyzeScoresActivity() {
        Intent intent = new Intent(this, AnalyzeScoresMain.class);
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
                openBuildArsenalActivity();
                return true;
            case R.id.goToFindABall:
                openFindABallActivity();
                return true;
            case R.id.goToLogScores:
                return false;
            case R.id.goToAnalyzeScores:
                openAnalyzeScoresActivity();
                return true;
            default:
                return false;
        }
    }
}