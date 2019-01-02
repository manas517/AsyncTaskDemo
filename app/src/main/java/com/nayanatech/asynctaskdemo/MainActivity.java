package com.nayanatech.asynctaskdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nayanatech.asynctaskdemo.Network.ApiClient;
import com.nayanatech.asynctaskdemo.Network.ApiInterface;
import com.nayanatech.asynctaskdemo.model.UnknownModel;
import com.nayanatech.asynctaskdemo.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     private Button button;
     private EditText time;
     private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn_run);
        time=findViewById(R.id.in_time);
        finalResult=findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AsyncTaskTest asyncTaskTest=new AsyncTaskTest();
                //String sleeptime=time.getText().toString();
               // asyncTaskTest.execute(sleeptime);

               //networkCallForUnknown();

                networkCallForUserInfo();
            }
        });
    }

    private void networkCallForUserInfo() {
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<User> call=apiInterface.createUser(new User("","Android Developer"));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                finalResult.setText(user.id+"\n"+user.name+"\n"+user.job+"\n"+user.createdAt);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void networkCallForUnknown() {
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<UnknownModel> call=apiInterface.getUnknownList();
        call.enqueue(new Callback<UnknownModel>() {
            @Override
            public void onResponse(Call<UnknownModel> call, Response<UnknownModel> response) {

                UnknownModel unknownModel=new UnknownModel();
                unknownModel=response.body();
                finalResult.setText(unknownModel.getTotalPages().toString());
            }

            @Override
            public void onFailure(Call<UnknownModel> call, Throwable t) {
                call.cancel();
            }
        });

    }

    private class AsyncTaskTest extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");
        }

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            finalResult.setText(result);
        }



        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}

