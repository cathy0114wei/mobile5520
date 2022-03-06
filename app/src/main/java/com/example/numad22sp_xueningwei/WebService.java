package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WebService extends AppCompatActivity {

    private EditText editText;
    private Button button;
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
            String url = NetworkUtil.validInput(editText.getText().toString());
            task.execute(url); // This is a security risk.  Don't let your user enter the URL in a real app.
        } catch (NetworkUtil.MyException e) {
            Toast.makeText(getApplication(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}