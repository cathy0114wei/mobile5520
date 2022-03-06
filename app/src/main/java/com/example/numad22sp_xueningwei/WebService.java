package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebService extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";
    private EditText editText;
    private Button button;
    private ProgressBar progressBar;
    private static final String subHead = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    //private Handler handler;
    private String url;
    private JSONObject jsonObject;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        editText = (EditText) findViewById(R.id.enter_search);
        button = (Button) findViewById(R.id.fetch);
        progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.INVISIBLE);
        //progressBar.setMax(100);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(progressBar.getVisibility()==View.GONE){
                progressBar.setVisibility(View.VISIBLE);
                getUrl(view);
            }
        });
//        MyAndroidThread myTask = new MyAndroidThread(WebService.this);
//        Thread t1 = new Thread(myTask, "startWebService");
//        t1.start();
    }

    private void getUrl(View view) {
        //PingWebServiceTask task = new PingWebServiceTask();
        try {
            url = NetworkUtil.validInput(subHead + editText.getText().toString());
            getWebService();
        } catch (NetworkUtil.MyException e) {
            Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

//    ExecutorService executor = Executors.newSingleThreadExecutor();
//    Handler handler = new Handler(Looper.getMainLooper());
    private void getWebService(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here

                getJsonObject();
                display();
                progressBar.setVisibility(View.INVISIBLE);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here

                    }
                });
            }
        });
    }

    private void getJsonObject() {
        Log.i(url, "fetching.............................");
        try {
            URL url_ = new URL(url);
            String resp = NetworkUtil.httpResponse(url_);
            JSONObject jObject = new JSONObject(resp);
            JSONArray jArray = jObject.getJSONArray("meals");
            jsonObject = jArray.getJSONObject(0);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(TAG, "JSONException");
            e.printStackTrace();
        }
        Log.i(url, "jsonObject get.............................");
    }

    private void display() {
        //super.onPostExecute(jObject);
        TextView result_view = (TextView) findViewById(R.id.result_view);
        try {

            result_view.setText(jsonObject.getString("strInstructions"));
        } catch (JSONException e) {
            result_view.setText("Something went wrong!");
        }

    }

//        MyAndroidThread myTask = new MyAndroidThread(WebService.this);
//        Thread t1 = new Thread(myTask, "Bajrang");
//        t1.start();

//
//    class MyAndroidThread implements Runnable
//    {
//        Activity activity;
//        public MyAndroidThread(Activity activity)
//        {
//            this.activity = activity;
//        }
//        @Override
//        public void run()
//        {
//
//            //perform heavy task here and finally update the UI with result this way -
//            activity.runOnUiThread(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    TextView result_view = (TextView) findViewById(R.id.result_view);
//                    try {
//
//                        result_view.setText(jsonObject.getString("strInstructions"));
//                    } catch (JSONException e) {
//                        result_view.setText("Something went wrong!");
//                    }
//                    Log.i(url, "display successfully.............................");                }
//            });
//        }
//    }
//    private void display(JSONObject jsonObject){
//        TextView result_view = (TextView) findViewById(R.id.result_view);
//        try {
//
//            result_view.setText(jsonObject.getString("strInstructions"));
//        } catch (JSONException e) {
//            result_view.setText("Something went wrong!");
//        }
//        Log.i(url, "display successfully.............................");
//    }


//    private JSONObject execute(String params) {
//        //Toast.makeText(WebService.this, "doInBackground", Toast.LENGTH_SHORT).show();
//        JSONObject resObject = new JSONObject();
//        try {
//            URL url = new URL(params);
//
//            String resp = NetworkUtil.httpResponse(url);
//            //Log.i(resp,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//
//            JSONObject jObject = new JSONObject(resp);
//            JSONArray jArray = jObject.getJSONArray("meals");
//            resObject = jArray.getJSONObject(0);
//            return resObject;
//        } catch (MalformedURLException e) {
//            Log.e(TAG, "MalformedURLException");
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            Log.e(TAG, "ProtocolException");
//            e.printStackTrace();
//        } catch (IOException e) {
//            Log.e(TAG, "IOException");
//            e.printStackTrace();
//        } catch (JSONException e) {
//            Log.e(TAG, "JSONException");
//            e.printStackTrace();
//        }
//
//        return resObject;
//    }
//
//


//    public class PingWebServiceTask{
//
//        //private ExecutorService executorService;
//        private void fetch(String url){
//            Log.i(url, "fetching.............................");
//            JSONObject jsonObject = execute(url);
//            Log.i(url, "jsonObject get.............................");
//            display(jsonObject);
//            Log.i(url, "display successfully.............................");
//        }
//        private JSONObject execute(String params) {
//            //Toast.makeText(WebService.this, "doInBackground", Toast.LENGTH_SHORT).show();
//            JSONObject resObject = new JSONObject();
//            try {
//                URL url = new URL(params);
//
//                String resp = NetworkUtil.httpResponse(url);
//                //Log.i(resp,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//
//                JSONObject jObject = new JSONObject(resp);
//                JSONArray jArray = jObject.getJSONArray("meals");
//                resObject = jArray.getJSONObject(0);
//                return resObject;
//            } catch (MalformedURLException e) {
//                Log.e(TAG,"MalformedURLException");
//                e.printStackTrace();
//            } catch (ProtocolException e) {
//                Log.e(TAG,"ProtocolException");
//                e.printStackTrace();
//            } catch (IOException e) {
//                Log.e(TAG,"IOException");
//                e.printStackTrace();
//            } catch (JSONException e) {
//                Log.e(TAG,"JSONException");
//                e.printStackTrace();
//            }
//
//            return resObject;
//        }
//        private void display(JSONObject jObject) {
//            //super.onPostExecute(jObject);
//            TextView result_view = (TextView)findViewById(R.id.result_view);
//            try {
//
//                result_view.setText(jObject.getString("strInstructions"));
//            } catch (JSONException e) {
//                result_view.setText("Something went wrong!");
//            }
//
//        }
//    }

//    private void performProgressBar(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })
//    }
}