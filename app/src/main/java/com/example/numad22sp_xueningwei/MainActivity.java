package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clickyClicky;
    private Button linkCollector;
    private Button aboutMe;

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
        clickyClicky = (Button)findViewById(R.id.button2);
        clickyClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();

            }
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
                openActivity4();
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

}