package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clickyClicky;
    private Button linkCollector;
    private Button aboutMe;
    private Button locator;
    private Button web;

//    public void toastMsg(String msg){
//        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
//    public void displayToastMsg(View v){
//        toastMsg("Xuening Wei wei.xu@northeastern.edu");
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("subHead+editText.getText().toString()");
        clickyClicky = (Button)findViewById(R.id.click);
        clickyClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openActivity2(); }
        });

        linkCollector = (Button) findViewById(R.id.linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        aboutMe = (Button) findViewById(R.id.aboutMe);
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("my app", "i'm here");
                openActivity4();
            }
        });

        locator = (Button) findViewById(R.id.locator);
        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        web = (Button) findViewById(R.id.webService);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, ClickyActivity.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }
    public void openActivity4(){
        Intent intent = new Intent(this, AboutMe.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent = new Intent(this,Locator.class);
        startActivity(intent);
    }
    public void openActivity6(){
        Intent intent = new Intent(this,WebService.class);
        startActivity(intent);
    }

}