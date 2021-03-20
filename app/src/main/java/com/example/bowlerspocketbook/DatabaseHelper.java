package com.example.bowlerspocketbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Initialize database
    private static final String DATABASE_NAME = "bowlers_pocketbook_db";
    private static final String TABLE_NAME = "scores";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        String createTable = "create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY, Event TEXT, Ball TEXT, Score INTEGER, Game INTEGER, Game_Day INTEGER, Game_Month INTEGER, Game_Year INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = "drop table if exists " + TABLE_NAME;
        db.execSQL(deleteTable);
    }

    public void resetScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    public boolean addGame(String event, String ball, int score, int game, int day, int month, int year) {
        //Get writeable database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put("Event", event);
        contentValues.put("Ball", ball);
        contentValues.put("Score", score);
        contentValues.put("Game", game);
        contentValues.put("Game_Day", day);
        contentValues.put("Game_Month", month);
        contentValues.put("Game_Year", year);

        //Add values into database
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList getAllScores() {
        //Get Readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();

        //Create cursor to select all values
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " order by Game_Year desc, Game_Month desc, Game_Day desc, Event, Game desc", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("Event")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Ball")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Score")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Day")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Month")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Year")));
            cursor.moveToNext();
        }

        return arrayList;
    }

    public ArrayList getScores(String eventType) {
        //Get Readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();

        //Create cursor to select all values
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where Event = '" + eventType + "' order by Game_Year desc, Game_Month desc, Game_Day desc, Event, Game desc", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("Event")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Ball")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Score")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Day")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Month")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Year")));
            cursor.moveToNext();
        }

        return arrayList;
    }

    public ArrayList getSeries() {
        //Get Readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();

        //Create cursor to select all values
        Cursor cursor = sqLiteDatabase.rawQuery("select Event, Game_Day, Game_Month, Game_Year, sum(Score) Series from " + TABLE_NAME + " where Event <> 'Practice' group by Event, Game_Day, Game_Month, Game_Year order by Game_Year desc, Game_Month desc, Game_Day desc, Event", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("Event")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Day")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Month")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Game_Year")));
            arrayList.add(cursor.getString(cursor.getColumnIndex("Series")));
            cursor.moveToNext();
        }

        return arrayList;
    }
}
