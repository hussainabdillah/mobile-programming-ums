package com.project.timercountdownapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumbers;
    private Button buttonStart;
    private Button buttonStop;
    private TextView textViewCountDown;
    private Intent serviceIntent;
    private CountDownTimer countDownTimer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumbers = findViewById(R.id.inputNumbers);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        textViewCountDown = findViewById(R.id.textViewCountDown);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputNumbers.getText().toString();
                if (!inputText.isEmpty()) {
                    try {
                        int countdownValue = Integer.parseInt(inputText);
                        startCountdown(countdownValue);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Invalid input format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCountdown();
            }
        });
    }

    private void startCountdown(int countdownValue) {
        textViewCountDown.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(countdownValue * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsRemaining = (int) millisUntilFinished / 1000;
                updateCountdownText(secondsRemaining);
            }

            @Override
            public void onFinish() {
                textViewCountDown.setVisibility(View.GONE);
            }
        };
        countDownTimer.start();
        startCountdownService(countdownValue);
    }

    private void updateCountdownText(int secondsRemaining) {
        textViewCountDown.setText(String.valueOf(secondsRemaining));
    }

    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        stopCountdownService();
    }

    private void startCountdownService(int countdownValue) {
        serviceIntent = new Intent(this, CountdownService.class);
        serviceIntent.putExtra("COUNTDOWN_VALUE", countdownValue);
        startService(serviceIntent);
    }

    private void stopCountdownService() {
        if (serviceIntent != null) {
            stopService(serviceIntent);
        }
    }
}





