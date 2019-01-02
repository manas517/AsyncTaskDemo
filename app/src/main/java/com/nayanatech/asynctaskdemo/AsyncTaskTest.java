package com.nayanatech.asynctaskdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AsyncTaskTest extends AsyncTask<String,String,String> {
    String resp;
    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
