package com.example.notes;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DataBaseNote extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notesDB.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "notes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "name";

    public DataBaseNote(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE notes (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE
                + " TEXT);");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NOTE
                + ") VALUES ('Заметка 1');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}