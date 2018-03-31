package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ctp.joke_display_lib.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.asynctasks.GetJokeTask;

public class MainActivity extends AppCompatActivity
        implements GetJokeTask.GetJokeTaskCallback,
        MainActivityFragment.MainActivityFragmentCallback{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BUNDLE_JOKE_STRING = "bundle-joke";



   private ProgressBar progressBar;
   private String theJoke;
   private RelativeLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        mainLayout = findViewById(R.id.rel_layout);

        if(savedInstanceState!=null){
            if(savedInstanceState.containsKey(BUNDLE_JOKE_STRING)){
                theJoke = savedInstanceState.getString(BUNDLE_JOKE_STRING);
            }
        }

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

        showProgressBar();
        new GetJokeTask(this).execute("joke");


    }


    @Override
    public void onJokeRetrieved(String joke) {

        hideProgressBar();
        MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.loadInterstitalAd();


        theJoke = joke;
        Log.d(TAG,"Joke is "+joke);

    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAdClosed() {
        if(TextUtils.isEmpty(theJoke)){
            Snackbar.make(mainLayout,"An error occured",Snackbar.LENGTH_LONG);
        }
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.INTENT_JOKE_STRING_EXTRA,theJoke);
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_JOKE_STRING,theJoke);
    }
}


