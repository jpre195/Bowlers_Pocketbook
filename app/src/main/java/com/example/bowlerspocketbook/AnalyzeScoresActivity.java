package com.example.bowlerspocketbook;

import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.io.*;

public class AnalyzeScoresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
//    ArrayAdapter arrayAdapter;

    //TODO change these so get data from database
//    int[] scoresList = {290, 279, 256};
//    String[] eventTypeList = {"League", "League", "League"};
//    int[] gameList = {1, 2, 3};
//    int[] ballImages = {R.drawable.omega_crux, R.drawable.omega_crux, R.drawable.omega_crux};

    ArrayList<Integer> scoresList = new ArrayList<Integer>();
    ArrayList<String> eventTypeList = new ArrayList<String>();
    ArrayList<Integer> gameList = new ArrayList<Integer>();
    ArrayList<Integer> ballImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_scores);

        //Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(AnalyzeScoresActivity.this);

        //Add database values to ArrayList
        arrayList = databaseHelper.getAllScores();

//        //Initialize ArrayAdapter
//        arrayAdapter = new ArrayAdapter(AnalyzeScoresActivity.this, android.R.layout.simple_list_item_1, arrayList);

        int numScores = arrayList.size() / 4;

        for (int i = 0; i < numScores; i++) {
            eventTypeList.add((String) arrayList.get(4 * i));
            //ballImages.add(R.drawable.nuclear_cell);
            scoresList.add(Integer.parseInt((String) arrayList.get(4 * i + 2)));
            gameList.add(Integer.parseInt((String) arrayList.get(4 * i + 3)));

            String currBall = arrayList.get(4 * i + 1).toString();
            String ball = "";

            String[] ballArray = currBall.split(" ", -1);

            for (int j = 0; j < ballArray.length; j++) {

                if (j == 0) {
                    ball += ballArray[j].toLowerCase();
                } else {
                    ball += "_" + ballArray[j].toLowerCase();
                }
            }

            int resId = getResources().getIdentifier(ball, "drawable", getPackageName());

            ballImages.add(resId);

//            if (i == 0) {
////                InputStream stream = getAssets().open("aero.jpg");
////                Drawable d = Drawable.createFromStream(stream, null);
//
////                ballImages.add(d);
//
////                int resId = getResources().getIdentifier("axiom.jpg", "drawable", getPackageName());
////                int resId = getResourceId(AnalyzeScoresActivity.this, "axiom.jpg", "drawable", getPackageName());
//                int resId = getResources().getIdentifier("axiom", "drawable", getPackageName());
//
//                ballImages.add(resId);
//
//                Toast.makeText(getApplicationContext(), String.valueOf(resId), Toast.LENGTH_LONG).show();
//
////                ballImages.add(R.drawable.all_road);
//
//            } else {
//                ballImages.add(R.drawable.nuclear_cell);
//            }
        }

//        int[] ballImages = ballImagesTemp.toArray(new int[0]);
//        int[] scoresList = scoresListTemp.toArray(new int[0]);
//        int[] gameList = gameListTemp.toArray(new int[0]);
//        String[] eventTypeList = eventTypeListTemp.toArray(new String[0]);

        recyclerView = findViewById(R.id.scoresRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        programAdapter = new AnalyzeScoresAdapter(this, eventTypeList, ballImages, gameList, scoresList);
        recyclerView.setAdapter(programAdapter);

    }
}