package com.example.bowlerspocketbook;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AnalyzeScoresMain extends TabActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_scores_main);

        Resources resources = getResources();
        TabHost tabHost = getTabHost();

        //Scores list activity
        Intent scoresListActivityIntent = new Intent().setClass(this, AnalyzeScoresActivity.class);
        TabSpec tabSpecAnalyzeScores = tabHost.newTabSpec("Scores").setIndicator("Scores").setContent(scoresListActivityIntent);

        //Scores metrics activity
        Intent scoresMetricsActivityIntent = new Intent().setClass(this, AnalyzeScoresMetricsActivity.class);
        TabSpec tabSpecAnalyzeScoresMetrics = tabHost.newTabSpec("Metrics").setIndicator("Metrics").setContent(scoresMetricsActivityIntent);

        //Scores graphs activity
        Intent scoresGraphsActivityIntent = new Intent().setClass(this, AnalyzeScoresGraphsActivity.class);
        TabSpec tabSpecAnalyzeScoresGraphs = tabHost.newTabSpec("Graphs").setIndicator("Graphs").setContent(scoresGraphsActivityIntent);

        tabHost.addTab(tabSpecAnalyzeScores);
        tabHost.addTab(tabSpecAnalyzeScoresMetrics);
        tabHost.addTab(tabSpecAnalyzeScoresGraphs);

        tabHost.setCurrentTab(0);
    }
}



