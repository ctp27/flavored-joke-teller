package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ctp.joke_display_lib.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.asynctasks.GetJokeTask;


public class MainActivity extends AppCompatActivity
                implements GetJokeTask.GetJokeTaskCallback{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
//        Toast.makeText(this, new JokeWizard().tellJoke(), Toast.LENGTH_LONG).show();
//

        new GetJokeTask(this).execute(new Pair<Context, String>(this,"joke"));




    }


    @Override
    public void onJokeRetrieved(String joke) {
        if(TextUtils.isEmpty(joke)){
            return;
        }

        Log.d(TAG,"Joke is "+joke);
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.INTENT_JOKE_STRING_EXTRA,joke);
        startActivity(intent);

    }
}
