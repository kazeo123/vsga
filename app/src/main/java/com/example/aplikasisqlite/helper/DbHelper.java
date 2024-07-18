package com.example.aplikasisqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "digitaltalent.db";

    public static final String TABLE_SQLite = "sqlite";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_JENIS = "jenis";
    public static final String COLUMN_ADDRESS = "address";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_TANGGAL + " TEXT NOT NULL, " + COLUMN_JENIS + " TEXT NOT NULL, " + COLUMN_ADDRESS
                + " TEXT NOT NULL " +   ")";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public void insert(String name, String address, String jenis, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (name,tanggal,jenis,address)" + "VALUES ('" + name + "','" + tanggal +"','"+ jenis +"','"+address+"')";
        Log.e("insert sqlite", "" + queryValues);
        db.execSQL(queryValues);
        db.close();
    }

    public void update(int id, String name, String address, String jenis, String tanggal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TANGGAL, tanggal);
        values.put(COLUMN_JENIS, jenis);
        values.put(COLUMN_ADDRESS, address);
        db.update(TABLE_SQLite, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String udpateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite",udpateQuery);
        db.execSQL(udpateQuery);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                map.put("name", cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                map.put("tanggal", cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL)));
                map.put("jenis", cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JENIS)));
                map.put("address", cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return wordList;
    }
}
