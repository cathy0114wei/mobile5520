package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class WebService extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";
    private EditText editText;
    private Button button;
    private static final String subHead = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        editText = (EditText) findViewById(R.id.enter_search);
        button = (Button) findViewById(R.id.fetch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWebserviceButtonHandler(view);
            }
        });
    }
    private void callWebserviceButtonHandler(View view){
        PingWebServiceTask task = new PingWebServiceTask();
        try {
            String url = NetworkUtil.validInput(subHead+editText.getText().toString());
            //System.out.println(subHead+editText.getText().toString());
            //Toast.makeText(WebService.this, subHead+editText.getText().toString(), Toast.LENGTH_SHORT).show();
            task.execute(url);
        } catch (NetworkUtil.MyException e) {
            Toast.makeText(getApplication(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    private class PingWebServiceTask  extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(WebService.this, "onProgressUpdate", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            //Toast.makeText(WebService.this, "doInBackground", Toast.LENGTH_SHORT).show();
            JSONObject resObject = new JSONObject();
            try {
                URL url = new URL(params[0]);

                String resp = NetworkUtil.httpResponse(url);
                Log.i(resp,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

                JSONObject jObject = new JSONObject(resp);
                JSONArray jArray = jObject.getJSONArray("meals");
                resObject = jArray.getJSONObject(0);
                return resObject;

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }

            return resObject;
        }

        @Override
        protected void onPostExecute(JSONObject jObject) {
            super.onPostExecute(jObject);
            TextView result_view = (TextView)findViewById(R.id.result_view);
            try {

                result_view.setText(jObject.getString("strInstructions"));
            } catch (JSONException e) {
                result_view.setText("Something went wrong!");
            }

        }
    }
}