package com.orca.orca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView confirm,wait,m;
    ImageButton send;
    EditText editText;
    public int counter=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m=findViewById(R.id.m);
        confirm=findViewById(R.id.confirm);
        send=findViewById(R.id.btn_send);
        wait=findViewById(R.id.wait);
        editText=findViewById(R.id.msg);
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.setVisibility(View.GONE);
                confirm.setVisibility(View.VISIBLE);
                confirm.setText("I agree to the terms and conditions ");

                editText.setVisibility(View.GONE);
                wait.setVisibility(View.VISIBLE);
                new CountDownTimer(5000, 1000){
                    public void onTick(long millisUntilFinished){
                      int a=  counter--;
                      send.setVisibility(View.GONE);
                        wait.setText("please wait while we redirect to the task page in"+" "+a +"s");
                    }
                    public  void onFinish(){

                        Intent intent=new Intent(getApplicationContext(),Questionnaire.class);
                        startActivity(intent);
                    }
                }.start();
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
