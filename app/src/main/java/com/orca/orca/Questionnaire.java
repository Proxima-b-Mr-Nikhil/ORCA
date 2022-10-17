package com.orca.orca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Questionnaire extends AppCompatActivity {

    LinearLayout r1,r2,r3,r4,b1,b2,b3,b4;
    Button yes,no,next;
    TextView q;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        next=findViewById(R.id.next);
        q=findViewById(R.id.q);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                yes.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                no.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                no.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                count++;
                if (count>=8){
                   Intent intent=new Intent(getApplicationContext(), taskCalendar.class);
                   startActivity(intent);

                }
                switch (count) {
                    case 1:
                       q.setText("Are you using antidepressants for more than a year ?");
                        break;
                    case 2:
                        q.setText("Are you a thyroid patient ?");
                        break;
                    case 3:
                        q.setText("  Are you feeling stressed ?");
                        break;
                    case 4:
                        q.setText("Did you observe a sudden decrease in need of sleep ?" );
                        break;
                    case 5:
                        q.setText("Are you flying suddenly from one idea to the next ?" );
                        break;
                    case 6:
                        q.setText("Are you speaking more loudly than before ?" );
                        break;
                    case 7:
                        q.setText("Are you more hyperactive than before ?" );
                        break;

                }


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
