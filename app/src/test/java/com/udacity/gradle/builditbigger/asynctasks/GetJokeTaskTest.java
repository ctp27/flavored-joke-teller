package com.udacity.gradle.builditbigger.asynctasks;


import android.test.InstrumentationTestCase;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class GetJokeTaskTest extends InstrumentationTestCase {


    private String jokeString;

    @Test
    public void testGetJokeTask() throws Throwable{

        final CountDownLatch signal = new CountDownLatch(1);
        final GetJokeTask getJokeTask = new GetJokeTask();


        getJokeTask.setmCallback(new GetJokeTask.GetJokeTaskCallback() {
            @Override
            public void onJokeRetrieved(String joke) {
                jokeString = joke;
                signal.countDown();
            }
        });

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                getJokeTask.execute("joke");
            }
        });
        try {
            signal.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertNotNull(jokeString);
        Assert.assertTrue(!jokeString.isEmpty());

    }


}