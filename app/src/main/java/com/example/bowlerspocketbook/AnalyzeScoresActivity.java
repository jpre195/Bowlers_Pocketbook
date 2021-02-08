package com.example.bowlerspocketbook;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

            if (i == 1) {
                ballImages.add(R.drawable.all_road);
            } else {
                ballImages.add(R.drawable.nuclear_cell);
            }
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