package com.udacity.gradle.builditbigger.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by clinton on 3/23/18.
 */

public class GetJokeTask extends AsyncTask<String, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private GetJokeTaskCallback mCallback;

    public GetJokeTask(GetJokeTaskCallback mCallback){
        this.mCallback = mCallback;
    }

    public GetJokeTask(){}

    public interface GetJokeTaskCallback{
        void onJokeRetrieved(String joke);
    }

    public void setmCallback(GetJokeTaskCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        String name = params[0];

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.onJokeRetrieved(result);
    }
}
