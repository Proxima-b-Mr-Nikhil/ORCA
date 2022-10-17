package com.orca.orca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class taskCalendar extends AppCompatActivity {
    TextView taskno,taskname,taskinfo,tvTime;
    Button start,next,ex,gd,bd;
    ImageView img;
    LinearLayout lexp;
    int count=0;
    BarChart barChart;

    private static final String[] DAYS = { "","" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        tvTime=findViewById(R.id.timer);
        start=findViewById(R.id.startbutton);
        next=findViewById(R.id.nexttask);
        lexp=findViewById(R.id.lexp);
        taskno=findViewById(R.id.taskno);
        taskname=findViewById(R.id.taskname);
        taskinfo=findViewById(R.id.taskinfo);
        img=findViewById(R.id.img);
        ex=findViewById(R.id.excellent);
        bd=findViewById(R.id.bad);
        gd=findViewById(R.id.good);

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gd.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                bd.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                ex.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
            }
        });
        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                bd.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                gd.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
            }
        });
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gd.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                ex.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_bg));
                bd.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundedbuttongreen));
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CountDownTimer(2000, 1000){
                    @SuppressLint({"SetTextI18n", "DefaultLocale"})
                    public void onTick(long millisUntilFinished){
                        start.setVisibility(View.GONE);
                        tvTime.setText(String.format(Locale.getDefault(), "Time Remaining %02d min: %02d sec",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60));
                    }
                    public  void onFinish(){
                        lexp.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);
                        tvTime.setText("Congratulations ! you have finished the task.\n give review about your experience and move to the next task. ");
                    }
                }.start();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=count++;
                switch (a){
                    case 1:
                        tvTime.setVisibility(View.GONE);
                        img.setBackgroundResource(R.drawable.music);
                        taskno.setText("Task-2");
                        taskname.setText("Listen to the Music");
                        taskinfo.setText("listening to music also increases cognitive functions, creativity, and decreases feelings of fatigue. All of these factors lead to better workflow and a more optimal result in the activity done while listening to music.");
                        break;
                    case 2:
                        img.setBackgroundResource(R.drawable.sport);
                        taskno.setText("Task-3");
                        taskname.setText("Playing a Sport/Game");
                        taskinfo.setText("When you are physically active your mind gets a chance to unplug from daily stresses and strains of life. Physical exercise reduces the stress hormones in your body and stimulates the release of endorphins. These endorphins may give you more energy and focus for whatever life has.");
                        break;
                    case 3:
                        img.setBackgroundResource(R.drawable.swimming);
                        taskno.setText("Task-4");
                        taskname.setText("Do Swimming");
                        taskinfo.setText("keeps your heart rate up but takes some of the impact stress off your body, builds endurance, muscle strength and cardiovascular fitness, helps maintain a healthy weight, healthy heart and lungs.");
                        break;
                    case 4:
                        img.setBackgroundResource(R.drawable.cycling);
                        taskno.setText("Task-5");
                        taskname.setText("Do Cycling");
                        taskinfo.setText("Cycling is mainly an aerobic activity, which means that your heart, blood vessels and lungs all get a workout. You will breathe deeper, perspire and experience increased body temperature, which will improve your overall fitness level.");
                        break;
                    case 5:
                        img.setBackgroundResource(R.drawable.paint);
                        taskno.setText("Task-6");
                        taskname.setText("Make a Painting");
                        taskinfo.setText("Mental-health issues and stress or high anxiety often go together. Finding an emotional release like painting allows a person’s mind to relax and let go of all the problems that contribute to a high stress level.");
                        break;
                    case 6:
                        img.setBackgroundResource(R.drawable.workout);
                        taskno.setText("Task-7");
                        taskname.setText("WorkOut");
                        taskinfo.setText("Exercise has been shown to improve your mood and decrease feelings of depression, anxiety and stress. It can also help them be more aware of their mental state and practice distraction from their fears");
                        break;
                    case 7:
                        img.setBackgroundResource(R.drawable.social);
                        taskno.setText("Task-8");
                        taskname.setText("Social Interaction");
                        taskinfo.setText("Better mental health – it can lighten your mood and make you feel happier.Research shows that having a strong network of support or strong community bonds fosters both emotional and physical health and is an important component of adult life.");
                        break;
                    case 8:
                        img.setBackgroundResource(R.drawable.home);
                        taskno.setText("Task-9");
                        taskname.setText("Organize Your Space");
                        taskinfo.setText("An organized home enables you to be more productive. You’ll be able to focus better on the task at hand as your mind and vision are less occupied with the clutter in your house.");
                        break;
                    case 9:
                        img.setBackgroundResource(R.drawable.cook);
                        taskno.setText("Task-10");
                        taskname.setText("Cook any Dish");
                        taskinfo.setText("When you prepare your own meals, you have more control over the ingredients. By cooking for yourself, you can ensure that you and your family eat fresh, wholesome meals. This can help you to look and feel healthier, boost your energy, stabilize your weight and mood, and improve your sleep and resilience to stress.");
                        break;
                    case 10:
                        img.setBackgroundResource(R.drawable.gardening);
                        taskno.setText("Task-11");
                        taskname.setText("Gardening");
                        taskinfo.setText("Gardening gives you a chance to focus on something and put your mind to work with a goal and a task.Getting dirt under your nails while digging in the ground can make you pretty happy.");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            int a = 75;
            int b = 75;



            AlertDialog.Builder builder = new AlertDialog.Builder(taskCalendar.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.percentage, viewGroup, false);

            barChart = dialogView.findViewById(R.id.pieChart);
            barChart.setNoDataText("loading...");
            Paint p = barChart.getPaint(Chart.PAINT_INFO);
            p.setTextSize(60f);
            configureChartAppearance();
            BarDataSet barDataSet1 = new BarDataSet(dataValues1(a,b), "DataSet 1");
            BarData barData =new BarData();
            barData.addDataSet(barDataSet1);

            barChart.setData(barData);
            barDataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
            barDataSet1.setValueTextColor(Color.BLACK);
            barDataSet1.setValueTextSize(10f);

            barDataSet1.setColors(
                    ContextCompat.getColor(barChart.getContext(), R.color.blue),
                    ContextCompat.getColor(barChart.getContext(),R.color.green)


            );



            barChart.getAxisRight().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.setDrawBarShadow(false);
            barChart.setPinchZoom(false);
            barChart.setDrawGridBackground(false);
            barData.setBarWidth(0.65f);
            barChart.getDescription().setEnabled(false);
            barChart.animateY(5000);
            barChart.animateX(6000);
            barChart.invalidate();


            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
        return super.onOptionsItemSelected(item);
    }
    private void configureChartAppearance() {
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();

        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return DAYS[(int) value];
            }
        });
    }

    private List<BarEntry> dataValues1(int a, int b) {

        ArrayList<BarEntry> dataVals=new ArrayList<>();

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Total no.of Task");
        xAxisLabel.add("Performance");



        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        ValueFormatter formatter = new ValueFormatter() {


            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        };

        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        dataVals.add(new BarEntry(0,a));
        dataVals.add(new BarEntry(1,b));

        return dataVals;


    }
}
