package com.sagar.countdowntimer;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText edtTime;
    private Button btnStart;
    private TextView txtremain;
    private LinearLayout llStart;
    private LinearLayout llRemainingTime;
    private Button btnGoBack;
    private LinearLayout llmain;


    private String strTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        initViews();

    }


    public void initViews() {

        llStart = findViewById(R.id.llstart);
        llRemainingTime = findViewById(R.id.llresult);
        llmain = findViewById(R.id.maincontainer);
        llmain.setOrientation(LinearLayout.VERTICAL);

        edtTime = findViewById(R.id.edt_timer);

        txtremain = findViewById(R.id.timeRemain);


        btnStart = findViewById(R.id.btnstart);
        btnGoBack = findViewById(R.id.btngoback);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llStart.setVisibility(View.GONE);
                llRemainingTime.setVisibility(View.VISIBLE);

                strTime = edtTime.getText().toString().trim();

                long time = TimeUnit.SECONDS.toMillis(Long.parseLong(strTime));


                new CountDownTimer(time, 1000) {

                    public void onTick(long millisUntilFinished) {
                        txtremain.setText("Time remaining: " + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        txtremain.setText("Completed");

                        btnGoBack.setVisibility(View.VISIBLE);
                        btnGoBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                llRemainingTime.setVisibility(View.GONE);
                                btnGoBack.setVisibility(View.GONE);

                                llStart.setVisibility(View.VISIBLE);
                                edtTime.getText().clear();

                            }
                        });

                    }

                }.start();


            }
        });
    }


}