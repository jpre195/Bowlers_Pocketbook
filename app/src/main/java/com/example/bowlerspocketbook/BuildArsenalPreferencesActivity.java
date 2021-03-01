package com.example.bowlerspocketbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;

public class BuildArsenalPreferencesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner playStyleSpinner;
    Spinner releasePositionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_arsenal_preferences);

        playStyleSpinner = findViewById(R.id.playStyleSpinner);
        ArrayAdapter<CharSequence> playStyleAdapter = ArrayAdapter.createFromResource(this, R.array.play_style_array, android.R.layout.simple_spinner_item);
        playStyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playStyleSpinner.setAdapter(playStyleAdapter);
        playStyleSpinner.setOnItemSelectedListener(this);

        releasePositionSpinner = findViewById(R.id.releasePositionSpinner);
        ArrayAdapter<CharSequence> releasePositionAdapter = ArrayAdapter.createFromResource(this, R.array.release_position_array, android.R.layout.simple_spinner_item);
        releasePositionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        releasePositionSpinner.setAdapter(releasePositionAdapter);
        releasePositionSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}