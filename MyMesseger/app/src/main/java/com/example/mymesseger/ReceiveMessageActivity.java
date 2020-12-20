package com.example.mymesseger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {
    public static  final String EXTRA_MESSAGE="message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        setText();
    }

    private void setText(){
        Intent intent = getIntent();
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView)findViewById(R.id.message);
        textView.setText(text);
    }
}