package com.example.bowlerspocketbook;

import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import android.widget.ImageButton;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;

public class FindABallActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    List<BowlingBall> bowlingBalls;
    ArrayList<String> brandList;
    ArrayList<Integer> ballImages;
    ArrayList<String> ballNameList;
    ArrayList<String> coreList;
    ArrayList<Float> rgList;
    ArrayList<Float> diffList;
    ArrayList<String> coverList;

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;

    TextView toolbarTitle;

    ImageButton activityMenuBtn;
    ImageButton filterMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_ball);

        activityMenuBtn = (ImageButton) findViewById(R.id.menu_button);

        activityMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu activityMenuPopup = new PopupMenu(FindABallActivity.this, v);
                activityMenuPopup.setOnMenuItemClickListener(FindABallActivity.this);
                activityMenuPopup.inflate(R.menu.activity_menu);
                activityMenuPopup.show();
            }
        });

        filterMenuBtn = (ImageButton) findViewById(R.id.filter_button);

        filterMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu filterMenuPopup = new PopupMenu(FindABallActivity.this, v);
                filterMenuPopup.setOnMenuItemClickListener(FindABallActivity.this);


//                filterMenuPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//
//                        item.setChecked(!item.isChecked());
//
//                        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
////                        item.setActionView(new View(context));
//                        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
//                            @Override
//                            public boolean onMenuItemActionExpand(MenuItem item) {
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onMenuItemActionCollapse(MenuItem item) {
//                                return false;
//                            }
//                        });
////                        filterMenuPopup.show();
//                        return false;
//                    }
//                });


                filterMenuPopup.inflate(R.menu.filter_menu);
                filterMenuPopup.show();
            }
        });

        toolbarTitle = (TextView) findViewById(R.id.toolbar_filter_title);
        toolbarTitle.setText("Find a Ball");

        brandList = new ArrayList<String>();
        ballImages = new ArrayList<Integer>();
        ballNameList = new ArrayList<String>();
        coreList = new ArrayList<String>();
        rgList = new ArrayList<Float>();
        diffList = new ArrayList<Float>();
        coverList = new ArrayList<String>();

        bowlingBalls = new ArrayList<>();
        readBowlingBallData();

        int numBalls = bowlingBalls.size();

        for (int i = 0; i < numBalls; i++) {
//            brandList.add(bowlingBalls.get(7 * i + 1));
//            ballNameList.add(bowlingBalls.get(7 * i));
//            coreList.add(bowlingBalls.get(7 * i + 2));
//            rgList.add(bowlingBalls.get(7 * i + 5));
//            diffList.add(bowlingBalls.get(7 * i + 4));
//            coverList.add(bowlingBalls.get(7 * i + 3));
//
//            ballImages.add(R.drawable.nuclear_cell);

            brandList.add(bowlingBalls.get(i).getBrand());
            ballNameList.add(bowlingBalls.get(i).getBallName());
            coreList.add(bowlingBalls.get(i).getCore());
            rgList.add(bowlingBalls.get(i).getRg());
            diffList.add(bowlingBalls.get(i).getDiff());
            coverList.add(bowlingBalls.get(i).getCoverstock());

//            ballImages.add(R.drawable.nuclear_cell);

            String currBall = bowlingBalls.get(i).getBallName();
            String ball = "";

            String[] ballArray = currBall.split(" ", -1);

//            if (ballArray[ballArray.length - 1].equals("-")) {
//                ballArray.remove(ballArray.length - 1);
//            }

            for (int j = 0; j < ballArray.length; j++) {

                if (j == 0) {
                    ball += ballArray[j].toLowerCase().replace("/", "_").replace("-", "_").replace(".", "_").replace("'", "_").replace("!", "_");
                } else {
                    ball += "_" + ballArray[j].toLowerCase().replace("/", "_").replace("-", "_").replace(".", "_").replace("'", "_").replace("!", "_");
                }
            }

            int resId = getResources().getIdentifier(ball, "drawable", getPackageName());

            ballImages.add(resId);
        }

        recyclerView = findViewById(R.id.ballsRV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        programAdapter = new FindABallAdapter(this, brandList, ballImages, ballNameList, coreList, rgList, diffList, coverList);
        recyclerView.setAdapter(programAdapter);
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

    public void openBuildArsenalActivity() {
        Intent intent = new Intent(this, BuildArsenalPreferencesActivity.class);
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
                openBuildArsenalActivity();
                return true;
            case R.id.goToFindABall:
                return false;
            case R.id.goToLogScores:
                openLogScoresActivity();
                return true;
            case R.id.goToAnalyzeScores:
                openAnalyzeScoresActivity();
                return true;
            case R.id.filterByBrand:
                return false;
            case R.id.filter900Global:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterBrunswick:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterColumbia300:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterDV8:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterEbonite:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterHammer:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterMotiv:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterRadical:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterRotoGrip:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterStorm:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            case R.id.filterTrack:
                item.setChecked(!item.isChecked());

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//                        item.setActionView(new View(context));
                MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });
//                        filterMenuPopup.show();
                return false;
            default:
                return false;
        }
    }

//    public boolean checkBoxClick(MenuItem item) {
//        item.setChecked(!item.isChecked());
//        return false;
//    }
}