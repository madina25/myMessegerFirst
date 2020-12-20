package com.example.notes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewNote extends AppCompatActivity {
    EditText noteBox;
    Button delButton;
    Button saveButton;

    DataBaseNote sqlHelper;
    SQLiteDatabase db;
    Cursor noteCursor;
    long noteId=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);

        noteBox = (EditText) findViewById(R.id.note);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        sqlHelper = new DataBaseNote(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteId = extras.getLong("id");
        }
        if (noteId > 0) {
            noteCursor = db.rawQuery("select * from " + DataBaseNote.TABLE + " where " +
                    DataBaseNote.COLUMN_ID + "=?", new String[]{String.valueOf(noteId)});
            noteCursor.moveToFirst();
            noteBox.setText(noteCursor.getString(1));
            noteCursor.close();
        } else {
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseNote.COLUMN_NOTE, noteBox.getText().toString());

        if (noteId > 0) {
            db.update(DataBaseNote.TABLE, cv, DataBaseNote.COLUMN_ID + "=" + String.valueOf(noteId), null);
        } else {
            db.insert(DataBaseNote.TABLE, null, cv);
        }
        goPage();
    }
    public void delete(View view){
        db.delete(DataBaseNote.TABLE, "_id = ?", new String[]{String.valueOf(noteId)});
        ModalDialog dialog = new ModalDialog();
        dialog.show(getSupportFragmentManager(), "modalDialog");
        goPage();
    }
    private void goPage(){
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
