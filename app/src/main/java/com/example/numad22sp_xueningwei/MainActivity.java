package com.example.numad22sp_xueningwei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clickyClicky;
//    private Button btn1 = (Button) findViewById(R.id.button1);

    public void toastMsg(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
    public void displayToastMsg(View v){
        toastMsg("Xuening Wei wei.xu@northeastern.edu");
    }
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
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getBaseContext(), "Xuening Wei wei.xu@northeastern.edu", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, ClickyActivity.class);
        startActivity(intent);
    }

//    public void displayToastMsg(View view) {
//        toastMsg("Xuening Wei wei.xu@northeastern.edu");
//    }
}