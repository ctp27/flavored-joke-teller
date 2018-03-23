package com.ctp.joke_display_lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String INTENT_JOKE_STRING_EXTRA = "joke-extra-haha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        /*  Display back button */
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView jokeDisplayView = findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        if(intent.hasExtra(INTENT_JOKE_STRING_EXTRA)){
            jokeDisplayView.setText(intent.getStringExtra(INTENT_JOKE_STRING_EXTRA));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
