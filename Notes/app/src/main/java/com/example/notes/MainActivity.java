package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView notesList;
    TextView header;
    DataBaseNote DataBaseNote;
    SQLiteDatabase db;
    Cursor noteCursor;
    SimpleCursorAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = (TextView) findViewById(R.id.header);
        notesList = (ListView) findViewById(R.id.list);

        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewNote.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        DataBaseNote = new DataBaseNote(getApplicationContext());
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, NewNote.class);
        startActivity(intent);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), NewNote.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        db = DataBaseNote.getReadableDatabase();
        noteCursor = db.rawQuery("select * from " + DataBaseNote.TABLE, null);
        String[] headers = new String[]{DataBaseNote.COLUMN_NOTE};
        noteAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                noteCursor, headers, new int[]{android.R.id.text1}, 0);
        header.setText("Count: " + String.valueOf(noteCursor.getCount()));
        notesList.setAdapter(noteAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        noteCursor.close();
    }
}